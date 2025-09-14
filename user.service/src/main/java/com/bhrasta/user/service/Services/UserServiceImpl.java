package com.bhrasta.user.service.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhrasta.user.service.Entities.Users;
import com.bhrasta.user.service.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Users createUser(Users user) {
		
		return userRepository.save(user);
	}

	@Override
	public Users getUser(String id) {
		
		//Users user =  userRepository.getById(id).orElseThrow(() -> new RunTimeException("User not found with given ID :"+id));
	
		return userRepository.getById(id);
	}

	@Override
	public List<Users> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public Users updateUser(String id, Users user) {
		
		Users existingUsers = userRepository.getById(id);
		
		existingUsers.setName(user.getName());
		existingUsers.setMail(user.getMail());
		existingUsers.setPhone(user.getPhone());
		existingUsers.setLocation(user.getLocation());
		existingUsers.setStatus(user.getStatus());
		existingUsers.setModified_date(LocalDateTime.now());
		existingUsers.setModified_by("current logged in User");
		
		return userRepository.save(existingUsers);
	}

	@Override
	public void deleteUser(String id) {
		
		userRepository.deleteById(id);
		
	}

}
