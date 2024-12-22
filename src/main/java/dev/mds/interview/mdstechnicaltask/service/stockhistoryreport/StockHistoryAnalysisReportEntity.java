package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.Stock;

public class StockHistoryAnalysisReportEntity {
	private Stock stock;
	private List<StockHistoryPeriodAnalysisReportEntity> periods;
	public StockHistoryAnalysisReportEntity(Stock stock, List<StockHistoryPeriodAnalysisReportEntity> periods) {
		super();
		this.stock = stock;
		this.periods = periods;
	}
	
	public Stock getStock() {
		return stock;
	}
	public List<StockHistoryPeriodAnalysisReportEntity> getPeriods() {
		return periods;
	}
}
