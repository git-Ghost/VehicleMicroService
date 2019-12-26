package com.infy.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.infy.models.Vehicle;
import com.infy.recordData.RecordInformation;
import com.infy.repository.VehicleRepository;

public class VehicleRepositoryImpl implements VehicleRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RecordInformation recordInformation;
	
	@Override
	public Vehicle getVehicleDetails(String vin) {
		Query query = new Query(Criteria.where("vin").is(vin));
		return mongoTemplate.findOne(query, Vehicle.class);
	}

	@Override
	public RecordInformation insertVehicle(Vehicle vh) {
		Vehicle vehiclePresent = this.getVehicleDetails(vh.getVin());
		if (vehiclePresent==null) {
			mongoTemplate.insert(vh);
			recordInformation.setId(vh.getId());
			recordInformation.setInserted(true);
			recordInformation.setVehicle(vh);
			return recordInformation;
		}
		recordInformation.setId(vehiclePresent.getId());
		recordInformation.setVehicle(vehiclePresent);
		return recordInformation;
	}

	@Override
	public void updateVehicleDetails(String vin,Map<String,Object> data) {
		Query query = new Query();
		query.addCriteria(Criteria.where("vin").is(vin));
		query.fields().include("vin");
		//Iteration through each configured map value
		Update update = new Update();
		for (String str : data.keySet()) {
			update.set(str, data.get(str));
		}
		mongoTemplate.updateFirst(query, update, Vehicle.class);
	}	
}
