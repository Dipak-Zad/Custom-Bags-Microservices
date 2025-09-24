
package com.bhrasta.user.service.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

	private String id;
	private String bagId;
	private String userId;
	private Double finalAmt;
	private Integer quantity;
	private String status;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	
}
