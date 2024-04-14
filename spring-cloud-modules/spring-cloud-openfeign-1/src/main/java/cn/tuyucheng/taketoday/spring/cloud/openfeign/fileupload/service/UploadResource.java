package cn.tuyucheng.taketoday.spring.cloud.openfeign.fileupload.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.web.multipart.MultipartFile;

public interface UploadResource {

   @RequestLine("POST /upload-file")
   @Headers("Content-Type: multipart/form-data")
   Response uploadFile(@Param("file") MultipartFile file);
}