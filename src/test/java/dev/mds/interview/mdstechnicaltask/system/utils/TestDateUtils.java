package dev.mds.interview.mdstechnicaltask.system.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
class TestDateUtils {
	
	@Test
	void giveSameDates_whenDaysBetween_thenReturnZero(){
		Date date = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		
		long expectedResult = 0;
		
		long result = DateUtils.daysBetween(date, date);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	void givenDatesWithTwoDaysBetween_whenDaysBetween_thenReturnTwo(){
		Date dateFrom = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		Date dateTo = new Date(Date.UTC(2020, 2, 22, 0, 0, 0));
		
		long expectedResult = 2;
		
		long result = DateUtils.daysBetween(dateFrom, dateTo);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	void givenDateAnd2Days_whenAddDays_thenReturnTwoDaysAfter(){
		Date date = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		int daysCount = 2;
		
		Date expectedResult = new Date(Date.UTC(2020, 2, 22, 0, 0, 0));
		
		Date result = DateUtils.addDays(date, daysCount);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenDateAndMinus2Days_whenAddDays_thenReturnTwoDaysBefore(){
		Date date = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		int daysCount = -2;
		
		Date expectedResult = new Date(Date.UTC(2020, 2, 18, 0, 0, 0));
		
		Date result = DateUtils.addDays(date, daysCount);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenDateAnd0Days_whenAddDays_thenReturnSameDay(){
		Date date = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		int daysCount = 0;
		
		Date expectedResult = new Date(Date.UTC(2020, 2, 20, 0, 0, 0));
		
		Date result = DateUtils.addDays(date, daysCount);
		
		assertEquals(expectedResult, result);
	}
}