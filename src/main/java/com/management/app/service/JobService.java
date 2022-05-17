package com.management.app.service;

import java.util.List;

import com.management.app.entitys.Job;

public interface JobService {

	boolean existByName(String name);

	Job saveJop(Job job);

	boolean existJop(Long id);

	boolean updateExistByName(Long id, String name);

	Job updateJop(Job job);
	
	public List<Job> getAllJop();
	
	public void deleteById(Long id);

}
