package com.infy.appBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.google.gson.Gson;
import com.infy.dao.VehicleRepositoryImpl;
import com.infy.locales.GenericReponse;
import com.infy.locales.entryCreationRes.EntryCreationResponse;
import com.infy.recordData.RecordInformation;
import com.infy.repository.VehicleRepository;

@Configuration
public class AppBeans {

	@Bean(name = "vehicleRepository")
	VehicleRepository getVehicleRepository() {
		return new VehicleRepositoryImpl();
	}

	@Bean(name = "genericError")
	GenericReponse getGenericResponse() {
		return new GenericReponse();
	}

	@Bean(name = "entryCreate")
	EntryCreationResponse getEntryCreationResponse() {
		return new EntryCreationResponse();
	}

	@Bean
	RecordInformation getRecordInformation() {
		return new RecordInformation();
	}

	@Bean
	Gson getGson() {
		return new Gson();
	}

	@Autowired
	MongoDbFactory mongoDbFactory;
	@Autowired
	MongoMappingContext mongoMappingContext;

	@Bean
	public MappingMongoConverter mappingMongoConverter() {

		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		return converter;
	}
}
