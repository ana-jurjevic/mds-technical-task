package dev.mds.interview.mdstechnicaltask.system.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
class TestDateUtils {
	
	@Test
	void daysBetweenZeroWhenSameDates(){
		Date date = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		
		long expectedResult = 0;
		
		long result = DateUtils.daysBetween(date, date);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	void daysBetween2WhenTwoDaysBetween(){
		Date dateFrom = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		Date dateTo = new Date(Date.UTC(2020, 2, 22, 0, 0, 0));
		
		long expectedResult = 2;
		
		long result = DateUtils.daysBetween(dateFrom, dateTo);
		
		assertEquals(expectedResult, result);
	}
}
