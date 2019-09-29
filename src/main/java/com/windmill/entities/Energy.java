package com.windmill.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Energy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(min = 16, max = 16)
	@NotNull
	@NotEmpty
	@Column(name = "windmill_id")
	private String windmillId;

	@Column(name = "electricity_generated")
	private double electricityGenerated;

	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWindmillId() {
		return windmillId;
	}

	public void setWindmillId(String windmillId) {
		this.windmillId = windmillId;
	}

	public double getElectricityGenerated() {
		return electricityGenerated;
	}

	public void setElectricityGenerated(double electricityGenerated) {
		this.electricityGenerated = electricityGenerated;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
