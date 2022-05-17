package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.WorkingDays;

@Repository
public interface WorkingDaysRepository extends JpaRepository<WorkingDays, Long> {

}
