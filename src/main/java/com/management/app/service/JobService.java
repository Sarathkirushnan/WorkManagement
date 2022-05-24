package com.management.app.service;

import java.util.List;

import com.management.app.entitys.Job;
import com.management.app.entitys.WorkplaceJob;

public interface JobService {

	boolean existByName(String name);

	Job saveJob(Job job);

	boolean existJob(Long id);

	boolean updateExistByName(Long id, String name);

	Job updateJob(Job job);
	
	public List<Job> getAllJob();
	
	public void deleteById(Long id);

	boolean existJobByName(String name);

	List<WorkplaceJob> getJobsByCompanyId(Long id);

}
