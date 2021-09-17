package com.codeonist.ems.rest.controllers;
/**
 * 
 * @author Sourabh Sarkar
 * Date - 13-AUG-2021
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeonist.ems.beans.Employee;
import com.codeonist.ems.rest.constants.UriConstants;
import com.codeonist.ems.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(UriConstants.API_VERSION_1)
public class EmployeeRestController {

	Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;

	//Getting all the records
	@GetMapping(UriConstants.ALL_EMPLOYEES)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		try {
			employeeService.findAllEmployees().forEach(employees::add);
			return employees.size() >= 1 ? new ResponseEntity<>(employees, HttpStatus.OK)
					: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.info("Couldn't return anything as " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//Getting few records
	@RequestMapping(UriConstants.FEW_EMPLOYEES)
	public List<Employee> getFirstTenEmployees() {
		return employeeService.findFirstTenEmployees().toList();
	}

	//Getting single record
	@RequestMapping(UriConstants.SINGLE_EMPLOYEE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		logger.debug("Getting Single Employee with ID - " + id);
		Optional<Employee> employeeData = Optional.ofNullable(employeeService.findByEmployeeId(id));
		return employeeData.isPresent() ? new ResponseEntity<>(employeeData.get(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//Creating new record
	@PostMapping(UriConstants.CREATE_NEW_EMPLOYEES)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			employeeService.save(employee);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Couldn't create anything as " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Updating an existing record
	@PutMapping(UriConstants.SINGLE_EMPLOYEE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = Optional.ofNullable(employeeService.findByEmployeeId(id));

		if (employeeData.isPresent()) {
			Employee _employee = employeeData.get();
			_employee.setName(employee.getName());
			_employee.setPosition(employee.getPosition());
			_employee.setAge(employee.getAge());
			_employee.setOffice(employee.getOffice());
			_employee.setStartDate(employee.getStartDate());
			_employee.setSalary(employee.getSalary());
			return new ResponseEntity<>(employeeService.save(_employee), HttpStatus.OK);
		} else {
			logger.info("Couldn't find the record in database with ID - " + employee.getId());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Deleting an existing record
	@DeleteMapping(UriConstants.SINGLE_EMPLOYEE)
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
		Optional<Employee> rec = Optional.ofNullable(employeeService.findByEmployeeId(id));
		try {
			employeeService.delete(rec.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Bad request as " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//Deleting all the records
	@DeleteMapping(UriConstants.ALL_EMPLOYEES)
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			employeeService.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Bad request as " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
