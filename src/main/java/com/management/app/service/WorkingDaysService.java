package com.management.app.service;

import java.sql.Date;
import java.util.List;

import com.management.app.entitys.WorkingDays;

public interface WorkingDaysService {

	boolean isVelidDateReng(Long workplaceAndJopId, Long employeeId, Date startDate, Date endDate);

	void saveWorkingDays(WorkingDays workingDays);

	List<WorkingDays> getWorkdaysByEmployeeId(Long id);

	boolean existByWorkingDaysId(Long workingDaysId);

	List<WorkingDays> getByEmployeeIdAndDate(Long id, Date date);

}
