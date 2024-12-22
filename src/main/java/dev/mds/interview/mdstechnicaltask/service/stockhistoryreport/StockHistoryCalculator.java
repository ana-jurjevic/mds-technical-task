package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.List;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public interface StockHistoryCalculator {
	/**
	 * For list of stock history data sorted by date ascending calculates best buy and sell dates.
	 * Returns array of StockHistory where first element is best buy date and second element is best sell date.
	 * If no dates exist such that profit is positive returns empty list.
	 * @param data StockHistory list sorted by date ascending
	 * @return array of StockHistory where first element is best buy date and second element is best sell date, or empty array is positive profit does not exist
	 */
	StockHistory[] calculateBestBuySell(List<StockHistory> data);
	/**
	 * @param bestBuy best buy stock data
	 * @param bestSell best sell stock data
	 * @return profit - difference between close price on best sell and best buy dates or 0 if parameters null
	 */
	Double calculateProfit(StockHistory bestBuy, StockHistory bestSell);
	/**
	 * @param data StockHistory list sorted by date ascending
	 * @return max profit for multiple trades over the given period or 0 if no profit can be made
	 */
	Double calculateMaxProfit(List<StockHistory> data);
	// TODO JA optional calculation
}
