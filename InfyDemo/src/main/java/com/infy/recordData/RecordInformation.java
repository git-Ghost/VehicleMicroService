package com.infy.recordData;

import com.infy.models.Vehicle;

public class RecordInformation {

	private Long id;
	private boolean isInserted;
	private Vehicle vehicle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isInserted() {
		return isInserted;
	}
	public void setInserted(boolean isInserted) {
		this.isInserted = isInserted;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Override
	public String toString() {
		return "RecordInformation [id=" + id + ", isInserted=" + isInserted + ", vehicle=" + vehicle + "]";
	}
}
