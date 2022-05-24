package com.management.app.service;

import java.util.List;

import com.management.app.entitys.Job;
import com.management.app.entitys.WorkplaceJob;

public interface WorkplaceJobService {
	public void saveWorkplaceJob(WorkplaceJob workplaceJob);

	public void updateWorkplaceJob(WorkplaceJob workplaceJob);

	public void deleteWorkplaceJob(Long id);

	public List<WorkplaceJob> getAllWorkplaceJob();

	public WorkplaceJob getWorkplaceJobById(Long id);
	
	public boolean workplaceJobExist(Long id);

	public boolean existByJopAndPlace(Long jobId, Long workplaceId);

	public void saveWorkplaceJob(WorkplaceJob workplaceJob, List<Job> newJobs);

}
