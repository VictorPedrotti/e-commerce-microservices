package com.estudosjava.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudosjava.product_service.dto.ProductRequestDto;
import com.estudosjava.product_service.dto.ProductResponseDto;
import com.estudosjava.product_service.model.Product;
import com.estudosjava.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  
  private final ProductRepository productRepository;
  
  public Product createProduct(ProductRequestDto dto) {
    Product product = dto.toEntity();
    productRepository.save(product);
    log.info("Product created successfully");
    return product;
  }

  public List<ProductResponseDto> getAllProducts() {
    return productRepository.findAll()
              .stream()
              .map(ProductResponseDto::from)
              .toList();
  }

}
