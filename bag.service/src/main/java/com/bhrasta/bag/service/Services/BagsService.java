package com.bhrasta.bag.service.Services;

import java.util.List;

import com.bhrasta.bag.service.DTOs.BagsDTO;
import com.bhrasta.bag.service.Entities.Bags;

public interface BagsService {

	Bags addBag(BagsDTO bagsDTO);
	
	Bags getBag(String id);
	
	List<Bags> getAllBags();
	
	Bags updateBag(String id, BagsDTO bagsDTO);
	
	boolean deleteBag(String id);
}
