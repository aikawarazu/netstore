package com.fc.service.impl;

import com.fc.common.model.PageResult;
import com.fc.mapper.BookInfoDao;
import com.fc.model.BookInfo;
import com.fc.model.BookInfoExample;
import com.fc.service.BookService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseService implements BookService {

  private final BookInfoDao bookInfoDao;

  @Autowired
  public BookServiceImpl(BookInfoDao bookInfoDao) {
    this.bookInfoDao = bookInfoDao;
  }

  @Override
  public PageResult<BookInfo> list(Integer page, Integer pageSize, Long categoryId) {
    BookInfoExample example = new BookInfoExample();
    if (page == null) {
      page = 1;
    }
    if (pageSize == null) {
      pageSize = 10;
    }

    if (categoryId != null) {
      example.createCriteria().andCategoryIdEqualTo(categoryId);
    }
    long totalCount = bookInfoDao.countByExample(example);
    PageResult<BookInfo> pageResult = new PageResult<>(pageSize, totalCount);
    if (totalCount == 0) {
      pageResult.setData(new ArrayList<>());
      return pageResult;
    }

    pageResult.setCurPage(page);
    example.setLimit(pageSize);
    example.setOffset(pageResult.getStartIndex());
    pageResult.setData(bookInfoDao.selectByExample(example));
    return pageResult;
  }

  @Override
  public void save(BookInfo bookInfo) {
    try {
      bookInfoDao.insert(bookInfo);
    } catch (Exception e) {
      logger.error("", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteById(int i) {
    try {
      bookInfoDao.deleteByPrimaryKey((long) i);
    } catch (Exception e) {
      logger.error("", e);
    }
  }
}
