package com.codeonist.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeonist.ems.beans.Employee;
import com.codeonist.ems.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public String getAllEmployees(ModelMap model) {
		List<Employee> list = employeeService.findAllEmployees();
		model.addAttribute("empList", list);
		return "employee";
	}

}
