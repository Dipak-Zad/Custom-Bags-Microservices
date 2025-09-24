package com.bhrasta.user.service.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.modelmapper.ModelMapper;

import com.bhrasta.user.service.Entities.Orders;
import com.bhrasta.user.service.DTOs.UsersDTO;
import com.bhrasta.user.service.Entities.Users;
import com.bhrasta.user.service.Exceptions.ResourceNotFoundException;
import com.bhrasta.user.service.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Users createUser(UsersDTO userDTO) {
		
		try {
		
			Users user = new Users();
			
			user = modelMapper.map(userDTO, Users.class);
			
			String randomUserId = UUID.randomUUID().toString();
			user.setId(randomUserId);
			user.setCreated_date(LocalDateTime.now());
			user.setCreated_by("current session user");
			
			System.out.println("user created!!!");
			
			return userRepository.save(user);
		
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
        
        return null;
	}

	@Override
	public Users getUser(String id) {
		
		try {
			
			Users user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found "+id));
		
			ArrayList<Orders> userOrdersList = restTemplate.getForObject("http://localhost:8080/api/orders/findUserOrders/"+id, ArrayList.class);
			
			user.setOrders(userOrdersList);
			
			return user;
	
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
        
        return null;
	}

	@Override
	public List<Users> getAllUser() {
		
		try {
		
		return userRepository.findAll();
		
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
        
        return null;
	}

	@Override
	public Users updateUser(String id, UsersDTO userDTO) {
		
		try {
			
			Users existingUsers = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found "+id));
			
			if (userDTO.getName() != null && !userDTO.getName().trim().isEmpty()) {
		        existingUsers.setName(userDTO.getName());
		    }
			
			if (userDTO.getMail() != null && !userDTO.getMail().trim().isEmpty()) {
		        existingUsers.setMail(userDTO.getMail());
		    }
			
			if (userDTO.getPhone() != null && !userDTO.getPhone().trim().isEmpty()) {
		        existingUsers.setPhone(userDTO.getPhone());
		    }
			
			if (userDTO.getLocation() != null && !userDTO.getLocation().trim().isEmpty()) {
		        existingUsers.setLocation(userDTO.getLocation());
		    }
			
			if (userDTO.getStatus() != null) {
		        existingUsers.setStatus(userDTO.getStatus());
		    }
			
			existingUsers.setModified_date(LocalDateTime.now());
			existingUsers.setModified_by("current logged in User");
			
			return userRepository.save(existingUsers);
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(String id) {
		
		try {
			
			userRepository.deleteById(id);
			
			Users userCheck = userRepository.findById(id).orElse(null);	
		
			if(userCheck==null)
			{	
				System.out.println("user deleted in service layer");
			}

			return  userCheck == null;
		
		}
		catch (Exception e) {
			//System.err.println(e);
			e.printStackTrace();
		}
		return false;
	}

}
