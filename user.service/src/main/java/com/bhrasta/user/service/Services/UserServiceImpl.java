package com.bhrasta.user.service.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

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
	
	@Override
	public Users createUser(UsersDTO userDTO) {
		
		Users user = new Users();
		
		String randomUserId = UUID.randomUUID().toString();
		user.setId(randomUserId);
		user.setCreated_date(LocalDateTime.now());
		user.setCreated_by("current session user");
		
		user = modelMapper.map(userDTO, Users.class);
		
		return userRepository.save(user);
	}

	@Override
	public Users getUser(String id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found "+id));
	}

	@Override
	public List<Users> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public Users updateUser(String id, UsersDTO userDTO) {
		
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

	@Override
	public void deleteUser(String id) {
		
		userRepository.deleteById(id);
		
	}

}
