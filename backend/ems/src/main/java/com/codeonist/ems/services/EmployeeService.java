package com.codeonist.ems.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeonist.ems.beans.Employee;
import com.codeonist.ems.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public Page<Employee> findFirstTenEmployees() {
		return employeeRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id")));
	}

	public Employee findByEmployeeId(Long id) {
		return employeeRepository.findByEmployeeId(id);
	}

	public List<Employee> findByNameContaining(String name) {
		return employeeRepository.findByNameContaining(name);
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteById(long id) {
		employeeRepository.deleteById(id);
	}

	public void deleteAll() {
		employeeRepository.deleteAll();
	}

	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

}
