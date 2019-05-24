package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.ImageUploadRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.ImageUploadResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */

@Api(value = "图片接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "涉密图片存取接口")
@RequestMapping("/images")
public interface SecurityImageFacade {


  @ApiOperation(value = "图片上传", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "上传图片")
  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  ReturnResult<ImageUploadResponse> upload(ImageUploadRequest request);

  @ApiOperation(value = "图片下载", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "图片下载")
  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  ResponseEntity download(@PathVariable String name);

  @ApiOperation(value = "图片删除", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "图片删除")
  @RequestMapping(value = "/del/{name}", method = RequestMethod.GET)
  ReturnResult deleteImage(@PathVariable String name);

}
