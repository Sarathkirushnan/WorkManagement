package com.management.app.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Job;
import com.management.app.entitys.WorkplaceJob;
import com.management.app.repositories.JobRepository;
import com.management.app.repositories.WorkplaceJobRepository;
import com.management.app.service.WorkplaceJobService;

@Service
public class WorkplaceJobServiceImpl implements WorkplaceJobService {
	@Autowired
	private WorkplaceJobRepository workplaceJobRepository;
	@Autowired
	private JobRepository jobRepository;

	@Transactional
	public void saveWorkplaceJob(WorkplaceJob workplaceJob) {
		workplaceJobRepository.save(workplaceJob);
	}

	@Transactional
	public void updateWorkplaceJob(WorkplaceJob workplaceJob) {
		workplaceJobRepository.save(workplaceJob);
	}

	@Transactional(readOnly = true)
	public boolean workplaceJobExist(Long id) {
		return workplaceJobRepository.existsById(id);
	}

	@Transactional
	public void deleteWorkplaceJob(Long id) {
		workplaceJobRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<WorkplaceJob> getAllWorkplaceJob() {
		return workplaceJobRepository.findAll();
	}

	@Transactional(readOnly = true)
	public WorkplaceJob getWorkplaceJobById(Long id) {
		return workplaceJobRepository.getById(id);
	}

	@Transactional(readOnly = true)
	public boolean existByJopAndPlace(Long jobId, Long workplaceId) {
		return workplaceJobRepository.existsByJobIdAndWorkplaceId(jobId,workplaceId);
	}

	@Transactional
	public void saveWorkplaceJob(WorkplaceJob workplaceJob, List<Job> newJobs) {
		for(Job job:newJobs) {
			WorkplaceJob workpaceJobObj=new WorkplaceJob();
			BeanUtils.copyProperties(workplaceJob, workpaceJobObj);
			if(job.getId()!=null&&job.getId()!=0) {
				workpaceJobObj.setJob(job);				
			}else {
				Job newJob=jobRepository.save(job);
				workpaceJobObj.setJob(newJob);
			}	
		workplaceJobRepository.save(workpaceJobObj);
		}
		
	}
}
