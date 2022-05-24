package com.management.app.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.InOutTime;

@Repository
public interface InOutTimeRepository extends JpaRepository<InOutTime, Long> {

	List<InOutTime> findByWorkingDaysEmployeeIdAndDateOrderByInTime(Long id, Date date);

	List<InOutTime> findByWorkingDaysEmployeeId(Long id);

	List<InOutTime> findByWorkingDaysEmployeeIdAndDate(Long id, Date date);

}
