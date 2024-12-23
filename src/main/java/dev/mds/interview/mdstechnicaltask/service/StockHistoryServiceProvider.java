package dev.mds.interview.mdstechnicaltask.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mds.interview.mdstechnicaltask.model.Stock;
import dev.mds.interview.mdstechnicaltask.model.StockHistory;
import dev.mds.interview.mdstechnicaltask.repository.StockHistoryRepository;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryPeriodAnalysisReportEntity;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryAnalysisReportEntity;
import dev.mds.interview.mdstechnicaltask.system.AppLogger;
import dev.mds.interview.mdstechnicaltask.system.utils.DateUtils;

@Service
public class StockHistoryServiceProvider implements StockHistoryService {
	private static final AppLogger logger = AppLogger.of(StockHistoryServiceProvider.class);
	
	@Autowired
	StockHistoryRepository repository;
	
	@Autowired
	StockService stockService;

	@Override
	public List<StockHistory> getData() {
		logger.debug("StockServiceProvider getData");
		return repository.findAll();
	}

	@Override
	public StockHistory insert(StockHistory entity) {
		logger.info("StockServiceProvider insert " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public StockHistory update(StockHistory entity) {
		logger.info("StockServiceProvider update " + entity.toString());
		return repository.save(entity);
	}

	@Override
	public void delete(StockHistory entity) {
		logger.info("StockServiceProvider delete " + entity.toString());
		repository.delete(entity);
	}

	@Override
	public StockHistory getById(Long id) {
		logger.info("StockServiceProvider getById " + id.toString());
		return repository.getReferenceById(id);
	}

	@Override
	public void delete(Long id) {
		logger.info("StockServiceProvider delete " + id);
		repository.deleteById(id);
	}

	@Override
	public StockHistoryAnalysisReportEntity search(String stockCode, Date dateFrom, Date dateTo) {
		logger.info("StockServiceProvider search for stockCode: " + stockCode + ", dateFrom: " + dateFrom + ", dateTo: " + dateTo);
		long daysCount = DateUtils.daysBetween(dateFrom, dateTo);
		Stock stock = stockService.getByCode(stockCode);
		logger.info("Stock: " + stock);
		Date precedingPeriodDateFrom = DateUtils.addDays(dateFrom, -(int) daysCount-1);
		Date precedingPeriodDateTo = DateUtils.addDays(dateFrom, -1);
		Date followingPeriodDateFrom = DateUtils.addDays(dateTo, 1);
		Date followingPeriodDateTo = DateUtils.addDays(dateTo, (int) daysCount + 1);
		List<StockHistoryPeriodAnalysisReportEntity> dataByPeriod = new ArrayList<>();
		dataByPeriod.add(new StockHistoryPeriodAnalysisReportEntity(dateFrom, dateTo, repository.search(dateFrom, dateTo, stock), repository.searchOtherStocks(dateFrom, dateTo, stock)));
		dataByPeriod.add(new StockHistoryPeriodAnalysisReportEntity(precedingPeriodDateFrom, precedingPeriodDateTo, repository.search(precedingPeriodDateFrom, precedingPeriodDateTo, stock), repository.searchOtherStocks(precedingPeriodDateFrom, precedingPeriodDateTo, stock)));
		dataByPeriod.add(new StockHistoryPeriodAnalysisReportEntity(followingPeriodDateFrom, followingPeriodDateTo, repository.search(followingPeriodDateFrom, followingPeriodDateTo, stock), repository.searchOtherStocks(followingPeriodDateFrom, followingPeriodDateTo, stock)));
		return new StockHistoryAnalysisReportEntity(stock, dataByPeriod);
	}
}
