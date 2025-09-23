package com.bhrasta.bag.service.DTOs;

import java.time.LocalDateTime;

import com.bhrasta.bag.service.Enums.Status;
import com.bhrasta.bag.service.Enums.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BagsDTO {

	private String id;	
	private String bagModel;
//	private Integer rating;
	private String bagPrice;
	private Type bagType;
	private Status status = Status.AVAILABLE;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	
}
