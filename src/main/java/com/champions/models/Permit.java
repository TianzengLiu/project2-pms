package com.champions.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permits")
public class Permit {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="permit_id")
	private int permitId;
	
	private String parkingSpot;
	
	private String vehicleLicense;
	
	private long initialDate;
	
	private long expiryDate;

	public Permit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permit(int permitId, String parkingSpot, String vehicleLicense, long initialDate, long expiryDate) {
		super();
		this.permitId = permitId;
		this.parkingSpot = parkingSpot;
		this.vehicleLicense = vehicleLicense;
		this.initialDate = initialDate;
		this.expiryDate = expiryDate;
	}

	public int getPermitId() {
		return permitId;
	}

	public void setPermitId(int permitId) {
		this.permitId = permitId;
	}

	public String getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(String parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}

	public long getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(long initialDate) {
		this.initialDate = initialDate;
	}

	public long getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(long expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Permit [permitId=" + permitId + ", parkingSpot=" + parkingSpot + ", vehicleLicense=" + vehicleLicense
				+ ", initialDate=" + initialDate + ", expiryDate=" + expiryDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (expiryDate ^ (expiryDate >>> 32));
		result = prime * result + (int) (initialDate ^ (initialDate >>> 32));
		result = prime * result + ((parkingSpot == null) ? 0 : parkingSpot.hashCode());
		result = prime * result + permitId;
		result = prime * result + ((vehicleLicense == null) ? 0 : vehicleLicense.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permit other = (Permit) obj;
		if (expiryDate != other.expiryDate)
			return false;
		if (initialDate != other.initialDate)
			return false;
		if (parkingSpot == null) {
			if (other.parkingSpot != null)
				return false;
		} else if (!parkingSpot.equals(other.parkingSpot))
			return false;
		if (permitId != other.permitId)
			return false;
		if (vehicleLicense == null) {
			if (other.vehicleLicense != null)
				return false;
		} else if (!vehicleLicense.equals(other.vehicleLicense))
			return false;
		return true;
	}
	
}
