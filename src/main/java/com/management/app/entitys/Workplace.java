package com.management.app.entitys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "workplace")
@Getter
@Setter
@ToString
public class Workplace extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String contactNumber;
	private String email;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workplace", cascade = CascadeType.MERGE)
	 @JsonIgnoreProperties("vehicle")
	private List<WorkplaceJob> workplaceJobs;
}
