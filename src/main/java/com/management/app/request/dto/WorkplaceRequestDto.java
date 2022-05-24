package com.management.app.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceRequestDto {
	private Long id;
	private String name;
	private String address;
	private String contactNumber;
	private String email;
}
