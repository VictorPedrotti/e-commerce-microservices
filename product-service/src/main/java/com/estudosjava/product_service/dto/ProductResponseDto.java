package com.estudosjava.product_service.dto;

import java.math.BigDecimal;

import com.estudosjava.product_service.model.Product;

public record ProductResponseDto(String id, String name, String description, BigDecimal price) {

  public static ProductResponseDto from(Product product) {
    return new ProductResponseDto(
      product.getId(),
      product.getName(),
      product.getDescription(),
      product.getPrice()
    );
  }
}
