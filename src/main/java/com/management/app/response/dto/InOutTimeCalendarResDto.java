package com.management.app.response.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InOutTimeCalendarResDto {
	private String title;
	private Timestamp start;
	private Timestamp end;
	private String constraint;
}
