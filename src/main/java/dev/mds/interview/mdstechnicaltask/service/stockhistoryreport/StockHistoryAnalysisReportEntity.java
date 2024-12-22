package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.Company;

public class StockHistoryAnalysisReportEntity {
	private Company company;
	private List<StockHistoryPeriodAnalysisReportEntity> periods;
	public StockHistoryAnalysisReportEntity(Company company, List<StockHistoryPeriodAnalysisReportEntity> periods) {
		super();
		this.company = company;
		this.periods = periods;
	}
	
	public Company getCompany() {
		return company;
	}
	public List<StockHistoryPeriodAnalysisReportEntity> getPeriods() {
		return periods;
	}
}
