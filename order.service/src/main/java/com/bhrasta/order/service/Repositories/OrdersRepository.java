package com.bhrasta.order.service.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bhrasta.order.service.Entities.Orders;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String>{

	//custom functions
	//@Query("SELECT * FROM Orders WHERE userId = : u_id")
	public List<Orders> findByUserId(String userId); //@Param("u_id") 
	
	//@Query("SELECT * FROM Orders WHERE bagId = : b_id")
	public List<Orders> findByBagId(String bagId); //@Param("b_id") 
}
