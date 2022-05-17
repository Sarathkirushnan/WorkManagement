package com.management.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Job;
import com.management.app.repositories.JobRepository;
import com.management.app.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;

	@Transactional(readOnly = true)
	public boolean existByName(String name) {
		return jobRepository.existsByName(name);
	}

	@Transactional
	public Job saveJop(Job job) {
		return jobRepository.save(job);
	}

	@Transactional(readOnly = true)
	public boolean existJop(Long id) {
		return jobRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean updateExistByName(Long id, String name) {
		return jobRepository.existsByNameAndIdNot(name,id);
	}

	@Transactional
	public Job updateJop(Job job) {
		return jobRepository.save(job);
	}
	
	@Transactional(readOnly = true)
	public List<Job> getAllJop() {
		return jobRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		jobRepository.deleteById(id);
	}
}
