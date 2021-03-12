package com.fsoft.F_Cinema.validation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateValidation {

	/**
	 * checking 2 range of date is overlapped
	 * 
	 * @param [<Date> start1 - <Date> end2 ] [<Date> start3 - <Date> end4 ]
	 * @return boolean
	 */
	public Boolean isDateOverlapped(List<Date> dates1, List<Date> dates2) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		// sdf.setLenient(true);
		Date start1 = dates1.get(0);
		Date end1 = dates1.get(1);
		Date start2 = dates2.get(0);
		Date end2 = dates2.get(1);
		// (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
		if (start1.compareTo(end2) <= 0 && start2.compareTo(end1) <= 0) {
			return true;
		}

		return false;
	}

}
