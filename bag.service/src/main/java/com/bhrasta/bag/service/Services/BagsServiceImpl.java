package com.bhrasta.bag.service.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bhrasta.bag.service.DTOs.BagsDTO;
import com.bhrasta.bag.service.Entities.Bags;
import com.bhrasta.bag.service.Exceptions.ResourceNotFoundException;
import com.bhrasta.bag.service.Repositories.BagsRepository;

@Service
public class BagsServiceImpl implements BagsService {

	@Autowired
	private BagsRepository bagsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Bags addBag(BagsDTO bagsDTO) {

		try {
			Bags newBag = new Bags();
			
			newBag = modelMapper.map(bagsDTO, Bags.class);
			
			newBag.setCreatedBy("current session user");
			newBag.setCreatedDate(LocalDateTime.now());
			
			return bagsRepository.save(newBag);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Bags getBag(String id) {
		try {
			
			return bagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bag with given ID doesn't exist "+id));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Bags> getAllBags() {
		try {
			
			return bagsRepository.findAll();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Bags updateBag(String id, BagsDTO bagsDTO) {
		try {
			
			Bags existingBag = bagsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bag with given ID doesn't exist "+id));
			
			if (bagsDTO.getBagModel() != null && !bagsDTO.getBagModel().trim().isEmpty()) {
		        existingBag.setBagModel(bagsDTO.getBagModel());
		    }
			
			if (bagsDTO.getBagColor() != null && !bagsDTO.getBagColor().trim().isEmpty()) {
		        existingBag.setBagColor(bagsDTO.getBagColor());
		    }
			
			if (bagsDTO.getBagPrice() != null && !bagsDTO.getBagPrice().trim().isEmpty()) {
		        existingBag.setBagPrice(bagsDTO.getBagPrice());
		    }
			
			if (bagsDTO.getBagType() != null) {
		        existingBag.setBagType(bagsDTO.getBagType());
		    }
			
			if (bagsDTO.getStatus() != null) {
		        existingBag.setStatus(bagsDTO.getStatus());
		    }
			
			existingBag.setModifiedBy("current logged in user");
			existingBag.setModifiedDate(LocalDateTime.now());
			
			System.out.println("service status update."+existingBag.getStatus());
			
			return bagsRepository.save(existingBag);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteBag(String id) {
		try {
			bagsRepository.deleteById(id);
			Bags bagCheck = bagsRepository.findById(id).orElse(null);
			
			return bagCheck == null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
