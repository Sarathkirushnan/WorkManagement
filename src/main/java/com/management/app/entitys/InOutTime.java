package com.management.app.entitys;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "in_out_time")
@Getter
@Setter
public class InOutTime extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private Timestamp inTime;
	private Timestamp outTime;
	@ManyToOne
	@JoinColumn(name = "working_days_id", nullable = false)
	private WorkingDays workingDays;
}
