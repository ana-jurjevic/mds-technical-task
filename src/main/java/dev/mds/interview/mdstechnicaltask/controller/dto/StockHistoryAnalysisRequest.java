package dev.mds.interview.mdstechnicaltask.controller.dto;

import java.util.Date;

public class StockHistoryAnalysisRequest {
	private String stockCode;
	private Date dateFrom;
	private Date dateTo;
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}
