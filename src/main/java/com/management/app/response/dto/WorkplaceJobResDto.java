package com.management.app.response.dto;

import com.management.app.entitys.Workplace;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WorkplaceJobResDto {
	private Long id;
	private Workplace workplace;
	private Long workplaceId;
	private String workplaceName;
	private String workplaceAddress;
	private String workplaceContactNumber;
	private String workplaceEmail;
	private Long jobId;
	private String jobName;
}
