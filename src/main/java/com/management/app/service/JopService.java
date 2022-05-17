package com.management.app.service;

import java.util.List;

import com.management.app.entitys.Jop;

public interface JopService {

	boolean existByName(String name);

	Jop saveJop(Jop jop);

	boolean existJop(Long id);

	boolean updateExistByName(Long id, String name);

	Jop updateJop(Jop jop);
	
	public List<Jop> getAllJop();
	
	public void deleteById(Long id);

}
