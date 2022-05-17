package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmailAndIdNot(String email, Long id);

	boolean existsByMobileNumberAndIdNot(String mobileNumber, Long id);

	boolean existsByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);

	boolean existsByDevice(String device);

	Employee findByDevice(String device);
	
}
