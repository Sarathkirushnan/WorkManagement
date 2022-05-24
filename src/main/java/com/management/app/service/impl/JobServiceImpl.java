package com.management.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Job;
import com.management.app.entitys.WorkplaceJob;
import com.management.app.repositories.JobRepository;
import com.management.app.repositories.WorkplaceRepository;
import com.management.app.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Transactional(readOnly = true)
	public boolean existByName(String name) {
		return jobRepository.existsByName(name);
	}

	@Transactional
	public Job saveJob(Job job) {
		return jobRepository.save(job);
	}

	@Transactional(readOnly = true)
	public boolean existJob(Long id) {
		return jobRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean updateExistByName(Long id, String name) {
		return jobRepository.existsByNameAndIdNot(name,id);
	}

	@Transactional
	public Job updateJob(Job job) {
		return jobRepository.save(job);
	}
	
	@Transactional(readOnly = true)
	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		jobRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean existJobByName(String name) {
		return jobRepository.existsByNameIgnoreCase(name);
	}

	@Transactional(readOnly = true)
	public List<WorkplaceJob> getJobsByCompanyId(Long id) {
		return workplaceRepository.findById(id).get().getWorkplaceJobs();
	}
}
