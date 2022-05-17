package com.management.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.app.entitys.Jop;
import com.management.app.request.dto.JopRequestDto;
import com.management.app.response.BaseResponse;
import com.management.app.service.JopService;

@CrossOrigin
@RestController
public class JopController {
	@Autowired
	private JopService jopService;

	@PostMapping(value = "/api/v1/employee")
	public ResponseEntity<BaseResponse> addEmployee(@RequestBody JopRequestDto jopRequestDto) {
		if (jopService.existByName(jopRequestDto.getName())) {
			return ResponseEntity.ok(new BaseResponse("error", "This jop already exist"));
		}
		Jop jop = new Jop();
		BeanUtils.copyProperties(jopRequestDto, jop);
		Jop savedJop = jopService.saveJop(jop);
		return ResponseEntity.ok(new BaseResponse("success", "jop saved successfully"));
	}
}
