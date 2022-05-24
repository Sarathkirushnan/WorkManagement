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

import com.management.app.entitys.InOutTime;
import com.management.app.entitys.Job;
import com.management.app.entitys.WorkingDays;
import com.management.app.request.dto.InOutTimeRequestDto;
import com.management.app.request.dto.JobRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.InOutTimeCalendarResDto;
import com.management.app.response.dto.JobResDto;
import com.management.app.service.InOutTimeService;
import com.management.app.service.JobService;
import com.management.app.service.WorkingDaysService;

@CrossOrigin
@RestController
public class InOutTimeController {
	@Autowired
	private InOutTimeService inOutTimeService;
	@Autowired
	private WorkingDaysService workingDaysService;

	@PostMapping(value = "/api/v1/in-out-time")
	public ResponseEntity<BaseResponse> saveInOutTime(@RequestBody InOutTimeRequestDto outTimeRequestDto) {
		if (!workingDaysService.existByWorkingDaysId(outTimeRequestDto.getWorkingDaysId())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "selected job not exist"));
		}
		if (!inOutTimeService.isVelidWorkingTimeId(outTimeRequestDto.getWorkingDaysId(), outTimeRequestDto.getInTime(),
				outTimeRequestDto.getOutTime(), outTimeRequestDto.getDate())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This time periout you have sheduled"));
		}
		InOutTime inOutTime = new InOutTime();
		BeanUtils.copyProperties(outTimeRequestDto, inOutTime, "id");
		WorkingDays workingDays = new WorkingDays();
		workingDays.setId(outTimeRequestDto.getWorkingDaysId());
		inOutTime.setWorkingDays(workingDays);
		InOutTime savedInOutTime = inOutTimeService.saveInOutTime(inOutTime);
		return ResponseEntity.ok(new BaseResponse("success", "Time saved successfully"));
	}

	@GetMapping(value = "/api/v1/in-out-time/employee/{id}")
	public ResponseEntity<BaseResponse> getInOutTimeByEmployeeId(@PathVariable Long id) {
		List<InOutTime> inOutTimes = inOutTimeService.getInOutTimeByEmployeeId(id);
		List<InOutTimeCalendarResDto> responseDtos = new ArrayList<>();
		for (InOutTime inOutTime : inOutTimes) {
			InOutTimeCalendarResDto responseDto = new InOutTimeCalendarResDto();
			responseDto.setTitle(inOutTime.getWorkingDays().getWorkplaceJop().getJob().getName() + " ("
					+ inOutTime.getWorkingDays().getWorkplaceJop().getWorkplace().getName() + ") Per Hours pricing : $"
					+ inOutTime.getWorkingDays().getIncomePerHours());
			responseDto.setStart(inOutTime.getInTime());
			responseDto.setEnd(inOutTime.getOutTime());
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<InOutTimeCalendarResDto>>("inOutTime", responseDtos, "success",
				"In out times are get successfully"));
	}
	@GetMapping(value = "/api/v1/in-out-time/employee/{id}/date/{date}")
	public ResponseEntity<BaseResponse> getInOutTimeByEmployeeIdAndDate(@PathVariable Long id,@PathVariable Date date) {
		List<InOutTime> inOutTimes = inOutTimeService.getInOutTimeByEmployeeIdAndDate(id,date);
		List<InOutTimeCalendarResDto> responseDtos = new ArrayList<>();
		for (InOutTime inOutTime : inOutTimes) {
			InOutTimeCalendarResDto responseDto = new InOutTimeCalendarResDto();
			responseDto.setTitle(inOutTime.getWorkingDays().getWorkplaceJop().getJob().getName() + " ("
					+ inOutTime.getWorkingDays().getWorkplaceJop().getWorkplace().getName() + ") Per Hours pricing : $"
					+ inOutTime.getWorkingDays().getIncomePerHours());
			responseDto.setStart(inOutTime.getInTime());
			responseDto.setEnd(inOutTime.getOutTime());
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<InOutTimeCalendarResDto>>("inOutTime", responseDtos, "success",
				"In out times are get successfully"));
	}
}
