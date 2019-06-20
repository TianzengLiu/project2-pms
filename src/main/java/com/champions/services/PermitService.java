package com.champions.services;

import java.util.List;
import java.util.Map;

import com.champions.models.Permit;

public interface PermitService {

	public List<Permit> findAll();
	
	public Permit findById(Integer id);
	
	public List<Permit> findByParkingSpot(String parkingSpot);
	
	public List<Permit> findByVehicleLicense(String vehicleLicense);
	
	public Permit save(Permit permit);
	
	public Permit update(Map<String, Object> updates, int id);
}
