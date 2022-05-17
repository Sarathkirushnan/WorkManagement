package com.management.app.entitys;

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
@Table(name = "workplace_jop")
@Getter
@Setter
public class WorkplaceJop extends DateAudit{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "workplace_id", nullable = false)
	private Workplace workplace;
	@ManyToOne
	@JoinColumn(name = "jop_id", nullable = false)
	private Job job;
	private Double incomePerHours;
}
