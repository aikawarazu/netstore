package com.fc.exceptions;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);

  @Override
  public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
    try {
      out.write("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
