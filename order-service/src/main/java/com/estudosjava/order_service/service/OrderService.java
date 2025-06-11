package com.estudosjava.order_service.service;

import org.springframework.stereotype.Service;

import com.estudosjava.order_service.client.InventoryClient;
import com.estudosjava.order_service.dto.OrderRequestDto;
import com.estudosjava.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final InventoryClient inventoryClient;
  
  public void placeOrder(OrderRequestDto dto) {
    var isProductInStock = inventoryClient.isInStock(dto.skuCode(), dto.quantity());

    if (isProductInStock){
      orderRepository.save(dto.toEntity());
    } else {
        throw new RuntimeException("Product with SkuCode " + dto.skuCode() + " is not in stock");
    }  
  }
}
