package com.example.adcash.controller;

import com.example.adcash.model.Product;
import com.example.adcash.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Long id) {
    Product productById = productService.findProductById(id);
    return ResponseEntity.ok(productById);
  }

  @PostMapping("/products")
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.addProduct(product));
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                              @RequestBody Product product) {
    productService.updateProduct(id, product);
    return ResponseEntity.ok("Updated product successfully");
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok("Product successfully deleted with id=" + id);
  }

//  @PostMapping("/products/{id}/{categoryId}")
//  public void addCategoryToProduct(@PathVariable Long id,
//                                   @PathVariable Long categoryId) {
//    productService.addCategoryToProduct(id, categoryId);
//  }
}
