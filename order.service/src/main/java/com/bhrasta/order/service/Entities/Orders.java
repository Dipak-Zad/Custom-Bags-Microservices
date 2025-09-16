package com.bhrasta.order.service.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import com.bhrasta.order.service.Enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name="bag_id", nullable=false)
	private String bagId;
	
	@OneToOne
	@Column(name="user_id", nullable=false)
	private String userId;
	
	@Column(name="final_amt", nullable=false)
	private double finalAmt;
	
	@Column(nullable=false)
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Status status = Status.PENDING;
	
	@CreationTimestamp
	@Column(name="created_date", nullable=false)
	private LocalDateTime createdDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	
}
