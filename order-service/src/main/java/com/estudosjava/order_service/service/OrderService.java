package com.estudosjava.order_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.estudosjava.order_service.client.InventoryClient;
import com.estudosjava.order_service.dto.OrderRequestDto;
import com.estudosjava.order_service.event.OrderPlacedEvent;
import com.estudosjava.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final InventoryClient inventoryClient;
  private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
  
  public void placeOrder(OrderRequestDto dto) {
    var isProductInStock = inventoryClient.isInStock(dto.skuCode(), dto.quantity());

    if (isProductInStock){
      var order = orderRepository.save(dto.toEntity());

      OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.newBuilder()
            .setOrderNumber(order.getOrderNumber())
            .setEmail(dto.userDetails().email())
            .setFirstName(dto.userDetails().firstName())
            .setLastName(dto.userDetails().lastName())
            .build();
      
      log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
      kafkaTemplate.send("order-placed", orderPlacedEvent);
      log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
    } else {
        throw new RuntimeException("Product with SkuCode " + dto.skuCode() + " is not in stock");
    }  
  }
}
