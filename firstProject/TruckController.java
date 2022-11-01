package com.onepoint.enseirb.firstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/truck")
public class TruckController {
	@Autowired
	TruckRepository truckRepository;

	@GetMapping
	public Truck greet() {
		return new Truck("78-12-30", "Mercedes", "black");
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Truck getById(@PathVariable Long id) {
		try {
		 return truckRepository.getById(id);}
		catch (Exception e) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "truck not found"
					);
		}
	
	}

}
