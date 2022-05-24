package com.management.app.request.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InOutTimeRequestDto {
	private Long id;
	private Date date;
	private Timestamp inTime;
	private Timestamp outTime;
	private Long workingDaysId;
}
