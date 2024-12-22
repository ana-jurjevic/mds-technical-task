package dev.mds.interview.mdstechnicaltask.controller.dto;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

//TODO JA
public class StockHistoryAnalysisResponse {
	private Long id;
	
	public StockHistoryAnalysisResponse(StockHistory entity) {
		this.id = entity.getId();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
