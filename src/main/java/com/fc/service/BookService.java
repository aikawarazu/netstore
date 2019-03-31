package com.fc.service;

import com.fc.common.model.PageResult;
import com.fc.model.BookInfo;

public interface BookService {
  PageResult<BookInfo> list(Integer page, Integer pageSize, Long categoryId);

  void save(BookInfo bookInfo);

  void deleteById(int i);

}
