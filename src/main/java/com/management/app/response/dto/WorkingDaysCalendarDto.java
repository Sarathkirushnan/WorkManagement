package com.management.app.response.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkingDaysCalendarDto {
	private String title;
	private Date start;
	private Date end;
	private String constraint;
}
