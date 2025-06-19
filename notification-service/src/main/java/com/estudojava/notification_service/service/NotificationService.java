package com.estudojava.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.estudosjava.order_service.event.OrderPlacedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private final JavaMailSender javaMailSender;
  
  @KafkaListener(topics = "order-placed")
  public void listen(OrderPlacedEvent orderPlacedEvent) {
    log.info("Got message from order-placed topic {}", orderPlacedEvent);
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("troffidinho@gmail.com");
      messageHelper.setTo(orderPlacedEvent.getEmail().toString());
      messageHelper.setSubject(String.format("Your order with with order number %s is placed successfully", orderPlacedEvent.getOrderNumber()));
      messageHelper.setText(String.format("""
                            Hi, %s %s!

                            Your order with order number %s is now placed successfully.
                            
                            Best Regards
                            Spring Shop
                            """,
                    orderPlacedEvent.getFirstName().toString(),
                    orderPlacedEvent.getLastName().toString(),      
                    orderPlacedEvent.getOrderNumber()));
    };

    try {
      javaMailSender.send(messagePreparator);
      log.info("Order notification email sent");
    } catch (Exception e) {
      log.error("Exception occurred when sending mail", e);
      throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
    }
  }

}
