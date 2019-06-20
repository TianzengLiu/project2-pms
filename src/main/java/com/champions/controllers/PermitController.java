package com.champions.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.champions.models.Permit;
import com.champions.models.User;
import com.champions.services.PermitService;

@RestController
@RequestMapping("permit")
public class PermitController {

	private PermitService permitService;

	public PermitController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public PermitController(PermitService permitService) {
		
		this.permitService = permitService;
	}
	
	@GetMapping
	public List<Permit> findAll() {
		
		return permitService.findAll();
	}
	
	@GetMapping("spot/{spot}")
	public List<Permit> findByParkingSpot(@PathVariable String spot) {
		
		return permitService.findByParkingSpot(spot);
	}
	
	@GetMapping("license/{license}")
	public List<Permit> findByVehicleLicense(@PathVariable String license) {
		
		return permitService.findByVehicleLicense(license);
	}
	
	@PostMapping
	public Permit savePermit(@Valid @RequestBody Permit permit) {
		
		return permitService.save(permit);
	}
	
	@RequestMapping(value = "{id}", 
					method = RequestMethod.PATCH, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public Permit partialUpdate(
	@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {

		return permitService.update(updates, Integer.parseInt(id));
	}
}
