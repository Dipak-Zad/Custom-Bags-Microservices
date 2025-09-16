package com.bhrasta.order.service.Services;

import java.util.List;

import com.bhrasta.order.service.DTOs.OrdersDTO;
import com.bhrasta.order.service.Entities.Orders;

public interface OrdersService{

	Orders CreateOrder(OrdersDTO orderDTO);
	
	Orders GetOrder(String id);
	
	List<Orders> GetAllOrders();
	
	Orders UpdateOrder(String id, OrdersDTO orderDTO);
	
	void DeleteOrder(String id);
	
}
