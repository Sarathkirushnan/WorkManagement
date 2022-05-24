package com.management.app.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.app.entitys.InOutTime;
import com.management.app.entitys.WorkingDays;
import com.management.app.repositories.InOutTimeRepository;
import com.management.app.repositories.WorkingDaysRepository;
import com.management.app.service.InOutTimeService;

@Service
public class InOutTimeServiceImpl implements InOutTimeService{
	@Autowired
	private InOutTimeRepository inOutTimeRepository;
	@Autowired
	private WorkingDaysRepository workingDaysRepository;
	
	@Transactional(readOnly = true)
	public boolean existByWorkingDaysId(Long workingDaysId) {
		return inOutTimeRepository.existsById(workingDaysId);
	}

	@Transactional(readOnly = true)
	public boolean isVelidWorkingTimeId(Long workingDaysId, Timestamp inTime, Timestamp outTime, Date date) {
		WorkingDays workingDays=workingDaysRepository.getById(workingDaysId);
		List<InOutTime> inOutTimes=inOutTimeRepository.findByWorkingDaysEmployeeIdAndDateOrderByInTime(workingDays.getEmployee().getId(),date);
		for (InOutTime inOutTime : inOutTimes) {
			if (inOutTime.getInTime().equals(inTime) || inOutTime.getOutTime().equals(inTime)
					|| inOutTime.getOutTime().equals(outTime) || inOutTime.getOutTime().equals(outTime)) {
				return false;
			}
			if (inTime.before(inOutTime.getInTime())) {
				if (outTime.before(inOutTime.getInTime())) {
					return true;
				} else {
					return false;
				}
			} else if (inTime.after(inOutTime.getOutTime())) {
				continue;
			} else {
				return false;
			}

		}
		return true;
	}

	@Transactional
	public InOutTime saveInOutTime(InOutTime inOutTime) {
		return inOutTimeRepository.save(inOutTime);
	}

	@Transactional(readOnly = true)
	public List<InOutTime> getInOutTimeByEmployeeId(Long id) {
		return inOutTimeRepository.findByWorkingDaysEmployeeId(id);
	}

	@Transactional(readOnly = true)
	public List<InOutTime> getInOutTimeByEmployeeIdAndDate(Long id, Date date) {
		return inOutTimeRepository.findByWorkingDaysEmployeeIdAndDate(id,date);
	}

}
