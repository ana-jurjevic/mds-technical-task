package dev.mds.interview.mdstechnicaltask.system;

import org.apache.logging.log4j.LogManager;

public class Logger implements AppLogger {
	private org.apache.logging.log4j.Logger logger;

	public Logger(Class<?> clazz) {
		this.logger = LogManager.getLogger(clazz);
	}

	@Override
	public void error(Exception exception) {
		logger.error(exception);
	}

	@Override
	public void info(Exception exception) {
		logger.info(exception);
	}

	@Override
	public void debug(Exception exception) {
		logger.debug(exception);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void debug(String message) {
		logger.debug(message);
	}
}