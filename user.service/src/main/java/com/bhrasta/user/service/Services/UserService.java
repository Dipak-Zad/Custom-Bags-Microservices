package com.bhrasta.user.service.Services;

import java.util.List;

import com.bhrasta.user.service.Entities.Users;

public interface UserService {
	
	Users createUser(Users user);
	
	Users getUser(String id);
	
	List<Users> getAllUser();
	
	Users updateUser(String id, Users user);
	
	void deleteUser(String id);

}
