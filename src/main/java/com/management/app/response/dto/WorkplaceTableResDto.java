package com.management.app.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkplaceTableResDto {
private Long id;
private String name;
private String contactNumber;
private List<JobTableResDto> jobs;
}
