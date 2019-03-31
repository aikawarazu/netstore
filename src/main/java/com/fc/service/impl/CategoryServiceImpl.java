package com.fc.service.impl;

import com.fc.mapper.CategoryDao;
import com.fc.model.Category;
import com.fc.model.CategoryExample;
import com.fc.service.CategoryService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;

  @Autowired
  public CategoryServiceImpl(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Override
  public void save(Category category) {
    categoryDao.insert(category);
  }

  @Override
  public List<Category> list() {
    return categoryDao.selectByExample(new CategoryExample());
  }

  @Override
  public Category findByName(String name) {
    CategoryExample categoryExample = new CategoryExample();
    categoryExample.setLimit(1);
    categoryExample.createCriteria().andNameEqualTo(name);
    List<Category> categories = categoryDao.selectByExample(categoryExample);
    return categories.isEmpty() ? null : categories.get(0);
  }

  @Override
  public void deleteById(Integer id) {
    if (id != null) {
      categoryDao.deleteByPrimaryKey(id.longValue());
    }
  }

  @Override
  public List<Category> findByIds(List<Long> categoryIds) {
    if (categoryIds.isEmpty()) {
      return Collections.emptyList();
    }
    CategoryExample example = new CategoryExample();
    example.createCriteria().andIdIn(categoryIds);
    return categoryDao.selectByExample(example);
  }
}
