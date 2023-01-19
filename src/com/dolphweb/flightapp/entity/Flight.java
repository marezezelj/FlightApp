package com.dolphweb.flightapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="flights")
public class Flight {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="code")
	private int code;
	
	@Column(name="company")
	private String company;
	
	@Column(name="start_city")
	private String originCity;
	
	@Column(name="destination")
	private String destinationCity;
	
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="passengers")
	private int passengerNumber;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="flight", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Passanger> passangers;
	
	public Flight() {
		
	}

	public Flight(int code, String company, String originCity, String destinationCity, int capacity) {
		this.code = code;
		this.company = company;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", code=" + code + ", company=" + company + ", originCity=" + originCity
				+ ", destinationCity=" + destinationCity + ", capacity=" + capacity + ", passengerNumber="
				+ passengerNumber + "]";
	}

	public List<Passanger> getPassangers() {
		return passangers;
	}

	public void setPassangers(List<Passanger> passangers) {
		this.passangers = passangers;
	}
	
	public void addPassanger(Passanger p) {
		if (this.passangers==null) {
			this.passangers=new ArrayList<>();
		}
		
		this.passangers.add(p);
	}
	
	
}
