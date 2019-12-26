package com.infy.handlersTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.infy.handlers.VehicleHandler;
import com.infy.locales.GenericReponse;
import com.infy.locales.entryCreationRes.EntryCreationResponse;
import com.infy.models.Vehicle;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VehicleHandler.class)
public class VehicleHandlerTest {

	@MockBean
	private VehicleHandler vehicleHandler;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Vehicle newVehicle;
	
	@Autowired
	private EntryCreationResponse entryCreationRes;

	@Autowired
	private GenericReponse res;
	
	@Before
	void init() {
		newVehicle.setActive(false);
		newVehicle.setConnected(false);
		newVehicle.setCountryCode("NYZ");
		newVehicle.setType("A");
		newVehicle.setId();
		newVehicle.setVin("1J4FY49S3PP254747");
		newVehicle.setBrand("BMW");
		newVehicle.setModel("1.5L Convertable");
		newVehicle.setYear(2003);
		newVehicle.setColor("Vintage Blue");
		newVehicle.setModelCode("180MA");
	}
	
	@Test
	public void createVechicleEntry() {
		entryCreationRes.setCode(HttpServletResponse.SC_CREATED);
		entryCreationRes.setMsg("entity created");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		entryCreationRes.setData(Long.parseLong(ft.format(dNow)));
		
	}

}
