package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

class TestStockHistoryAnalysisCalculator {
	private static StockHistoryCalculator calculator;
	
	@BeforeAll
	static void init() {
		calculator = new StockHistoryAnalysisCalculator();
	}
	
	@Test
	void givenStockHistoryBestBuyBeforeBestSell_whenCalculateBestBuySell_thenReturnBestBuyAndBestSell() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(17.5);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(18.5);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(19.5);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(20.5);
		data.add(entity4);
	
		StockHistory[] expectedResult = new StockHistory[] {entity1, entity4};
		
		StockHistory[] result = calculator.calculateBestBuySell(data);
		
		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}
	
	@Test
	void givenStockHistorySingleEntry_whenCalculateBestBuySell_thenReturnEmpty() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(17.5);
		data.add(entity1);
	
		StockHistory[] result = calculator.calculateBestBuySell(data);
		
		assertEquals(0, result.length);
	}
	
	@Test
	void givenStockHistoryBestBuyNotHighestValue_whenCalculateBestBuySell_thenReturnBestBuyAndBestSell() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(2.0);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(5.0);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(1.0);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(3.0);
		data.add(entity4);
	
		StockHistory[] expectedResult = new StockHistory[] { entity1, entity2 };

		StockHistory[] result = calculator.calculateBestBuySell(data);

		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}
	
	@Test
	void givenStockHistoryNoProfitCouldBeMade_whenCalculateBestBuySell_thenReturnEmptyResult() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(20.5);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(19.5);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(18.5);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(17.5);
		data.add(entity4);
	
		
		StockHistory[] result = calculator.calculateBestBuySell(data);
		
		assertEquals(0, result.length);
	}
	
	@Test
	void givenBestSellAndBestBuy_whenCalculateProfit_thenReturnProfit() {
		StockHistory bestBuy = new StockHistory();
		bestBuy.setClose(18.5);
		StockHistory bestSell = new StockHistory();
		bestSell.setClose(19.5);
		
		Double expectedResult = 1.0;
		
		Double result = calculator.calculateProfit(bestBuy, bestSell);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenBestSellNull_whenCalculateProfit_thenZero() {
		StockHistory bestBuy = new StockHistory();
		bestBuy.setClose(18.5);
		StockHistory bestSell = null;
		
		Double expectedResult = 0.0;
		
		Double result = calculator.calculateProfit(bestBuy, bestSell);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenBestBuyNull_whenCalculateProfit_thenZero() {
		StockHistory bestSell = new StockHistory();
		bestSell.setClose(18.5);
		StockHistory bestBuy = null;
		
		Double expectedResult = 0.0;
		
		Double result = calculator.calculateProfit(bestSell, bestBuy);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	void givenBestBuyBestSellNull_whenCalculateProfit_thenZero() {
		StockHistory bestSell = null;
		StockHistory bestBuy = null;
		
		Double expectedResult = 0.0;
		
		Double result = calculator.calculateProfit(bestSell, bestBuy);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryDataEvenSize_whenCalculateMaxProfit_thenReturnProfit() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(2.0);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(1.0);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(3.0);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(5.0);
		data.add(entity4);

		Double expectedResult = 5.0;
		
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryDataOddSize_whenCalculateMaxProfit_thenReturnProfit() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(2.0);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(1.0);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(3.0);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(5.0);
		data.add(entity4);
		StockHistory entity5 = new StockHistory();
		entity5.setClose(7.0);
		data.add(entity5);

		Double expectedResult = 9.0;
		
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryNoProfitCouldBeMade_whenCalculateMaxProfit_thenReturnZero() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(20.5);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(19.5);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(18.5);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(17.5);
		data.add(entity4);
	
		
		Double expectedResult = 0.0;
		
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryDataWithImportantOrder_whenCalculateMaxProfit_thenReturnProfit() {
		List<StockHistory> data = new ArrayList<>();
		StockHistory entity1 = new StockHistory();
		entity1.setClose(3.0);
		data.add(entity1);
		StockHistory entity2 = new StockHistory();
		entity2.setClose(8.0);
		data.add(entity2);
		StockHistory entity3 = new StockHistory();
		entity3.setClose(1.0);
		data.add(entity3);
		StockHistory entity4 = new StockHistory();
		entity4.setClose(7.0);
		data.add(entity4);
		StockHistory entity5 = new StockHistory();
		entity5.setClose(9.0);
		data.add(entity5);
		StockHistory entity6 = new StockHistory();
		entity6.setClose(22.0);
		data.add(entity6);

		Double expectedResult = 28.0;
		
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryEmpty_whenCalculateMaxProfit_thenReturnZero() {
		List<StockHistory> data = new ArrayList<>();
		
		Double expectedResult = 0.0;
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void givenStockHistoryNull_whenCalculateMaxProfit_thenReturnZero() {
		List<StockHistory> data = null;
		
		Double expectedResult = 0.0;
		Double result = calculator.calculateMaxProfit(data);
		
		assertEquals(expectedResult, result);
	}
	
}