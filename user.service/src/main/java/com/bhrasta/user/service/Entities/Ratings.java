package com.bhrasta.user.service.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {

	private String id;
	private String bagId;
	private String userId;
	private Integer rating;
	
}
