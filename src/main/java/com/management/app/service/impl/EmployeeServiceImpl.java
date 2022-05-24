package com.management.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Employee;
import com.management.app.repositories.EmployeeRepository;
import com.management.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Employee> getByEmployee(Long id) {
		return  employeeRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public boolean isEmployeeIdExsist(Long id) {
		return employeeRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean existByMobile(String mobileNumber) {
		return employeeRepository.existsByMobileNumber(mobileNumber);
	}

	@Transactional(readOnly = true)
	public boolean existByEmail(String email) {
		return employeeRepository.existsByEmail(email);
	}

	@Transactional(readOnly = true)
	public boolean existByUpdateMobile(Long id, String mobileNumber) {
		return employeeRepository.existsByMobileNumberAndIdNot(mobileNumber,id);
	}

	@Transactional(readOnly = true)
	public boolean existByUpdateEmail(Long id, String email) {
		return employeeRepository.existsByEmailAndIdNot(email,id);
	}

	@Transactional(readOnly = true)
	public boolean existByDevice(String device) {
		return employeeRepository.existsByDevice(device);
	}

	@Transactional(readOnly = true)
	public Optional<Employee> getByEmployeeEmail(String email){
		return employeeRepository.findByEmailIgnoreCase(email);
	}
}
