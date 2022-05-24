package com.management.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.app.entitys.Job;
import com.management.app.entitys.Workplace;
import com.management.app.entitys.WorkplaceJob;
import com.management.app.request.dto.JobRequestDto;
import com.management.app.request.dto.WorkplaceJobRequestDto;
import com.management.app.request.dto.WorkplaceRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.JobTableResDto;
import com.management.app.response.dto.WorkplaceJobResDto;
import com.management.app.response.dto.WorkplaceResDto;
import com.management.app.response.dto.WorkplaceTableResDto;
import com.management.app.service.JobService;
import com.management.app.service.WorkplaceJobService;
import com.management.app.service.WorkplaceService;

@CrossOrigin
@RestController
public class WorkplaceJobController {
	@Autowired
	private WorkplaceJobService workplaceJobService;
	@Autowired
	private WorkplaceService workplaceService;
	@Autowired
	private JobService jobService;

	@PostMapping(value = "/api/v1/workplaceJob")
	public ResponseEntity<BaseResponse> addWorkplace(@RequestBody WorkplaceJobRequestDto workplaceJobRequestDto) {
		if (!workplaceService.workplaceExist(workplaceJobRequestDto.getWorkplaceId())) {
			return ResponseEntity.ok(new BaseResponse("error", "Workplace not exists"));
		}
		List<String> errorNames = new ArrayList<String>();
		List<Job> newJobs = new ArrayList<Job>();
		for (JobRequestDto jobs : workplaceJobRequestDto.getJobs()) {
			if (jobs.getId() != null && jobs.getId() != 0) {
				if (!jobService.existJob(jobs.getId())) {
					errorNames.add(jobs.getName());
				} else {
					Job job = new Job();
					BeanUtils.copyProperties(jobs, job);
					newJobs.add(job);
				}
			} else {
				Job job = new Job();
				BeanUtils.copyProperties(jobs, job);
				newJobs.add(job);
			}

		}
		if (errorNames.size() > 0) {
			return ResponseEntity.ok(new BaseResponse("error", "This " + errorNames + " Job(s) not exists"));
		}
//		if (workplaceJobService.existByJopAndPlace(workplaceJobRequestDto.getJobId(),workplaceJobRequestDto.getWorkplaceId())) {
//			return ResponseEntity.ok(new BaseResponse("error", "Workplace job allocation is doublicat"));
//		}

		WorkplaceJob workplaceJob = new WorkplaceJob();
		Workplace workplace = new Workplace();
		workplace.setId(workplaceJobRequestDto.getWorkplaceId());
		workplaceJob.setWorkplace(workplace);
		workplaceJobService.saveWorkplaceJob(workplaceJob, newJobs);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace job allocated successfully"));
	}

	@PutMapping(value = "/api/v1/workplaceJob")
	public ResponseEntity<BaseResponse> updateWorkplace(@RequestBody WorkplaceJobRequestDto workplaceJobRequestDto) {
		if (!workplaceJobService.workplaceJobExist(workplaceJobRequestDto.getId())) {
			return ResponseEntity.ok(new BaseResponse("error", "This workplace job allocation not exists"));
		}
		if (!workplaceService.workplaceExist(workplaceJobRequestDto.getWorkplaceId())) {
			return ResponseEntity.ok(new BaseResponse("error", "Workplace not exists"));
		}
//		if (!jobService.existJob(workplaceJobRequestDto.getJobId())) {
//			return ResponseEntity.ok(new BaseResponse("error", "Job not exists"));
//		}

		WorkplaceJob workplaceJob = new WorkplaceJob();
		BeanUtils.copyProperties(workplaceJobRequestDto, workplaceJob);
		workplaceJobService.saveWorkplaceJob(workplaceJob);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace updated successfully"));
	}

	@GetMapping(value = "/api/v1/workplaceJob")
	public ResponseEntity<BaseResponse> getAllWorkplaceJob() {
		List<Workplace> workplaceJobs = workplaceService.getAllWorkplace();
		List<WorkplaceTableResDto> responseDtos = new ArrayList<>();
		for (Workplace workplace : workplaceJobs) {
			WorkplaceTableResDto responseDto = new WorkplaceTableResDto();
			BeanUtils.copyProperties(workplace, responseDto);
			List<JobTableResDto> jobTableResDtos = new ArrayList<>();
			for (WorkplaceJob workplaceJob : workplace.getWorkplaceJobs()) {
				JobTableResDto jobTableResDto = new JobTableResDto();
				jobTableResDto.setId(workplaceJob.getId());
				jobTableResDto.setName(workplaceJob.getJob().getName());
				jobTableResDto.setJobId(workplaceJob.getJob().getId());
				jobTableResDtos.add(jobTableResDto);
			}
			responseDto.setJobs(jobTableResDtos);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<WorkplaceTableResDto>>("workplaceJobs", responseDtos,
				"success", "Workplace jobs get successfully"));
	}

	@GetMapping(value = "/api/v1/workplaceJob/{id}")
	public ResponseEntity<Object> getWorkplaceJob(@PathVariable Long id) {
		if (!workplaceJobService.workplaceJobExist(id)) {
			return ResponseEntity.ok(new BaseResponse("error", "This workplace job allocation not exists"));
		}
		WorkplaceJob workplaceJob = workplaceJobService.getWorkplaceJobById(id);
		WorkplaceJobResDto responseDto = new WorkplaceJobResDto();
		BeanUtils.copyProperties(workplaceJob, responseDto);

		return ResponseEntity.ok(new CommenResponse<WorkplaceJobResDto>("workplaceJob", responseDto, "success",
				"Workplace job get successfully"));
	}
}
