package com.bhrasta.order.service.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhrasta.order.service.DTOs.OrdersDTO;
import com.bhrasta.order.service.Entities.Orders;
import com.bhrasta.order.service.Enums.Status;
import com.bhrasta.order.service.Exceptions.ResourceNotFoundException;
import com.bhrasta.order.service.Repositories.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Orders CreateOrder(OrdersDTO orderDTO) {
		
		Orders orders = new Orders();
	
		orders.setStatus(Status.CREATED);
		orders.setCreatedBy("current session user");
		orders.setCreatedDate(LocalDateTime.now());
		
		orders = modelMapper.map(orderDTO, Orders.class);
		
		return ordersRepo.save(orders);
	}

	@Override
	public Orders GetOrder(String id) {
		
		return ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oder with given ID is not found !! "+id));
	}

	@Override
	public List<Orders> GetAllOrders() {
		
		return ordersRepo.findAll();
	}

	@Override
	public Orders UpdateOrder(String id, OrdersDTO orderDTO) { 
		
		Orders existingOrder = ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oder with given ID is not found !! "+id));
		
		if (orderDTO.getBagId() != null && !orderDTO.getBagId().trim().isEmpty()) {
	        existingOrder.setBagId(orderDTO.getBagId());
	    }
		
		if (orderDTO.getUserId() != null && !orderDTO.getUserId().trim().isEmpty()) {
	        existingOrder.setUserId(orderDTO.getUserId());
	    }
		
		if (orderDTO.getFinalAmt() != null) {
	        existingOrder.setFinalAmt(orderDTO.getFinalAmt());
	    }
		
		if (orderDTO.getQuantity() != null) {
	        existingOrder.setQuantity(orderDTO.getQuantity());
	    }
		
		if (orderDTO.getStatus() != null) {
	        existingOrder.setStatus(orderDTO.getStatus());
	    }
		
		existingOrder.setModifiedDate(LocalDateTime.now());
		existingOrder.setModifiedBy("current logged in User");
		
		return ordersRepo.save(existingOrder);
	}

	@Override
	public void DeleteOrder(String id) {
		
		ordersRepo.deleteById(id);
		
	}

}
