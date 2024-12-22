package dev.mds.interview.mdstechnicaltask.service;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.Stock;

public interface StockService {

	public List<Stock> getData();
	
	public Stock getById(Long id);
	
	public Stock getByCode(String code);
	
	public Stock insert(Stock entity);
	
	public Stock update(Stock entity);
	
	public void delete(Stock entity);
	
	public void delete(Long id);
}
