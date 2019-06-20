package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.champions.exceptions.PermitNotFoundException;
import com.champions.models.Permit;
import com.champions.models.User;
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
	public Permit findById(Integer id) {
		
		Permit permit = null;
		
		try {
			permit = permitDao.findById(id).get();
			
		} catch(NoSuchElementException e) {
			
			String err = "PermitService failed to find permit of ID " + id;
			throw new PermitNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return permit;
	}

	@Override
	public List<Permit> findByParkingSpot(String parkingSpot) {
		
		List<Permit> permits = permitDao.findByParkingSpot(parkingSpot);
		
		if(permits.isEmpty()) {
			
			String err = "PermitService found no permits for parking spot " + parkingSpot;
			throw new PermitNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return permits;
	}

	@Override
	public List<Permit> findByVehicleLicense(String vehicleLicense) {

		List<Permit> permits = permitDao.findByVehicleLicense(vehicleLicense);
		
		if(permits.isEmpty()) {
			
			String err = "PermitService found no permits for license " + vehicleLicense;
			throw new PermitNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return permits;
	}

	@Override
	public Permit save(Permit permit) {

		return permitDao.save(permit);
	}
	
	@Override 
	public void delete(Integer id) {
		
		permitDao.deleteById(id);
	}

	@Override
	public Permit update(Map<String, Object> updates, int id) {
		
		// test output
		//updates.forEach((s, o) -> {System.out.println(s + " " + o + " " + o.getClass());});
				
		Permit permit = null;
				
		try {
			permit = permitDao.findById(id).get();
					
		} catch(NoSuchElementException e) {
					
			String err = "PermitService update method failed to find permit of ID " + id;
			throw new PermitNotFoundException(HttpStatus.NOT_FOUND, err);
		}
				
		if(permit != null) {
					
			final Permit finalPermit = permit;
			updates.forEach((s, o) -> {fillPermit(finalPermit, s, o);});
			save(permit);
		}
				
		return permit;
	}
	
	private void fillPermit(Permit p, String s, Object o) {
		
		switch(s) {
		
			case "parkingSpot":
				p.setParkingSpot((String)o);
				break;
				
			case "vehicleLicense":
				p.setVehicleLicense((String)o);
				break;
				
			case "initialDate":
				p.setInitialDate((long)o);
				break;
				
			case "expiryDate":
				p.setExpiryDate((long) o);
				break;
				
			default:
				break;
		}
	}

}
