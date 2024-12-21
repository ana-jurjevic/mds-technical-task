package dev.mds.interview.mdstechnicaltask.service;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public interface StockHistoryService {
	public List<StockHistory> getData();
	
	public StockHistory getById(Long id);
	
	public StockHistory insert(StockHistory entity);
	
	public StockHistory update(StockHistory entity);
	
	public void delete(StockHistory entity);
	
	public void delete(Long id);
}
