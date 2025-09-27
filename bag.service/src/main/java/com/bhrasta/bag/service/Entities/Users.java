package com.bhrasta.bag.service.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {

	@Id
	private String id;
	private String name;
	private String mail;
	private String phone;
	private String location;
	private String status;
	private String created_by;
	private LocalDateTime created_date;
	private String modified_by;
	private LocalDateTime modified_date;
	
}
