package com.windmill.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Windmill {
	@Id
	@Length(min=16, max=16)
	@NotNull
	@NotEmpty
    private String id;
	
	private String name;
	private String address;
	private double lat;
	private double lon;
	
	public Windmill(String id, String name, String address, double lat,
			double lon) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
	}
	
	
	public Windmill() {
		super();
	}


	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
