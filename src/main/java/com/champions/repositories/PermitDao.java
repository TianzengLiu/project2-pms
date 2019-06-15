package com.champions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.champions.models.Permit;

public interface PermitDao extends JpaRepository<Permit, Integer> {

	public List<Permit> findByParkingSpot(String parkingSpot);
	
	public List<Permit> findByVehicleLicense(String vehicleLicense);
	
	// get by dates necessary?
}
