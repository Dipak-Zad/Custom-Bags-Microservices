package com.bhrasta.order.service.Services;

import java.util.List;

import com.bhrasta.order.service.DTOs.OrdersDTO;
import com.bhrasta.order.service.Entities.Orders;

public interface OrdersService{

	Orders CreateOrder(OrdersDTO orderDTO);
	
	Orders GetOrder(String id);
	
	Orders findOrderByIdUsingTemplate(String id);
	
	List<Orders> getAllUserOrders(String userId);
	
	List<Orders> getAllBagOrders(String userId);
	
	List<Orders> GetAllOrders();
	
	Orders UpdateOrder(String id, OrdersDTO orderDTO);
	
	boolean DeleteOrder(String id);
	
}
