package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

}
