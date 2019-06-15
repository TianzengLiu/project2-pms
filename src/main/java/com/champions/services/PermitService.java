package com.champions.services;

import java.util.List;

import com.champions.models.Permit;

public interface PermitService {

	public List<Permit> findAll();
	
	public List<Permit> findByParkingSpot(String parkingSpot);
	
	public List<Permit> findByVehicleLicense(String vehicleLicense);
	
	public Permit save(Permit permit);
}
