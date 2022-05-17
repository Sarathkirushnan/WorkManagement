package com.management.app.response.dto;

import com.management.app.utils.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private Gender gender;
	private String address;
	private String email;
	private String device;
}
