package dev.mds.interview.mdstechnicaltask.system.utils;

import java.time.Duration;
import java.util.Date;


public class DateUtils {

	private DateUtils() {
		// to prevent instantiation - utility class
	}
	
	public static long daysBetween(Date dateFrom, Date dateTo) {
		return Math.abs(Duration.between(dateFrom.toInstant(), dateTo.toInstant()).toDays());
	}
}
