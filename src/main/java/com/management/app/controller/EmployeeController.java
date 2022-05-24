package com.management.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.app.entitys.Employee;
import com.management.app.request.dto.EmployeeRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.EmployeeResDto;
import com.management.app.service.EmployeeService;

@CrossOrigin
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/api/v1/employee")
	public ResponseEntity<BaseResponse> addEmployee(@RequestBody EmployeeRequestDto employeeDto,
			HttpServletRequest request) {
		String device = request.getHeader("User-Agent");
		if (employeeService.existByDevice(device)) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This device already have employee"));
		}
		if (employeeService.existByMobile(employeeDto.getMobileNumber())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee mobile number already exist"));
		}
		if (employeeService.existByEmail(employeeDto.getEmail())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee email id already exist"));
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employee.setDevice(device);
		employee=employeeService.saveEmployee(employee);
		EmployeeResDto responseDto = new EmployeeResDto();
		BeanUtils.copyProperties(employee, responseDto);
		return ResponseEntity.ok(new CommenResponse<EmployeeResDto>("employee",responseDto,"success", "Employee saved successfully"));
	}

	@GetMapping(value = "/api/v1/employee")
	public ResponseEntity<BaseResponse> getEmployees() {
		List<Employee> employees = employeeService.getAllEmployee();
		List<EmployeeResDto> responseDtos = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeResDto responseDto = new EmployeeResDto();
			BeanUtils.copyProperties(employee, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<EmployeeResDto>>("employees", responseDtos, "success",
				"Employees get successfully"));
	}

	@GetMapping(value = "/api/v1/employee/{id}")
	public ResponseEntity<Object> getByEmployeeId(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.getByEmployee(id);
		if (!employee.isPresent()) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee not fount"));
		}
		EmployeeResDto responseDto = new EmployeeResDto();
		BeanUtils.copyProperties(employee.get(), responseDto);
		return ResponseEntity.ok(
				new CommenResponse<EmployeeResDto>("employee", responseDto, "success", "Employee get successfully"));
	}

	@PutMapping(value = "/api/v1/employee")
	public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeRequestDto employeeDto,
			HttpServletRequest request) {
		String device = request.getHeader("User-Agent");
		Optional<Employee> employeeDb = employeeService.getByEmployee(employeeDto.getId());
		if (!employeeDb.isPresent()) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee not fount"));
		}
		if (!employeeDb.get().getDevice().equals(device) && employeeService.existByDevice(device)) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This employee not using this device"));
		}
		if (employeeService.existByUpdateMobile(employeeDto.getId(), employeeDto.getMobileNumber())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee mobile number already exist"));
		}
		if (employeeService.existByUpdateEmail(employeeDto.getId(), employeeDto.getEmail())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "Employee email id already exist"));
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employee.setDevice(employeeDb.get().getDevice());
		employee=employeeService.saveEmployee(employee);
		EmployeeResDto responseDto = new EmployeeResDto();
		BeanUtils.copyProperties(employee, responseDto);
		return ResponseEntity.ok(new CommenResponse<EmployeeResDto>("employee",responseDto,"success", "Employee updated successfully"));
	}
	
	@GetMapping(value = "/api/v1/employee/email/{email}")
	public ResponseEntity<Object> getByEmployeEmail(@PathVariable String email,HttpServletRequest request) {
		Optional<Employee> employee = employeeService.getByEmployeeEmail(email);
		if (!employee.isPresent()) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "User not fount"));
		}
		String device = request.getHeader("User-Agent");
		if(!employee.get().getDevice().equals(device)){
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This user not velid for this device"));
		}
		EmployeeResDto responseDto = new EmployeeResDto();
		BeanUtils.copyProperties(employee.get(), responseDto);
		return ResponseEntity.ok(
				new CommenResponse<EmployeeResDto>("employee", responseDto, "success", "Employee get successfully"));
	}
}
