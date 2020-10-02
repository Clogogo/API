package com.example.adcash.service;

import com.example.adcash.model.Category;
import com.example.adcash.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category addCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category findCategoryById(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

  public void deleteCategory(Long categoryId) {
    categoryRepository.deleteById(categoryId);
  }

  public void updateCategory(Long categoryId, Category category) {
    categoryRepository
        .findById(categoryId)
        .ifPresentOrElse(
            existingProduct -> {
              existingProduct.setName(category.getName());
              categoryRepository.save(existingProduct);
            },
            () -> {
              throw new ResponseStatusException(BAD_REQUEST);
            });
  }
}
