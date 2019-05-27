package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.Conts.DELETE;
import static cn.org.bjca.footstone.usercenter.Conts.SAVED;
import static cn.org.bjca.footstone.usercenter.api.commons.Conts.SC_OK;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.IMAGES_DELETE_REMOTE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.IMAGES_DOWNLOAD_REMOTE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.IMAGES_UPLOAD_LOCAL_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.IMAGES_UPLOAD_READ_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.IMAGES_UPLOAD_REMOTE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.RESOURCE_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.SQL_EXCEPTION;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.ImageUploadRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.ImageUploadResponse;
import cn.org.bjca.footstone.usercenter.config.AuthCodeConfig;
import cn.org.bjca.footstone.usercenter.dao.mapper.ImagesMapper;
import cn.org.bjca.footstone.usercenter.dao.model.Images;
import cn.org.bjca.footstone.usercenter.dao.model.ImagesExample;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.CleanupInputStreamResource;
import cn.org.bjca.footstone.usercenter.util.FileSecurityUtil;
import cn.org.bjca.footstone.usercenter.util.SignatureUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */
@Slf4j
@Service
@ConfigurationProperties(prefix = "images")
@Data
public class ImagesService {

  private String downloadUrl;
  private String uploadUrl;
  private String deleteUrl;

  @Autowired
  private ImagesMapper imagesMapper;
  @Autowired
  private CloseableHttpClient httpClient;
  @Autowired
  private AuthCodeConfig config;

  public ImageUploadResponse upload(ImageUploadRequest request) {
    //save to db
    Images images = saveDb(request);
    log.info("save to db success : {}", images);

    Images update = copy(images);
    //upload to bedrock-oss
    uploadToRemote(request, update);
    log.info("upload to oss success! {}", update);

    int row = imagesMapper.updateByPrimaryKeySelective(update);
    if (row != 1) {
      throw new BaseException(SQL_EXCEPTION);
    }
    ImageUploadResponse response = new ImageUploadResponse();
    response.setName(update.getName());
    return response;
  }

  private Images copy(Images images) {
    Images img = new Images();
    img.setId(images.getId());
    return img;
  }

  private void uploadToRemote(ImageUploadRequest request, Images images) {
    Path encode = null;
    try {
      encode = FileSecurityUtil.encode(request.getImage().getInputStream());
      images.setName(encode.toFile().getName());

      HttpPost uploadFile = createRequest(encode);
      log.info("开始上传图片服务 {}", uploadFile);

      CloseableHttpResponse response = httpClient.execute(uploadFile);
      String res = EntityUtils.toString(response.getEntity());
      log.info("oss 返回 {} ", res);

      ReturnResult returnResult = JSON.parseObject(res, ReturnResult.class);
      if (returnResult.getStatus() != SC_OK) {
        throw new BaseException(IMAGES_UPLOAD_REMOTE_ERROR);
      }
      JSONObject data = (JSONObject) returnResult.getData();
      String id = data.getString("id");
      images.setOutFileName(id);
      images.setSaveStatus(SAVED);
    } catch (IOException e) {
      log.error("文件上传异常 ", e);
      throw new BaseException(IMAGES_UPLOAD_REMOTE_ERROR, e);
    } finally {
      if (nonNull(encode)) {
        //noinspection ResultOfMethodCallIgnored
        encode.toFile().delete();
      }
    }
  }

  private HttpPost createRequest(Path file) {
    HttpPost uploadFile = new HttpPost(uploadUrl);
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    HashMap<String, String> params = createParam();
    params.put("md5", generatorMd5(file));
    String signStr = SignatureUtils
        .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, config.getSignKey());
    params.put("signature", signStr);
    for (Entry<String, String> entry : params.entrySet()) {
      builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN);
    }
    try {
      builder.addBinaryBody("file", new FileInputStream(file.toFile()),
          ContentType.APPLICATION_OCTET_STREAM,
          file.toFile().getName() + ".pk"
      );
    } catch (FileNotFoundException e) {
      log.error("文件读取异常! ", e);
      throw new BaseException(IMAGES_UPLOAD_LOCAL_ERROR, e);
    }
    HttpEntity multipart = builder.build();
    uploadFile.setEntity(multipart);
    return uploadFile;
  }

  private HashMap<String, String> createParam() {
    HashMap<String, String> params = Maps.newHashMap();
    params.put("appId", config.getAppId());
    params.put("deviceId", config.getDeviceId());
    params.put("version", config.getVersion());
    params.put("signAlgo", SignatureUtils.SIGN_ALGORITHMS_HMACSHA256);
    return params;
  }

  private String generatorMd5(Path image) {
    try (InputStream inputStream = new FileInputStream(image.toFile())) {
      return DigestUtils.md5Hex(inputStream);
    } catch (IOException e) {
      log.error("文件生成Md5异常", e);
    }
    throw new BaseException(IMAGES_UPLOAD_READ_ERROR);
  }

  private Images saveDb(ImageUploadRequest request) {
    Images images = new Images();
    images.setAppId(request.getAppId());
    images.setOriginFileName(request.getImage().getOriginalFilename());
    images.setUid(request.getUid());
    imagesMapper.insertSelective(images);
    return images;
  }

  public ResponseEntity<InputStreamResource> download(String name) {
    Images images = queryImages(name);
    if (isNull(images)) {
      throw new BaseException(RESOURCE_NOT_EXIST);
    }
    /**状态为DELETE也报不存在**/
    if (images.getSaveStatus().equals(DELETE)) {
      throw new BaseException(RESOURCE_NOT_EXIST);
    }
    String outFileName = images.getOutFileName();

    HttpResponse response = doDownload(outFileName);
    try {
      String fileName = URLEncoder
          .encode(images.getOriginFileName(), StandardCharsets.UTF_8.toString());
      Path decode = FileSecurityUtil.decode(response.getEntity().getContent());
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName)
          .contentType(getContentType(images))
          .contentLength(response.getEntity().getContentLength())
          .body(new CleanupInputStreamResource(decode.toFile()));
    } catch (IOException e) {
      log.error("文件流读取异常!", e);
      throw new BaseException(IMAGES_DOWNLOAD_REMOTE_ERROR, e);
    }
  }

  private MediaType getContentType(Images images) {
    return APPLICATION_OCTET_STREAM;
  }

  private HttpResponse doDownload(String outFileName) {
    HttpGet request = createDownloadRequest(outFileName);
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
      HttpEntity entity = response.getEntity();
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode != HttpStatus.SC_OK) {
        log.error(EntityUtils.toString(entity));
      }
      return response;
    } catch (IOException e) {
      log.error("请求 {} 异常", request);
      throw new BaseException(IMAGES_DOWNLOAD_REMOTE_ERROR, e);
    }

  }

  private HttpGet createDownloadRequest(String outFileName) {
    URIBuilder builder = null;
    try {
      builder = new URIBuilder(downloadUrl);
      HashMap<String, String> params = createParam();
      params.put("id", outFileName);
      String signStr = SignatureUtils
          .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, config.getSignKey());
      params.put("signature", signStr);
      for (Entry<String, String> entry : params.entrySet()) {
        builder.setParameter(entry.getKey(), entry.getValue());
      }
      return new HttpGet(builder.build());
    } catch (URISyntaxException e) {
      log.error("创建下载请求异常!", e);
      throw new BaseException(ERROR, e);
    }

  }

  private Images queryImages(String name) {
    ImagesExample example = new ImagesExample();
    example.createCriteria().andNameEqualTo(name);
    List<Images> images = imagesMapper.selectByExample(example);
    return images.isEmpty() ? null : images.get(0);
  }

  public void deleteImage(String name) {
    Images images = queryImages(name, SAVED);
    if (isNull(images)) {
      throw new BaseException(RESOURCE_NOT_EXIST);
    }
    // bedrock delete
    doDelete(images.getOutFileName());
    // save db 修改状态
    images.setSaveStatus(DELETE);
    int row = imagesMapper.updateByPrimaryKeySelective(images);
    if (row != 1) {
      throw new BaseException(SQL_EXCEPTION);
    }
  }

  private Images queryImages(String name, String saveStatus) {
    ImagesExample example = new ImagesExample();
    example.createCriteria().andNameEqualTo(name).andSaveStatusEqualTo(saveStatus);
    List<Images> images = imagesMapper.selectByExample(example);
    return images.isEmpty() ? null : images.get(0);
  }

  private void doDelete(String outFileName) {
    HttpDelete request = createDeleteRequest(outFileName);
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
      HttpEntity entity = response.getEntity();
      int responseCode = response.getStatusLine().getStatusCode();
      if (responseCode != HttpStatus.SC_OK) {
        log.error(EntityUtils.toString(entity));
      }
    } catch (IOException e) {
      log.error("请求 {} 异常", request);
      throw new BaseException(IMAGES_DELETE_REMOTE_ERROR, e);
    }
  }

  private HttpDelete createDeleteRequest(String outFileName) {
    URIBuilder builder = null;
    try {
      builder = new URIBuilder(deleteUrl);
      HashMap<String, String> params = createParam();
      params.put("id", outFileName);
      String signStr = SignatureUtils
          .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, config.getSignKey());
      params.put("signature", signStr);
      for (Entry<String, String> entry : params.entrySet()) {
        builder.setParameter(entry.getKey(), entry.getValue());
      }
      return new HttpDelete(builder.build());
    } catch (URISyntaxException e) {
      log.error("创建删除请求异常!", e);
      throw new BaseException(ERROR, e);
    }
  }

}
