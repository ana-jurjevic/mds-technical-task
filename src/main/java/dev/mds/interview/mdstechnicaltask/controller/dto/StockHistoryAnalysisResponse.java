package dev.mds.interview.mdstechnicaltask.controller.dto;

import java.util.List;

public class StockHistoryAnalysisResponse {
	private List<StockHistoryAnalysisData> periods;

	public StockHistoryAnalysisResponse(List<StockHistoryAnalysisData> periods) {
		super();
		this.periods = periods;
	}

	public List<StockHistoryAnalysisData> getPeriods() {
		return periods;
	}
}
