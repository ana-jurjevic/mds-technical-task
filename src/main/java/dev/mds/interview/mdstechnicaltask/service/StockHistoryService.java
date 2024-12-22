package dev.mds.interview.mdstechnicaltask.service;

import java.util.Date;
import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryAnalysisReportEntity;

public interface StockHistoryService {
	public List<StockHistory> getData();
	
	public StockHistoryAnalysisReportEntity search(String stockCode, Date dateFrom, Date dateTo);
	
	public StockHistory getById(Long id);
	
	public StockHistory insert(StockHistory entity);
	
	public StockHistory update(StockHistory entity);
	
	public void delete(StockHistory entity);
	
	public void delete(Long id);
}
