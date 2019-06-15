package com.champions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champions.models.Permit;
import com.champions.repositories.PermitDao;

@Service
public class PermitServiceImpl implements PermitService {

	private PermitDao permitDao;
	
	@Autowired
	public PermitServiceImpl(PermitDao pd) {
		
		this.permitDao = pd;
	}
	
	@Override
	public List<Permit> findAll() {
		
		return permitDao.findAll();
	}

	@Override
	public List<Permit> findByParkingSpot(String parkingSpot) {
		
		return permitDao.findByParkingSpot(parkingSpot);
	}

	@Override
	public List<Permit> findByVehicleLicense(String vehicleLicense) {
		
		return permitDao.findByVehicleLicense(vehicleLicense);
	}

	@Override
	public Permit save(Permit permit) {

		return permitDao.save(permit);
	}

}
