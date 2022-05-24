package com.management.app.request.dto;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.management.app.entitys.Employee;
import com.management.app.entitys.WorkplaceJob;
import com.management.app.utils.JobStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkingDaysReqDto {
	private Long id;
	private Date startDate;
	private Date endDate;
	private Long workplaceAndJopId;
	private Long employeeId;
	private JobStatus status;
	private Double incomePerHours;
}
