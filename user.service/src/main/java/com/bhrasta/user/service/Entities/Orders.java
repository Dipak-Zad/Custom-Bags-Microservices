
package com.bhrasta.user.service.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

	@Id
	private String id;
	private String bagId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;
	private Double finalAmt;
	private Integer quantity;
	private String status;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	
}
