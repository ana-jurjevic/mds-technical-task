package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.Date;
import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public class StockHistoryPeriodAnalysisReportEntity {
	private List<StockHistory> data;
	private Date bestBuyDate;
	private Double bestBuyValue;
	private Date bestSellDate;
	private Double bestSellValue;
	private Double profit;
	private Double maxProfit;
	
	private StockHistoryCalculator calculator;
	
	// optional List<String> moreProfitableStocks
	
	public StockHistoryPeriodAnalysisReportEntity(List<StockHistory> data){
		this.calculator = new StockHistoryAnalysisCalculator();
		this.data = data;
		StockHistory[] bestTrade = calculator.calculateBestBuySell(data);
		if (bestTrade.length == 0) {
			return;
		}
		this.bestBuyDate = bestTrade[0].getDate();
		this.bestBuyValue = bestTrade[0].getClose();
		this.bestSellDate = bestTrade[1].getDate();
		this.bestSellValue = bestTrade[1].getClose();
		this.profit = calculator.calculateProfit(bestTrade[0], bestTrade[1]);
		this.maxProfit = calculator.calculateMaxProfit(data);
	}

	public List<StockHistory> getData() {
		return data;
	}

	public Date getBestBuyDate() {
		return bestBuyDate;
	}

	public Double getBestBuyValue() {
		return bestBuyValue;
	}

	public Date getBestSellDate() {
		return bestSellDate;
	}

	public Double getBestSellValue() {
		return bestSellValue;
	}

	public Double getProfit() {
		return profit;
	}

	public Double getMaxProfit() {
		return maxProfit;
	}
}