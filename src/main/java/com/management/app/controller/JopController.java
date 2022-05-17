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

import com.management.app.entitys.Jop;
import com.management.app.request.dto.JopRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.response.CommenResponse;
import com.management.app.response.dto.JopResDto;
import com.management.app.service.JopService;

@CrossOrigin
@RestController
public class JopController {
	@Autowired
	private JopService jopService;

	@PostMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> addJop(@RequestBody JopRequestDto jopRequestDto) {
		if (jopService.existByName(jopRequestDto.getName())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop already exist"));
		}
		Jop jop = new Jop();
		BeanUtils.copyProperties(jopRequestDto, jop,"id");
		Jop savedJop = jopService.saveJop(jop);
		return ResponseEntity.ok(new BaseResponse("success", "jop saved successfully"));
	}
	
	@PutMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> updateJop(@RequestBody JopRequestDto jopRequestDto) {
		if (!jopService.existJop(jopRequestDto.getId())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop not exist"));
		}
		if (jopService.updateExistByName(jopRequestDto.getId(),jopRequestDto.getName())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop already exist"));
		}
		Jop jop = new Jop();
		BeanUtils.copyProperties(jopRequestDto, jop);
		Jop savedJop = jopService.updateJop(jop);
		return ResponseEntity.ok(new BaseResponse("success", "jop update successfully"));
	}
	
	@GetMapping(value = "/api/v1/jop")
	public ResponseEntity<BaseResponse> getJops() {
		List<Jop> jops = jopService.getAllJop();
		List<JopResDto> responseDtos = new ArrayList<>();
		for (Jop jop : jops) {
			JopResDto responseDto = new JopResDto();
			BeanUtils.copyProperties(jop, responseDto);
			responseDtos.add(responseDto);
		}
		return ResponseEntity.ok(new CommenResponse<List<JopResDto>>("jops", responseDtos, "success",
				"jops get successfully"));
	}
	
	@DeleteMapping(value = "/api/v1/jop/{id}")
	public ResponseEntity<BaseResponse> deleteJop(@PathVariable Long id) {
		if (!jopService.existJop(id)) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop not exist"));
		}
		jopService.deleteById(id);
		return ResponseEntity.ok(new BaseResponse("success", "jop deleted successfully"));
	}
}
