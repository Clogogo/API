package com.example.adcash.controller;

import com.example.adcash.model.Category;
import com.example.adcash.model.Product;
import com.example.adcash.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/categories/{id}")
  public ResponseEntity<Category> findProductById(@PathVariable Long id) {
    Category categoryById = categoryService.findCategoryById(id);
    return ResponseEntity.ok(categoryById);
  }

  @PostMapping("/categories")
  public ResponseEntity<Category> addCategory(@RequestBody Category category) {
    return ResponseEntity.ok(categoryService.addCategory(category));
  }

  @PutMapping("/categories/{id}")
  public ResponseEntity<String> updateCategory(@PathVariable Long id,
                                              @RequestBody Category category) {
    categoryService.updateCategory(id, category);
    return ResponseEntity.ok("Updated product successfully");
  }

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok("Category successfully deleted with id=" + id);
  }

}
