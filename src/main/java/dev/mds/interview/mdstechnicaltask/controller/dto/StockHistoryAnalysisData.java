package dev.mds.interview.mdstechnicaltask.controller.dto;

import java.util.Date;
import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.Stock;

public class StockHistoryAnalysisData {
	private Date bestBuyDate;
	private Double bestBuyPrice;
	private Date bestSellDate;
	private Double bestSellPrice;
	private Double profit;
	private Double maxProfit;
	private List<Stock> moreProfitableStocks;
	
	private StockHistoryAnalysisData() {
		// can be instantiated only using builder
	}
	
	public Date getBestBuyDate() {
		return bestBuyDate;
	}

	public Double getBestBuyPrice() {
		return bestBuyPrice;
	}

	public Date getBestSellDate() {
		return bestSellDate;
	}

	public Double getBestSellPrice() {
		return bestSellPrice;
	}

	public Double getProfit() {
		return profit;
	}

	public Double getMaxProfit() {
		return maxProfit;
	}

	public List<Stock> getMoreProfitableStocks() {
		return moreProfitableStocks;
	}

	public static class StockHistoryAnalysisDataBuilder {
		private Date bestBuyDate;
		private Double bestBuyPrice;
		private Date bestSellDate;
		private Double bestSellPrice;
		private Double profit;
		private Double maxProfit;
		private List<Stock> moreProfitableStocks;
		
		public StockHistoryAnalysisDataBuilder bestBuyDate(Date bestBuyDate) {
			this.bestBuyDate = bestBuyDate;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder bestBuyPrice(Double bestBuyPrice) {
			this.bestBuyPrice = bestBuyPrice;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder bestSellDate(Date bestSellDate) {
			this.bestSellDate = bestSellDate;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder bestSellPrice(Double bestSellPrice) {
			this.bestSellPrice = bestSellPrice;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder profit(Double profit) {
			this.profit = profit;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder maxProfit(Double maxProfit) {
			this.maxProfit = maxProfit;
			return this;
		}
		
		public StockHistoryAnalysisDataBuilder moreProfitableStocks(List<Stock> moreProfitableStocks) {
			this.moreProfitableStocks = moreProfitableStocks;
			return this;
		}
		
        public StockHistoryAnalysisData build() {
        	StockHistoryAnalysisData entity = new StockHistoryAnalysisData();
        	entity.bestBuyDate = this.bestBuyDate;
        	entity.bestBuyPrice = this.bestBuyPrice;
        	entity.bestSellDate = this.bestSellDate;
        	entity.bestSellPrice = this.bestSellPrice;
        	entity.profit = this.profit;
        	entity.maxProfit = this.maxProfit;
        	entity.moreProfitableStocks = this.moreProfitableStocks;
            return entity;
        }
	}
}