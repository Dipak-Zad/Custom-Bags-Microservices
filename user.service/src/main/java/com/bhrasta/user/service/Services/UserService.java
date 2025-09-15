package com.bhrasta.user.service.Services;

import java.util.List;

import com.bhrasta.user.service.DTOs.UsersDTO;
import com.bhrasta.user.service.Entities.Users;

public interface UserService {
	
	Users createUser(UsersDTO userDTO);
	
	Users getUser(String id);
	
	List<Users> getAllUser();
	
	Users updateUser(String id, UsersDTO userDTO);
	
	void deleteUser(String id);


}
