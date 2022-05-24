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
import com.management.app.entitys.WorkplaceJob;
import com.management.app.request.dto.JobRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.JobResDto;
import com.management.app.response.dto.JobTableResDto;
import com.management.app.service.JobService;

@CrossOrigin
@RestController
public class JobController {
	@Autowired
	private JobService jobService;

	@PostMapping(value = "/api/v1/job")
	public ResponseEntity<BaseResponse> addjob(@RequestBody JobRequestDto jobRequestDto) {
		if (jobService.existByName(jobRequestDto.getName())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This job already exist"));
		}
		Job job = new Job();
		BeanUtils.copyProperties(jobRequestDto, job,"id");
		Job savedjob = jobService.saveJob(job);
		return ResponseEntity.ok(new BaseResponse("success", "job saved successfully"));
	}
	
	@PutMapping(value = "/api/v1/job")
	public ResponseEntity<BaseResponse> updatejob(@RequestBody JobRequestDto jobRequestDto) {
		if (!jobService.existJob(jobRequestDto.getId())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This job not exist"));
		}
		if (jobService.updateExistByName(jobRequestDto.getId(),jobRequestDto.getName())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This job already exist"));
		}
		Job job = new Job();
		BeanUtils.copyProperties(jobRequestDto, job);
		Job savedjob = jobService.updateJob(job);
		return ResponseEntity.ok(new BaseResponse("success", "job update successfully"));
	}
	
	@GetMapping(value = "/api/v1/job")
	public ResponseEntity<BaseResponse> getjobs() {
		List<Job> jobs = jobService.getAllJob();
		List<JobResDto> responseDtos = new ArrayList<>();
		for (Job job : jobs) {
			JobResDto responseDto = new JobResDto();
			BeanUtils.copyProperties(job, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<JobResDto>>("jobs", responseDtos, "success",
				"jobs get successfully"));
	}
	
	@DeleteMapping(value = "/api/v1/job/{id}")
	public ResponseEntity<BaseResponse> deletejob(@PathVariable Long id) {
		if (!jobService.existJob(id)) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This job not exist"));
		}
		jobService.deleteById(id);
		return ResponseEntity.ok(new BaseResponse("success", "job deleted successfully"));
	}
	
	@GetMapping(value = "/api/v1/job/check-name/{name}")
	public ResponseEntity<BaseResponse> isNameExist(@PathVariable String name) {
		if (jobService.existJobByName(name)) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This job already exist"));
		}
		return ResponseEntity.ok(new BaseResponse("success", "true"));
	}
	
	@GetMapping(value = "/api/v1/job/company/{id}")
	public ResponseEntity<BaseResponse> getJobsByCompanyId(@PathVariable Long id) {
		List<WorkplaceJob> jobs = jobService.getJobsByCompanyId(id);
		List<JobTableResDto> responseDtos = new ArrayList<>();
		for (WorkplaceJob job : jobs) {
			JobTableResDto responseDto = new JobTableResDto();
			responseDto.setId(job.getId());
			responseDto.setName(job.getJob().getName());
			responseDto.setJobId(job.getJob().getId());
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<JobTableResDto>>("jobs", responseDtos, "success",
				"jobs get successfully"));
	}
	
}
