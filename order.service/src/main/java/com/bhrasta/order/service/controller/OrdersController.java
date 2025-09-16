package com.bhrasta.order.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhrasta.order.service.DTOs.OrdersDTO;
import com.bhrasta.order.service.Entities.Orders;
import com.bhrasta.order.service.Services.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private OrdersService orderService;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<Orders> CreateNewOrder(@RequestBody OrdersDTO ordersDTO)
	{
		Orders newOrder = orderService.CreateOrder(ordersDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
	}
	
	@GetMapping("/findOrder/o_id")
	public ResponseEntity<Orders> FindOrder(@PathVariable("o_id") String orderId)
	{
		Orders existingOrder = orderService.GetOrder(orderId);
		
		return ResponseEntity.ok(existingOrder);
	}
	
	@GetMapping("/findAllOrders")
	public ResponseEntity<List<Orders>> FindAllOrders()
	{
		List<Orders> allOrders = orderService.GetAllOrders();
		
		return ResponseEntity.ok(allOrders);
	}
	
	@PatchMapping("/updateOrder/o_id")
	public ResponseEntity<Orders> UpdateOrder(@PathVariable("o_id") String orderId, @RequestBody OrdersDTO ordersDTO)
	{
		Orders updatedOrder = orderService.UpdateOrder(orderId, ordersDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedOrder);
	}
	
	@DeleteMapping("/deleteOrder/o_id")
	public ResponseEntity<Void> DeleteOrder(@PathVariable("o_id") String orderId)
	{
		orderService.DeleteOrder(orderId);
		
		return (ResponseEntity<Void>) ResponseEntity.ok();
	}
	
}
