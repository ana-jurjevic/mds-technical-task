package dev.mds.interview.mdstechnicaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mds.interview.mdstechnicaltask.controller.converter.StockHistoryAnalysisConverter;
import dev.mds.interview.mdstechnicaltask.controller.dto.StockHistoryAnalysisRequest;
import dev.mds.interview.mdstechnicaltask.controller.dto.StockHistoryAnalysisResponse;
import dev.mds.interview.mdstechnicaltask.service.StockHistoryService;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryAnalysisReportEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stocks/search")
public class StockHistoryAnalysisController {
	
	@Autowired
	private StockHistoryService service;
	
	@Autowired
	private StockHistoryAnalysisConverter converter;
	
	
	@PostMapping
	public StockHistoryAnalysisResponse search(@RequestBody @Valid StockHistoryAnalysisRequest requestBody) {
		StockHistoryAnalysisReportEntity data = service.search(requestBody.getStockCode(), requestBody.getDateFrom(), requestBody.getDateTo());
		return converter.convertToDto(data);
	}
	
	@PostMapping("/old")
	public StockHistoryAnalysisReportEntity searchOld(@RequestBody @Valid StockHistoryAnalysisRequest requestBody) {
		StockHistoryAnalysisReportEntity data = service.search(requestBody.getStockCode(), requestBody.getDateFrom(), requestBody.getDateTo());
		return data;
	}
}
