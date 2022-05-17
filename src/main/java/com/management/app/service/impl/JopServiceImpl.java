package com.management.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.Jop;
import com.management.app.repositories.JopRepository;
import com.management.app.service.JopService;

@Service
public class JopServiceImpl implements JopService {
	@Autowired
	private JopRepository jopRepository;

	@Transactional(readOnly = true)
	public boolean existByName(String name) {
		return jopRepository.existsByName(name);
	}

	@Transactional
	public Jop saveJop(Jop jop) {
		return jopRepository.save(jop);
	}

}
