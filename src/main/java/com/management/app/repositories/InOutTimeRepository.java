package com.management.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.InOutTime;

@Repository
public interface InOutTimeRepository extends JpaRepository<InOutTime, Long> {

}
