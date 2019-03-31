package com.fc.service;

import com.fc.model.Category;
import java.util.List;

public interface CategoryService {

  List<Category> list();

  Category findByName(String name);

  void save(Category category);

  void deleteById(Integer id);

  List<Category> findByIds(List<Long> categoryIds);
}
