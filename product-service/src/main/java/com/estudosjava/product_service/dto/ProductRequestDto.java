package com.estudosjava.product_service.dto;

import java.math.BigDecimal;

import com.estudosjava.product_service.model.Product;

public record ProductRequestDto(String id, String name, String description, BigDecimal price) {

  public Product toEntity() {
    Product product = new Product();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    return product;
  }
}
