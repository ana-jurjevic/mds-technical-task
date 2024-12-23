package dev.mds.interview.mdstechnicaltask.controller.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.mds.interview.mdstechnicaltask.controller.dto.StockHistoryAnalysisData;
import dev.mds.interview.mdstechnicaltask.controller.dto.StockHistoryAnalysisResponse;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryAnalysisReportEntity;
import dev.mds.interview.mdstechnicaltask.service.stockhistoryreport.StockHistoryPeriodAnalysisReportEntity;

@Component
public class StockHistoryAnalysisConverter implements DtoConvertable<StockHistoryAnalysisReportEntity, StockHistoryAnalysisResponse> {

	@Override
	public StockHistoryAnalysisResponse convertToDto(StockHistoryAnalysisReportEntity entity) {
		List<StockHistoryAnalysisData> periods = entity.getPeriods().stream().map(this::covertPeriodData).toList();
		return new StockHistoryAnalysisResponse(periods);
	}

	private StockHistoryAnalysisData covertPeriodData(StockHistoryPeriodAnalysisReportEntity period) {
		return new StockHistoryAnalysisData.StockHistoryAnalysisDataBuilder().bestBuyDate(period.getBestBuyDate())
				.bestBuyPrice(period.getBestBuyValue()).bestSellDate(period.getBestSellDate())
				.bestSellPrice(period.getBestSellValue()).profit(period.getProfit()).maxProfit(period.getMaxProfit())
				.moreProfitableStocks(period.getMoreProfitableStocks())
				.build();
	}

}