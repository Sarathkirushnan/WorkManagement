package com.management.app.service;

import java.util.List;
import java.util.Optional;

import com.management.app.entitys.Employee;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Optional<Employee> getByEmployee(Long id);

	boolean isEmployeeIdExsist(Long id);

	boolean existByMobile(String mobileNumber);

	boolean existByEmail(String email);

	boolean existByUpdateMobile(Long id, String mobileNumber);

	boolean existByUpdateEmail(Long id, String email);

	boolean existByDevice(String device);

}
