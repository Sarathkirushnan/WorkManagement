package com.management.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.app.entitys.Job;
import com.management.app.request.dto.JobRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.JobResDto;
import com.management.app.service.JobService;

@CrossOrigin
@RestController
public class JobController {
	@Autowired
	private JobService jobService;

	@PostMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> addJop(@RequestBody JobRequestDto jobRequestDto) {
		if (jobService.existByName(jobRequestDto.getName())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop already exist"));
		}
		Job job = new Job();
		BeanUtils.copyProperties(jobRequestDto, job,"id");
		Job savedJop = jobService.saveJop(job);
		return ResponseEntity.ok(new BaseResponse("success", "jop saved successfully"));
	}
	
	@PutMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> updateJop(@RequestBody JobRequestDto jobRequestDto) {
		if (!jobService.existJop(jobRequestDto.getId())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop not exist"));
		}
		if (jobService.updateExistByName(jobRequestDto.getId(),jobRequestDto.getName())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop already exist"));
		}
		Job job = new Job();
		BeanUtils.copyProperties(jobRequestDto, job);
		Job savedJop = jobService.updateJop(job);
		return ResponseEntity.ok(new BaseResponse("success", "jop update successfully"));
	}
	
	@GetMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> getJops() {
		List<Job> jobs = jobService.getAllJop();
		List<JobResDto> responseDtos = new ArrayList<>();
		for (Job job : jobs) {
			JobResDto responseDto = new JobResDto();
			BeanUtils.copyProperties(job, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<JobResDto>>("jops", responseDtos, "success",
				"jops get successfully"));
	}
	
	@DeleteMapping(value = "/api/v1/jop/{id}")
	public ResponseEntity<BaseResponse> deleteJop(@PathVariable Long id) {
		if (!jobService.existJop(id)) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop not exist"));
		}
		jobService.deleteById(id);
		return ResponseEntity.ok(new BaseResponse("success", "jop deleted successfully"));
	}
}
