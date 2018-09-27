package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.SecurityImageFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.ImageUploadRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.ImageUploadResponse;
import cn.org.bjca.footstone.usercenter.biz.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */
@Slf4j
@Controller
public class ImagesController implements SecurityImageFacade {

  @Autowired
  private ImagesService imagesService;

  @Override
  public ReturnResult<ImageUploadResponse> upload(@Validated ImageUploadRequest request) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "上传图片");
    ReturnResult<ImageUploadResponse> result;
    try {
      //noinspection unchecked
      result = ReturnResult.success(imagesService.upload(request));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("图片上传异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }

  @Override
  public ResponseEntity download(@PathVariable String name) {

    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "下载图片");
    ResponseEntity<InputStreamResource> result;
    try {
      //noinspection unchecked
      result = imagesService.download(name);
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("图片下载异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }
}
