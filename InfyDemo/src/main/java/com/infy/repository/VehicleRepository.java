package com.infy.repository;

import java.util.Map;

import com.infy.models.Vehicle;
import com.infy.recordData.RecordInformation;

/*
 * public interface VehicleRepository extends MongoRepository<Vechicle, String>{
 * Vechicle getVechicleDetails(String vin); }
 */

public interface VehicleRepository {
	Vehicle getVehicleDetails(String vin);
	RecordInformation insertVehicle(Vehicle vh);
	void updateVehicleDetails(String vin,Map<String,Object> data);
}
