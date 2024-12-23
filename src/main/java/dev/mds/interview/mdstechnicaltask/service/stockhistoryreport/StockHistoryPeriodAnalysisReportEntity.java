package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import dev.mds.interview.mdstechnicaltask.model.Stock;
import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public class StockHistoryPeriodAnalysisReportEntity {
	private Date dateFrom;
	private Date dateTo;
	private List<StockHistory> periodStockData;
	private Date bestBuyDate;
	private Double bestBuyValue;
	private Date bestSellDate;
	private Double bestSellValue;
	private Double profit;
	private Double maxProfit;
	private List<Stock> moreProfitableStocks;
	
	private StockHistoryCalculator calculator;
	
	public StockHistoryPeriodAnalysisReportEntity(Date dateFrom, Date dateTo, List<StockHistory> data, List<StockHistory> otherStockData){
		this.calculator = new StockHistoryAnalysisCalculator();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.periodStockData = data;
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
		moreProfitableStocks = otherStockData.stream().collect(Collectors.groupingBy(StockHistory::getStock)).entrySet()
				.stream()
				.map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), calculator.calculateMaxProfit(e.getValue())))
				.filter(e -> e.getValue() > maxProfit).map(Entry::getKey).toList();
	}

	public List<StockHistory> getPeriodStockData() {
		return periodStockData;
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

	public Date getDateFrom() {
		return dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public List<Stock> getMoreProfitableStocks() {
		return moreProfitableStocks;
	}
}