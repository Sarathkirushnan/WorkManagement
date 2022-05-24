package com.management.app.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.app.entitys.WorkingDays;

@Repository
public interface WorkingDaysRepository extends JpaRepository<WorkingDays, Long> {
	List<WorkingDays> findByEmployeeIdAndWorkplaceJopIdOrderByStartDate(Long employeeId, Long workplaceAndJopId);

	List<WorkingDays> findByEmployeeId(Long id);

	List<WorkingDays> findByEmployeeIdAndEndDateAfterAndStartDateBefore(Long id, Date date, Date date2);

}
