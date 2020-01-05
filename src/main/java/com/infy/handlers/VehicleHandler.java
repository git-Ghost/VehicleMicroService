package com.infy.handlers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.infy.locales.GenericReponse;
import com.infy.locales.GetTotalTaxResponse;
import com.infy.locales.VehicleInsertRequest;
import com.infy.locales.VehicleUpdateRequest;
import com.infy.locales.entryCreationRes.EntryCreationResponse;
import com.infy.models.Vehicle;
import com.infy.recordData.RecordInformation;
import com.infy.repository.VehicleRepository;
import com.infy.utilites.ResponseHelper;

@RestController
@RequestMapping("/")
public class VehicleHandler {

	@Autowired
	VehicleRepository repository;

	@Autowired
	Gson gson;

	@Autowired
	GenericReponse res;

	@Autowired
	EntryCreationResponse entryCreationResponse;
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	@PostMapping(value = "/add", produces = { "application/JSON" })
	public @ResponseBody ResponseEntity<?> insertDetails(@Valid @RequestBody VehicleInsertRequest vechicleInfo) {
		// Validation Checks
		if (vechicleInfo.getCountryCode().trim().length() != 3) {
			return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST, "invalid country code",
					HttpStatus.BAD_REQUEST);
		}
		Vehicle vdata = gson.fromJson(gson.toJson(vechicleInfo), Vehicle.class);
		// Decimal value precison save value//

		vdata.setId(); // To set current date and time as id
		RecordInformation rslt = repository.insertVehicle(vdata);
		if (!rslt.isInserted()) {
			return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_CONFLICT, "vin already exists",
					HttpStatus.CONFLICT);
		}
		return ResponseHelper.setCreationResponse(entryCreationResponse, HttpServletResponse.SC_CREATED,
				"entity created", HttpStatus.CREATED, rslt.getId());
	}

	@GetMapping(value = "/getTax/{vin}", produces = { "application/JSON" })
	public @ResponseBody ResponseEntity<?> getTax(@PathVariable String vin) {
		if (vin.length() != 17) {
			return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST, "invalid vin",
					HttpStatus.BAD_REQUEST);
		} else {
			Vehicle vData = repository.getVehicleDetails(vin);
			if (vData.getVin() != null) {
				//Calculation of tax in bigDecimal and setting precision to 2
				GetTotalTaxResponse tax = new GetTotalTaxResponse();
				tax.setSGST(vData.getAmount().multiply(new BigDecimal(18)).divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_UNNECESSARY));
				tax.setCGST(vData.getAmount().multiply(new BigDecimal(40)).divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_UNNECESSARY));
				tax.setTotalTax();
				tax.setCommison();
				return new ResponseEntity<GetTotalTaxResponse>(tax, HttpStatus.OK);
			}else {
				return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_NOT_FOUND, "vin not found", HttpStatus.NOT_FOUND);
			}
		}
	}

	@PutMapping(path = "/update/{vin}")
	public ResponseEntity<?> updateDetails(@PathVariable String vin, @Valid @RequestBody VehicleUpdateRequest reqBody) {
		if (vin.length() != 17) {
			return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST, "invalid vin",
					HttpStatus.BAD_REQUEST);
		} else {
			Vehicle vdetails = repository.getVehicleDetails(vin);
			if (vdetails != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				// Validation checks for the fields
				if (reqBody.getBrand().trim().length() >= 1) {
					data.put("brand", reqBody.getBrand());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST,
							"invalid brand name", HttpStatus.BAD_REQUEST);
				}
				if (reqBody.getModel().trim().length() >= 1) {
					data.put("model", reqBody.getModel());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST, "invalid model",
							HttpStatus.BAD_REQUEST);
				}

				if (reqBody.getColor().trim().length() >= 1) {
					data.put("color", reqBody.getColor());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST,
							"invalid color provided", HttpStatus.BAD_REQUEST);
				}
				if (reqBody.getModel().trim().length() >= 1) {
					data.put("modelCode", reqBody.getModelCode());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST,
							"invalid model code", HttpStatus.BAD_REQUEST);
				}
				if (reqBody.getType().trim().length() == 1) {
					data.put("type", reqBody.getType());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST,
							"invalid vehicle type", HttpStatus.BAD_REQUEST);
				}
				if (reqBody.getCountryCode().trim().length() == 3) {
					data.put("countryCode", reqBody.getCountryCode());
				} else {
					return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_BAD_REQUEST,
							"invalid country code", HttpStatus.BAD_REQUEST);
				}

				data.put("year", reqBody.getYear());
				data.put("isActive", reqBody.getIsActive());
				data.put("isConnected", reqBody.getIsConnected());
				// Calling Repo for update
				repository.updateVehicleDetails(vin, data);
				return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_OK, "record updated successfully",
						HttpStatus.OK);
			} else {
				return ResponseHelper.setGenericResponse(res, HttpServletResponse.SC_NOT_FOUND,
						"vehicle details for vin", HttpStatus.NOT_FOUND);
			}
		}
	}
}
