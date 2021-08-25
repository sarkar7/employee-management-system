package com.codeonist.ems.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codeonist.ems.beans.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select e from Employee e where e.id = ?1")
	public Employee findByEmployeeId(Long id);

	public List<Employee> findByNameContaining(String name);

}
