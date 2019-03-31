package com.fc.common.progress;

import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.ProgressListener;

public class FileUploadProgressListener implements ProgressListener {

  private HttpSession session;

  FileUploadProgressListener(HttpSession session) {
    this.session = session;
  }

  @Override
  public void update(long pBytesRead, long pContentLength, int pItems) {
    System.out.println(pBytesRead + "/" + pContentLength + ":" + pItems);
  }

  public HttpSession getSession() {
    return session;
  }
}
