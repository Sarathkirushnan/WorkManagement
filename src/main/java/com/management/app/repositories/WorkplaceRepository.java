package com.management.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);

	List<Workplace> findByWorkplaceJobsIsNull();

	List<Workplace> findByWorkplaceJobsNotNull();

}
