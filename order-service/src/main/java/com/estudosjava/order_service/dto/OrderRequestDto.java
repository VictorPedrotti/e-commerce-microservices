package com.estudosjava.order_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.estudosjava.order_service.model.Order;

public record OrderRequestDto(Long id, String orderNumber, String skuCode,
                              BigDecimal price, Integer quantity, UserDetails userDetails) {

  public record UserDetails(String email, String firstName, String lastName) {
  }

  public Order toEntity() {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    order.setSkuCode(skuCode);
    order.setPrice(price);
    order.setQuantity(quantity);
    return order;
  }

}
