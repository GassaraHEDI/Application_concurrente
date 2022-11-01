package com.onepoint.enseirb.firstProject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TruckRepository {
	
	
	private Map<Long, Truck> trucks = new HashMap<>();
	public TruckRepository() {
		trucks.put(1L, new Truck("78-12-30", "Mercedes", "black"));
		trucks.put(2L, new Truck("75-24-30", "Renault", "Blue"));
		trucks.put(3L, new Truck("74-16-30", "Mercedes", "black"));
		trucks.put(4L, new Truck("79-24-30", "Mercedes", "white"));
	}
	
	public  Truck getById(Long id) {
		Truck truck = trucks.get(id);
		if (truck == null) {
		throw new IllegalArgumentException("Truck not exists");
		}
		return truck;
	}
	
}
