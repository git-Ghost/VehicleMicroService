package com.infy.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
@Document(collection = "vehicle")
public class Vehicle {

	@Id
	private Long id;
	
	@NonNull
	private String vin;
	@NonNull
	private String brand;
	@NonNull
	private String model;
	@NonNull
	private int year;
	@NonNull
	private String color;
	@NonNull
	private String modelCode;
	@NonNull
	private String type;
	@NonNull
	private String countryCode;
	@NonNull
	private boolean isConnected;
	@NonNull
	private boolean isActive;

	public Long getId() {
		return id;
	}
	
	//Will the set id as yyMMddhhmmssMs format
	public void setId() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		this.id = Long.parseLong(ft.format(dNow));
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vin=" + vin + ", brand=" + brand + ", model=" + model + ", year=" + year
				+ ", color=" + color + ", modelCode=" + modelCode + ", type=" + type + ", countryCode=" + countryCode
				+ ", isConnected=" + isConnected + ", isActive=" + isActive + "]";
	}
}
