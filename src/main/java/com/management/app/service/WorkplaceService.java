package com.management.app.service;

import java.util.List;

import com.management.app.entitys.Workplace;

public interface WorkplaceService {

	void saveWorkplace(Workplace workplace);

	boolean existByName(String name);

	boolean updateNameExist(Long id, String name);

	boolean workplaceExist(Long id);

	void deleteWorkplace(Long id);
	
	void updateWorkplace(Workplace workplace);

	List<Workplace> getAllWorkplace();

	List<Workplace> getAllUnAllocateWorkplace();

	List<Workplace> getAllAllocateWorkplace();

}
