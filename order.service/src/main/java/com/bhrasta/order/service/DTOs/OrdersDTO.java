package com.bhrasta.order.service.DTOs;

import java.time.LocalDateTime;

import com.bhrasta.order.service.Enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDTO {

	private String id;
	private String bagId;
	private String userId;
	private double finalAmt;
	private int quantity;
	private Status status;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	
}
