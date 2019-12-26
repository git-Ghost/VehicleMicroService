package com.infy.utilites;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.infy.locales.GenericReponse;
import com.infy.locales.entryCreationRes.EntryCreationResponse;

public class ResponseHelper {

	public static ResponseEntity<GenericReponse> setGenericResponse(GenericReponse res,Integer httpCode, String msg, HttpStatus httpHeaderCode) {
		res.setCode(httpCode);
		res.setMsg(msg);
		return new ResponseEntity<GenericReponse>(res, httpHeaderCode);
	}
	
	public static ResponseEntity<EntryCreationResponse> setCreationResponse(EntryCreationResponse entryCreationResponse,Integer httpCode, String msg, HttpStatus httpHeaderCode,Long creationID){
		entryCreationResponse.setCode(httpCode);
		entryCreationResponse.setMsg(msg);
		entryCreationResponse.setData(creationID);
		return new ResponseEntity<EntryCreationResponse>(entryCreationResponse, httpHeaderCode);
	}
}
