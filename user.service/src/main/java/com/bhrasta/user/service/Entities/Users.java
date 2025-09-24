package com.bhrasta.user.service.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import com.bhrasta.user.service.DTOs.OrdersDTO;
import com.bhrasta.user.service.Enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class Users {

	@Id
	//@GeneratedValue(generator = "uuid2") // Use uuid2 generator
    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "MAIL", nullable = false, unique = true)
	private String mail;
	
	@Column(name = "PHONE", nullable = false, unique = true, length = 10)
	private String phone;
	
	@Column(name = "LOCATION", nullable = false)
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private Status status = Status.ACTIVE;

	@Column(name = "CREATED_BY", nullable = false)
	private String created_by;

	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDateTime created_date;

	@Column(name = "MODIFIED_BY")
	private String modified_by;
	
	@UpdateTimestamp
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modified_date;
	
	@Transient
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();
//	@Transient
//	private List<Ratings> ratings = new ArrayList<>();
}
