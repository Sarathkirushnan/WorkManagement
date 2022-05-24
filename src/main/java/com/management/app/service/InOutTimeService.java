package com.management.app.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.management.app.entitys.InOutTime;

public interface InOutTimeService {

	boolean existByWorkingDaysId(Long workingDaysId);

	boolean isVelidWorkingTimeId(Long workingDaysId, Timestamp inTime, Timestamp outTime, Date date);

	InOutTime saveInOutTime(InOutTime inOutTime);

	List<InOutTime> getInOutTimeByEmployeeId(Long id);

	List<InOutTime> getInOutTimeByEmployeeIdAndDate(Long id, Date date);

}
