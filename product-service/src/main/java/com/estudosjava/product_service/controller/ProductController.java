package com.estudosjava.product_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudosjava.product_service.dto.ProductRequestDto;
import com.estudosjava.product_service.dto.ProductResponseDto;
import com.estudosjava.product_service.model.Product;
import com.estudosjava.product_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponseDto createProduct(@RequestBody ProductRequestDto dto) {
    Product newProduct = productService.createProduct(dto);
    return ProductResponseDto.from(newProduct);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponseDto> getAllProducts() {
    return productService.getAllProducts();
  }
}
