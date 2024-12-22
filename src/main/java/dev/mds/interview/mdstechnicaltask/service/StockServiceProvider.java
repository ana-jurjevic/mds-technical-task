package dev.mds.interview.mdstechnicaltask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mds.interview.mdstechnicaltask.model.Stock;
import dev.mds.interview.mdstechnicaltask.repository.StockRepository;
import dev.mds.interview.mdstechnicaltask.system.AppLogger;

@Service
public class StockServiceProvider implements StockService {
	private static final AppLogger logger = AppLogger.of(StockServiceProvider.class);
	
	@Autowired
	StockRepository repository;

	@Override
	public List<Stock> getData() {
		logger.debug("StockServiceProvider getData");
		return repository.findAll();
	}

	@Override
	public Stock insert(Stock entity) {
		logger.info("StockServiceProvider insert " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public Stock update(Stock entity) {
		logger.info("StockServiceProvider update " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public void delete(Stock entity) {
		logger.info("StockServiceProvider delete " + entity.toString());
		repository.delete(entity);
	}

	@Override
	public Stock getById(Long id) {
		logger.info("StockServiceProvider getById " + id.toString());
		return repository.getReferenceById(id);
	}

	@Override
	public void delete(Long id) {
		logger.info("StockServiceProvider delete " + id);
		repository.deleteById(id);
	}

	@Override
	public Stock getByCode(String code) {
		logger.info("StockServiceProvider getByCode " + code);
		return repository.getByCode(code);
	}
}
