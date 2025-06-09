package com.estudosjava.order_service.service;

import org.springframework.stereotype.Service;

import com.estudosjava.order_service.dto.OrderRequestDto;
import com.estudosjava.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  
  public void placeOrder(OrderRequestDto dto) {
    orderRepository.save(dto.toEntity());   
  }
}
