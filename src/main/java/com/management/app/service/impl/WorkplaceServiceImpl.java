package com.management.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Workplace;
import com.management.app.repositories.WorkplaceRepository;
import com.management.app.service.WorkplaceService;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {
	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Transactional
	public void saveWorkplace(Workplace workplace) {
		workplaceRepository.save(workplace);
	}

	@Transactional
	public void updateWorkplace(Workplace workplace) {
		workplaceRepository.save(workplace);
	}

	@Transactional(readOnly = true)
	public boolean existByName(String name) {
		return workplaceRepository.existsByName(name);
	}

	@Transactional(readOnly = true)
	public boolean updateNameExist(Long id, String name) {
		return workplaceRepository.existsByNameAndIdNot(name, id);
	}

	@Transactional(readOnly = true)
	public boolean workplaceExist(Long id) {
		return workplaceRepository.existsById(id);
	}

	@Transactional
	public void deleteWorkplace(Long id) {
		workplaceRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Workplace> getAllWorkplace() {
		return workplaceRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Workplace> getAllUnAllocateWorkplace() {
		return workplaceRepository.findByWorkplaceJobsIsNull();
		}

	@Transactional(readOnly = true)
	public List<Workplace> getAllAllocateWorkplace() {
		return workplaceRepository.findByWorkplaceJobsNotNull();
	}
}
