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

import com.management.app.entitys.Workplace;
import com.management.app.request.dto.WorkplaceRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.WorkplaceDropRes;
import com.management.app.response.dto.WorkplaceResDto;
import com.management.app.service.WorkplaceService;

@CrossOrigin
@RestController
public class WorkplaceController {
	@Autowired
	private WorkplaceService workplaceService;

	@PostMapping(value = "/api/v1/workplace")
	public ResponseEntity<BaseResponse> addWorkplace(@RequestBody WorkplaceRequestDto workplaceDto) {
		if (workplaceService.existByName(workplaceDto.getName())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This workplace name already exists"));
		}

		Workplace workplace = new Workplace();
		BeanUtils.copyProperties(workplaceDto, workplace, "id");
		workplaceService.saveWorkplace(workplace);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace saved successfully"));
	}

	@PutMapping(value = "/api/v1/workplace")
	public ResponseEntity<BaseResponse> updateWorkplace(@RequestBody WorkplaceRequestDto workplaceDto) {
		if (!workplaceService.workplaceExist(workplaceDto.getId())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This workplace not exists"));
		}
		if (workplaceService.updateNameExist(workplaceDto.getId(), workplaceDto.getName())) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This workplace name already exists"));
		}

		Workplace workplace = new Workplace();
		BeanUtils.copyProperties(workplaceDto, workplace);
		workplaceService.saveWorkplace(workplace);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace updated successfully"));
	}

	@DeleteMapping(value = "/api/v1/workplace/{id}")
	public ResponseEntity<BaseResponse> deleteWorkplace(@PathVariable Long id) {
		if (!workplaceService.workplaceExist(id)) {
			return ResponseEntity.badRequest().body(new BaseResponse("error", "This workplace not exists"));
		}
		workplaceService.deleteWorkplace(id);
		return ResponseEntity.ok(new BaseResponse("success", "Workplace updated successfully"));
	}

	@GetMapping(value = "/api/v1/workplace")
	public ResponseEntity<BaseResponse> getCompany() {
		List<Workplace> workplaces = workplaceService.getAllWorkplace();
		List<WorkplaceResDto> responseDtos = new ArrayList<>();
		for (Workplace workplace : workplaces) {
			WorkplaceResDto responseDto = new WorkplaceResDto();
			BeanUtils.copyProperties(workplace, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<WorkplaceResDto>>("workplaces", responseDtos, "success",
				"Workplace get successfully"));
	}
	
	@GetMapping(value = "/api/v1/workplace/un-allocated")
	public ResponseEntity<BaseResponse> getUnAllocateCompany() {
		List<Workplace> workplaces = workplaceService.getAllUnAllocateWorkplace();
		List<WorkplaceDropRes> responseDtos = new ArrayList<>();
		for (Workplace workplace : workplaces) {
			WorkplaceDropRes responseDto = new WorkplaceDropRes();
			BeanUtils.copyProperties(workplace, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<WorkplaceDropRes>>("workplaces", responseDtos, "success",
				"Workplace get successfully"));
	}
	
	@GetMapping(value = "/api/v1/workplace/allocated")
	public ResponseEntity<BaseResponse> getAllocateCompany() {
		List<Workplace> workplaces = workplaceService.getAllWorkplace();
		System.out.println(workplaces);
		List<WorkplaceDropRes> responseDtos = new ArrayList<>();
		for (Workplace workplace : workplaces) {
			if(!workplace.getWorkplaceJobs().isEmpty()) {
				WorkplaceDropRes responseDto = new WorkplaceDropRes();
				BeanUtils.copyProperties(workplace, responseDto);
				responseDtos.add(responseDto);
			}
		}
		return ResponseEntity.ok(new CommenResponse<List<WorkplaceDropRes>>("workplaces", responseDtos, "success",
				"Workplace get successfully"));
	}
}
