package com.example.adcash.service;

import com.example.adcash.model.Category;
import com.example.adcash.model.Product;
import com.example.adcash.repository.CategoryRepository;
import com.example.adcash.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryRepository categoryRepo;

  public ProductService(ProductRepository productRepo, CategoryRepository categoryRepository, CategoryRepository categoryRepo) {
    this.productRepository = productRepo;
    this.categoryRepository = categoryRepository;
    this.categoryRepo = categoryRepo;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product addProduct(Product product) {
    return productRepository.save(product);
  }

  public Product findProductById(Long productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }

  public void updateProduct(Long productId, Product product) {
    productRepository
        .findById(productId)
        .ifPresentOrElse(
            existingProduct -> {
              existingProduct.setName(product.getName());
              productRepository.save(existingProduct);
            },
            () -> {
              throw new ResponseStatusException(BAD_REQUEST);
            });
  }

//  public void addCategoryToProduct(Long productId, Long categoryId) {
//
//    productRepository.findById(productId).ifPresent(product -> {
//      categoryRepository.findById(categoryId).ifPresent(category -> {
//        category.setProduct(product);
//        categoryRepository.save(category);
//      });
//
//    });
//  }
}
