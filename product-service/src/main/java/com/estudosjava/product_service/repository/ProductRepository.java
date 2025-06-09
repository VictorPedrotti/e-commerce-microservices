package com.estudosjava.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estudosjava.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
