package com.management.app.entitys;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.management.app.utils.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Employee extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String address;
	private String email;
	private String device;

}
