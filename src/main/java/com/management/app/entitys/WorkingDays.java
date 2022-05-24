package com.management.app.entitys;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.management.app.utils.JobStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "working_days")
public class WorkingDays extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "workplace_jop_id", nullable = false)
	private WorkplaceJob workplaceJop;
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	@Enumerated(EnumType.STRING)
	private JobStatus status;
	private Double incomePerHours;
}
