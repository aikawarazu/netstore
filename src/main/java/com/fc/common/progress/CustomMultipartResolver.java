package com.fc.common.progress;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CustomMultipartResolver extends CommonsMultipartResolver {

  @Override
  protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
    String encoding = determineEncoding(request);
    FileUpload fileUpload = prepareFileUpload(encoding);
    //fileUpload.setFileSizeMax(1024 * 1024 * 500);// 单个文件最大500M
    //fileUpload.setSizeMax(1024 * 1024 * 500);// 一次提交总文件最大500M
    //向文件上傳進度監視器設置session用於存儲上傳進度
    FileUploadProgressListener listener = new FileUploadProgressListener(request.getSession());
    //將文件上傳進度監視器加入到fileUpload中
    fileUpload.setProgressListener(listener);
    try {
      List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
      return parseFileItems(fileItems, encoding);
    } catch (FileUploadException e) {
      e.printStackTrace();
    }
    return null;
  }
}
