package com.infy.locales;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "brand", "model", "year", "color", "modelCode", "type", "countryCode", "isConnected",
		"isActive" })
public class VehicleUpdateRequest {

	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("model")
	private String model;
	
	@JsonProperty("year")
	@Min(1)
	private Integer year;
	
	@JsonProperty("color")
	private String color;
	@JsonProperty("modelCode")
	private String modelCode;
	@JsonProperty("type")
	private String type;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("isConnected")
	private Boolean isConnected;
	@JsonProperty("isActive")
	private Boolean isActive;

	@JsonProperty("brand")
	public String getBrand() {
		return brand;
	}

	@JsonProperty("brand")
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@JsonProperty("model")
	public String getModel() {
		return model;
	}

	@JsonProperty("model")
	public void setModel(String model) {
		this.model = model;
	}

	@JsonProperty("year")
	public Integer getYear() {
		return year;
	}

	@JsonProperty("year")
	public void setYear(Integer year) {
		this.year = year;
	}

	@JsonProperty("color")
	public String getColor() {
		return color;
	}

	@JsonProperty("color")
	public void setColor(String color) {
		this.color = color;
	}

	@JsonProperty("modelCode")
	public String getModelCode() {
		return modelCode;
	}

	@JsonProperty("modelCode")
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("isConnected")
	public Boolean getIsConnected() {
		return isConnected;
	}

	@JsonProperty("isConnected")
	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	@JsonProperty("isActive")
	public Boolean getIsActive() {
		return isActive;
	}

	@JsonProperty("isActive")
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}