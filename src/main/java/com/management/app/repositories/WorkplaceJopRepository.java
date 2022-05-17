package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.WorkplaceJop;

@Repository
public interface WorkplaceJopRepository extends JpaRepository<WorkplaceJop, Long> {

}
