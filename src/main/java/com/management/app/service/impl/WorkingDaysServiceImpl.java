package com.management.app.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.WorkingDays;
import com.management.app.repositories.WorkingDaysRepository;
import com.management.app.service.WorkingDaysService;

@Service
public class WorkingDaysServiceImpl implements WorkingDaysService {
	@Autowired
	private WorkingDaysRepository workingDaysRepository;

	@Transactional(readOnly = true)
	public boolean isVelidDateReng(Long workplaceAndJopId, Long employeeId, Date startDate, Date endDate) {
		List<WorkingDays> workingDays = workingDaysRepository
				.findByEmployeeIdAndWorkplaceJopIdOrderByStartDate(employeeId, workplaceAndJopId);
		for (WorkingDays workingDay : workingDays) {
			if (workingDay.getStartDate().equals(startDate) || workingDay.getStartDate().equals(endDate)
					|| workingDay.getEndDate().equals(endDate) || workingDay.getEndDate().equals(startDate)) {
				return false;
			}
			if (startDate.before(workingDay.getStartDate())) {
				if (endDate.before(workingDay.getStartDate())) {
					return true;
				} else {
					return false;
				}
			} else if (startDate.after(workingDay.getEndDate())) {
				continue;
			} else {
				return false;
			}

		}
		return true;
	}

	@Transactional
	public void saveWorkingDays(WorkingDays workingDays) {
		workingDaysRepository.save(workingDays);
	}

	@Transactional(readOnly = true)
	public List<WorkingDays> getWorkdaysByEmployeeId(Long id) {
		return workingDaysRepository.findByEmployeeId(id);
	}

	@Transactional(readOnly = true)
	public boolean existByWorkingDaysId(Long workingDaysId) {
		return workingDaysRepository.existsById(workingDaysId);
	}

	@Transactional(readOnly = true)
	public List<WorkingDays> getByEmployeeIdAndDate(Long id, Date date) {
		List<WorkingDays> allWorks = workingDaysRepository.findByEmployeeId(id);
		List<WorkingDays> selectedWorks = new ArrayList<>();
		for (WorkingDays works : allWorks) {
			if ((works.getStartDate().before(date) && works.getEndDate().after(date))
					|| works.getStartDate().equals(date) || works.getEndDate().equals(date)) {

				selectedWorks.add(works);
			}
		}

		return selectedWorks;
	}

}
