package com.bhrasta.bag.service.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhrasta.bag.service.DTOs.BagsDTO;
import com.bhrasta.bag.service.Entities.Bags;
import com.bhrasta.bag.service.Services.BagsService;

@RestController
@RequestMapping("/api/bags")
public class BagsController {

	@Autowired
	private BagsService bagsService;
	
	@PostMapping("/saveBag")
	public ResponseEntity<Bags> AddNewBag(@RequestBody BagsDTO bagsDTO)
	{
		Bags newBag = bagsService.addBag(bagsDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newBag);
	}
	
	@GetMapping("/findBag/{b_id}")
	public ResponseEntity<Bags> FindBag(@PathVariable("b_id") String bagId)
	{
		Bags foundBags = bagsService.getBag(bagId);
		
		return ResponseEntity.ok(foundBags);
	}
	
	@GetMapping("/findAllBag")
	public ResponseEntity<List<Bags>> FindAllBags()
	{
		List<Bags> bagList = bagsService.getAllBags();
		
		return ResponseEntity.ok(bagList);
	}
	
	@PatchMapping("/updateBag/{b_id}")
	public ResponseEntity<Bags> UpdateBag(@PathVariable("b_id") String bagId,@RequestBody BagsDTO bagsDTO)
	{
		System.out.println("controller status sent."+bagsDTO.getStatus());
		Bags updatedBag = bagsService.updateBag(bagId, bagsDTO);
		System.out.println("controller status update."+updatedBag.getStatus());
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedBag);
	}
	
	@DeleteMapping("/deleteBag/{b_id}")
	public String RemoveBag(@PathVariable("b_id") String bagId)
	{
		boolean success = bagsService.deleteBag(bagId);
		
		return success ? "Bag deleted successfully!" : "Bag deletion failed!";
	}
}
