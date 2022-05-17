package com.management.app.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "workplace")
@Getter
@Setter
public class Workplace extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String contactNumber;
	private String email;
}
