package com.bhrasta.bag.service.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import com.bhrasta.bag.service.Enums.Status;
import com.bhrasta.bag.service.Enums.Type;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bags")
public class Bags {

	@Id
	@GeneratedValue(generator = "uuid2") // Use uuid2 generator
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;
	
	@Column(name = "model", nullable = false)
	private String bagModel;
	
	@Column(name = "price", nullable = false)
	private String bagPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private Type bagType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status = Status.AVAILABLE;

	@CreationTimestamp
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@UpdateTimestamp
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Transient
	@OneToMany(mappedBy = "bag", cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();
	
}
