package com.bhrasta.user.service.DTOs;

import java.time.LocalDateTime;

import com.bhrasta.user.service.Enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

	private String id;
	private String name;
	private String mail;
	private String phone;
	private String location;
	private Status status;
	private String created_by;
	private LocalDateTime created_date;
	private String modified_by;
	private LocalDateTime modified_date;
	
}
