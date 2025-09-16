package com.bhrasta.order.service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhrasta.order.service.Entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String>{

}
