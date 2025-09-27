package com.bhrasta.bag.service.Entities;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Orders {

	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "bagId")
	private Bags bag;
	private String userId;
	private Double finalAmt;
	private Integer quantity;
	private String status;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;

	@Transient
	private Users user;
	
}
