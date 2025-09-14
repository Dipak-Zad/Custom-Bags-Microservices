package com.bhrasta.user.service.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import com.bhrasta.user.service.Enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Table(name="USERS")
public class Users {

	@Id
	private String id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "MAIL", nullable = false, unique = true)
	private String mail;
	
	@Column(name = "PHONE", nullable = false, unique = true, length = 10)
	private String phone;
	
	@Column(name = "LOCATION", nullable = false)
	private String location;

	@Column(name = "STATUS", nullable = false)
	private Status status = Status.ACTIVE;

	@Column(name = "CREATED_BY", nullable = false)
	private String created_by;

	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDateTime created_date;

	@Column(name = "MODIFIED_BY", nullable = false)
	private String modified_by;
	
	@UpdateTimestamp
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modified_date;
}
