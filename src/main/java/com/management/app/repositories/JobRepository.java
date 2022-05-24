package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);

	boolean existsByNameIgnoreCase(String name);

}
