package com.management.app.request.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceJobRequestDto {
	private Long id;
	private Long workplaceId;
	private List<JobRequestDto> jobs;
}
