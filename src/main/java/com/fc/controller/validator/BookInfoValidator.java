package com.fc.controller.validator;

import com.fc.controller.bean.BookInfoEditVO;
import com.google.common.primitives.Doubles;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookInfoValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return BookInfoEditVO.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    BookInfoEditVO bookInfoEditVO = (BookInfoEditVO) target;
    if (StringUtils.isEmpty(bookInfoEditVO.getBookName())) {
      errors.rejectValue("bookName", "FIELD_EMPTY_STR", "bookName is null");
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getBookAuthor())) {
      errors.rejectValue("bookAuthor", "FIELD_EMPTY_STR", "bookAuthor is null");
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getPrice())) {
      errors.rejectValue("price", "FIELD_EMPTY_STR", "price is null");
    }else{
      if (Doubles.tryParse(bookInfoEditVO.getPrice()) == null) {
        errors.rejectValue("price", "FIELD_EMPTY_STR", "price should be number");
      }
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getBookVersion())) {
      errors.rejectValue("bookVersion", "FIELD_EMPTY_STR", "bookVersion is null");
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getPublisher())) {
      errors.rejectValue("publisher", "FIELD_EMPTY_STR", "publisher is null");
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getCategoryId())) {
      errors.rejectValue("categoryId", "FIELD_EMPTY_STR", "categoryName is null");
    }
    if (StringUtils.isEmpty(bookInfoEditVO.getDescription())) {
      errors.rejectValue("description", "FIELD_EMPTY_STR", "description is null");
    }
    if (bookInfoEditVO.getFile1() != null && bookInfoEditVO.getFile1().getContentType() != null && bookInfoEditVO.getFile1().getSize() > 0) {
      if (!bookInfoEditVO.getFile1().getContentType().contains("image")) {
        errors.rejectValue("file1", "ContentType ERROR", "file1 should be image");
      }
    }
    if (bookInfoEditVO.getFile2() != null && bookInfoEditVO.getFile2().getContentType() != null && bookInfoEditVO.getFile2().getSize() > 0) {
      if (!bookInfoEditVO.getFile2().getContentType().contains("image")) {
        errors.rejectValue("file2", "ContentType ERROR", "file2 should be image");
      }
    }
  }
}
