package com.bhrasta.bag.service.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bhrasta.bag.service.DTOs.BagsDTO;
import com.bhrasta.bag.service.Entities.Bags;
import com.bhrasta.bag.service.Entities.Orders;
import com.bhrasta.bag.service.Entities.Users;
import com.bhrasta.bag.service.Exceptions.ResourceNotFoundException;
import com.bhrasta.bag.service.Repositories.BagsRepository;

@Service
public class BagsServiceImpl implements BagsService {

	@Autowired
	private BagsRepository bagsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Bags addBag(BagsDTO bagsDTO) {

		try {
			Bags newBag = new Bags();
			
			newBag = modelMapper.map(bagsDTO, Bags.class);
			
			newBag.setCreatedBy("current session user");
			newBag.setCreatedDate(LocalDateTime.now());
			
			return bagsRepository.save(newBag);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Bags getBag(String id) {
		try
		{
			
			//returns the bag looking for
			Bags bag = bagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bag with given ID doesn't exist "+id));
			
			//returns array of bag's order
			Orders[] bagOrders = restTemplate.getForObject("http://localhost:8080/api/orders/findBagOrders/"+bag.getId(), Orders[].class);
			
			//bagOrder array converted to list
			List<Orders> orders = Arrays.stream(bagOrders).toList();
			
			//getting each order's user and setting it seperately
			List<Orders> ordersList = orders.stream().map(order ->{
				
				ResponseEntity<Users> usersEntity = restTemplate.getForEntity("http://localhost:8081/api/users/findUser/"+order.getUserId(),Users.class);
			
				Users user = usersEntity.getBody();
				
				order.setUser(user);
				
				return order; 
				
			}).collect(Collectors.toList());
			
			bag.setOrders(ordersList);
			
			return bag;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Bags> getAllBags() {
		try {
			
			return bagsRepository.findAll();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Bags updateBag(String id, BagsDTO bagsDTO) {
		try {
			
			Bags existingBag = bagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bag with given ID doesn't exist "+id));
			
			if (bagsDTO.getBagModel() != null && !bagsDTO.getBagModel().trim().isEmpty()) {
		        existingBag.setBagModel(bagsDTO.getBagModel());
		    }
			
//			if (bagsDTO.getRating() != null && (bagsDTO.getRating()<6 && bagsDTO.getRating()>0)) {
//				
//		        existingBag.setRating(bagsDTO.getRating());
//		    }
			
			if (bagsDTO.getBagPrice() != null && !bagsDTO.getBagPrice().trim().isEmpty()) {
		        existingBag.setBagPrice(bagsDTO.getBagPrice());
		    }
			
			if (bagsDTO.getBagType() != null) {
		        existingBag.setBagType(bagsDTO.getBagType());
		    }
			
			if (bagsDTO.getStatus() != null) {
		        existingBag.setStatus(bagsDTO.getStatus());
		    }
			
			existingBag.setModifiedBy("current logged in user");
			existingBag.setModifiedDate(LocalDateTime.now());
			
			System.out.println("service status update."+existingBag.getStatus());
			
			return bagsRepository.save(existingBag);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteBag(String id) {
		try {
			bagsRepository.deleteById(id);
			Bags bagCheck = bagsRepository.findById(id).orElse(null);
			
			return bagCheck == null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
