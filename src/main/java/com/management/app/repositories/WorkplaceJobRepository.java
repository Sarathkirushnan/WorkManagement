package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.WorkplaceJob;

@Repository
public interface WorkplaceJobRepository extends JpaRepository<WorkplaceJob, Long> {

	boolean existsByJobIdAndWorkplaceId(Long jobId, Long workplaceId);

}
