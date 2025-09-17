package com.bhrasta.order.service.Services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public Orders CreateOrder(OrdersDTO orderDTO) {
		try {
			Orders orders = new Orders();
		
			orders = modelMapper.map(orderDTO, Orders.class);
			
			orders.setStatus(Status.CREATED);
			orders.setCreatedBy("current session user");
			orders.setCreatedDate(LocalDateTime.now());
			
			System.out.println("order bagId "+orders.getBagId());
	
			System.out.println("order userId "+orders.getUserId());
	
			System.out.println("order finalAmt "+orders.getFinalAmt());
	
			System.out.println("order quantity "+orders.getQuantity());
	
			System.out.println("order status "+orders.getStatus());
	
			System.out.println("order createdDate "+orders.getCreatedDate());
	
			System.out.println("order createdBy "+orders.getCreatedBy());
	
			System.out.println("order modifiedDate "+orders.getModifiedDate());
	
			System.out.println("order modifiedBy "+orders.getModifiedBy());
			
			return ordersRepo.save(orders);
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Orders GetOrder(String id) {
		try {
				return ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oder with given ID is not found !! "+id));
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Orders findOrderByIdUsingTemplate(String orderId) {
		
		try {
		
        Query query = new Query(Criteria.where("id").is(new ObjectId(orderId)));
        
        System.out.println("inside orderservice Impl ");
        
        return mongoTemplate.findOne(query, Orders.class);
        
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
        
        return null;
    }
	
	@Override
	public List<Orders> GetAllOrders() {
		
		try {
		
		return ordersRepo.findAll();
		
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Orders UpdateOrder(String id, OrdersDTO orderDTO) { 
		
		try {
			
			Query query = new Query(Criteria.where("id").is(new ObjectId(id)));
	        
	        System.out.println("inside orderservice Impl ");
	        
	        Orders existingOrder =  mongoTemplate.findOne(query, Orders.class);
			
			//Orders existingOrder = ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oder with given ID is not found !! "+id));
			
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
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean DeleteOrder(String id) {
		
		try {
			
				Query query = new Query(Criteria.where("id").is(new ObjectId(id)));
		        
		        System.out.println("inside orderservice Impl ");
		        
		        Orders existingOrder =  mongoTemplate.findAndRemove(query, Orders.class); 
		        
		        return existingOrder == null;
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		return false;
		
	}

}
