package com.codeonist.ems.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeonist.ems.beans.Employee;
import com.codeonist.ems.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String name) {
		try {
			List<Employee> employees = new ArrayList<>();
			if (name == null) {
				employeeService.findAllEmployees().forEach(employees::add);
			} else {
				employeeService.findByNameContaining(name).forEach(employees::add);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/TenEmployees")
	public List<Employee> getFirstTenEmployees() {
		return employeeService.findFirstTenEmployees().toList();
	}

	@RequestMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Optional<Employee> employeeData = Optional.ofNullable(employeeService.findByEmployeeId(id));
		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee _employee = employeeService.save(new Employee(employee.getName(), employee.getPosition(),
					employee.getOffice(), employee.getAge(), employee.getStartDate(), employee.getSalary()));
			return new ResponseEntity<>(_employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/employees/{id}")
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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
		try {
			employeeService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			employeeService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
