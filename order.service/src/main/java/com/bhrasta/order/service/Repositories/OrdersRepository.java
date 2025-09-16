package com.bhrasta.order.service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bhrasta.order.service.Entities.Orders;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String>{

}
