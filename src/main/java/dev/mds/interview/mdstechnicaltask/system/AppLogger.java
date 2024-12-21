package dev.mds.interview.mdstechnicaltask.system;

public interface AppLogger {

	public static AppLogger of(Class<?> clazz) {
		return new Logger(clazz);
	}

	public void error(Exception exception);

	public void error(String message);

	public void info(Exception exception);
	
	public void info(String message);

	public void debug(Exception exception);
	
	public void debug(String message);
}