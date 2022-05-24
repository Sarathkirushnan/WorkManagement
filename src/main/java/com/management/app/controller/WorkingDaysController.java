package com.management.app.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.app.entitys.Employee;
import com.management.app.entitys.InOutTime;
import com.management.app.entitys.WorkingDays;
import com.management.app.entitys.Workplace;
import com.management.app.entitys.WorkplaceJob;
import com.management.app.request.dto.WorkingDaysReqDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.InOutTimeCalendarResDto;
import com.management.app.response.dto.WorkingDaysCalendarDto;
import com.management.app.response.dto.WorkingDaysDropResDto;
import com.management.app.response.dto.WorkplaceJobResDto;
import com.management.app.service.WorkingDaysService;

@CrossOrigin
@RestController
public class WorkingDaysController {
	@Autowired
	private WorkingDaysService workingDaysService;

	@PostMapping("/api/v1/working-days")
	public ResponseEntity<BaseResponse> addWorkignDays(@RequestBody WorkingDaysReqDto workingDaysReqDto) {
		if (!workingDaysService.isVelidDateReng(workingDaysReqDto.getWorkplaceAndJopId(),
				workingDaysReqDto.getEmployeeId(), workingDaysReqDto.getStartDate(), workingDaysReqDto.getEndDate())) {
			return ResponseEntity.badRequest()
					.body(new BaseResponse("error", "This work already allocated for this time period"));
		}

		WorkingDays workingDays = new WorkingDays();
		BeanUtils.copyProperties(workingDaysReqDto, workingDays, "id");
		Employee employee = new Employee();
		employee.setId(workingDaysReqDto.getEmployeeId());
		workingDays.setEmployee(employee);
		WorkplaceJob workplaceAndJop = new WorkplaceJob();
		workplaceAndJop.setId(workingDaysReqDto.getWorkplaceAndJopId());
		workingDays.setWorkplaceJop(workplaceAndJop);
		workingDaysService.saveWorkingDays(workingDays);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace saved successfully"));
	}

	@GetMapping(value = "/api/v1/working-days/employee/{id}")
	public ResponseEntity<Object> getWorkdaysByEmployeeId(@PathVariable Long id) {
		List<WorkingDays> workingDays = workingDaysService.getWorkdaysByEmployeeId(id);
		List<WorkingDaysCalendarDto> responseDtos = new ArrayList<WorkingDaysCalendarDto>();
		for (WorkingDays workingDay : workingDays) {
			WorkingDaysCalendarDto responseDto = new WorkingDaysCalendarDto();
			responseDto.setTitle(workingDay.getWorkplaceJop().getJob().getName() + " ("
					+ workingDay.getWorkplaceJop().getWorkplace().getName() + "), Per hours: $ "
					+ workingDay.getIncomePerHours());
			responseDto.setStart(workingDay.getStartDate());
			responseDto.setEnd(workingDay.getEndDate());
			responseDtos.add(responseDto);
		}

		return ResponseEntity.ok(new CommenResponse<List<WorkingDaysCalendarDto>>("workingDasys", responseDtos,
				"success", "Workplace job get successfully"));
	}
	
	@GetMapping(value = "/api/v1/working-days/employee/{id}/date/{date}")
	public ResponseEntity<BaseResponse> getInOutTimeByEmployeeIdAndDate(@PathVariable Long id,@PathVariable Date date) {
		List<WorkingDays> workingDays = workingDaysService.getByEmployeeIdAndDate(id,date);
		List<WorkingDaysDropResDto> responseDtos = new ArrayList<>();
		for (WorkingDays workingDay : workingDays) {
			WorkingDaysDropResDto responseDto = new WorkingDaysDropResDto();
			responseDto.setName(workingDay.getWorkplaceJop().getJob().getName() + " ("
					+ workingDay.getWorkplaceJop().getWorkplace().getName() + "), Per hours: $ "
					+ workingDay.getIncomePerHours());
			responseDto.setId(workingDay.getId());
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<WorkingDaysDropResDto>>("workingDays", responseDtos, "success",
				"In out times are get successfully"));
	}
}
