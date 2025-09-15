package com.bhrasta.user.service.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhrasta.user.service.DTOs.UsersDTO;
import com.bhrasta.user.service.Entities.Users;
import com.bhrasta.user.service.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<Users> RegisterUser(@RequestBody UsersDTO userDTO)
	{
		Users newUser = userService.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@GetMapping("/findUser/{u_id}")
	public ResponseEntity<Users> FindUser(@PathVariable("u_id") String userId)
	{
		Users existingUsers = userService.getUser(userId);
		return ResponseEntity.ok(existingUsers);
	}
	
	@GetMapping("/findAllUser")
	public ResponseEntity<List<Users>> FindAllUser()
	{
		List<Users> allUsers = userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}
	
	@PutMapping("/updateUser/{u_id}")
	public ResponseEntity<Users> UpdateUserProfile(@PathVariable("u_id") String userId,@RequestBody UsersDTO userDTO)
	{
		Users updatedUser = userService.updateUser(userId, userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
	}
	
	@DeleteMapping("/deleteUser/{u_id}")
	public ResponseEntity<Void> RemoveUser(@PathVariable("u_id") String userId)
	{
		userService.deleteUser(userId);
		return (ResponseEntity<Void>) ResponseEntity.ok();
	}
}
