package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import dev.mds.interview.mdstechnicaltask.model.StockHistory;

public class StockHistoryAnalysisCalculator implements StockHistoryCalculator {

	@Override
	public StockHistory[] calculateBestBuySell(List<StockHistory> data) {
		Double maxProfit = Double.NEGATIVE_INFINITY;
		int maxProfitBuyIndex = 0;
		int maxProfitSellIndex = 0;
		for (int buyIndex = 0; buyIndex < data.size() - 1; buyIndex++) {
			StockHistory buyData = data.get(buyIndex);
			for (int sellIndex = buyIndex + 1; sellIndex < data.size(); sellIndex++) {
				StockHistory sellData = data.get(sellIndex);
				double currentProfit = sellData.getClose() - buyData.getClose();
				if (currentProfit > maxProfit) {
					maxProfit = currentProfit;
					maxProfitBuyIndex = buyIndex;
					maxProfitSellIndex = sellIndex;
				}

			}
		}
		if (maxProfit > 0) {
			return new StockHistory[] { data.get(maxProfitBuyIndex), data.get(maxProfitSellIndex) };
		}
		return new StockHistory[0];
	}

	@Override
	public Double calculateProfit(StockHistory bestBuy, StockHistory bestSell) {
		if (bestBuy == null || bestSell == null) {
			return 0.0;
		}
		return bestSell.getClose() - bestBuy.getClose();
	}
	
	@Override
	public Double calculateMaxProfit(List<StockHistory> data) {
		if (data == null || data.isEmpty()) {
            return 0.0;
        }
        
        int n = data.size();
        
        Queue<State> statesToVisit = new LinkedList<>();
        Set<String> visitedStates = new HashSet<>();
        double maxProfit = 0.0;
        
        // first day possible states
        statesToVisit.add(new State(0, -data.get(0).getClose(), 1)); // buy on first day
        statesToVisit.add(new State(0, 0.0, 0)); // hold on first day
        
        while (!statesToVisit.isEmpty()) {
        	State currentState = statesToVisit.poll();
        	String currentStateKey = currentState.day + "-" + currentState.balance + "-" + currentState.stockCount; 
			if (visitedStates.contains(currentStateKey)) {
				continue;
			}
			visitedStates.add(currentStateKey);
        	if (currentState.day == n - 1 && currentState.stockCount == 0 && currentState.balance > maxProfit) {
        		maxProfit = currentState.balance;
			}
			if (currentState.day < n - 1) {
				addNextStates(data, statesToVisit, currentState);
			}
        }

        return maxProfit; 
	}

	private void addNextStates(List<StockHistory> data, Queue<State> statesToVisit, State currentState) {
		int day = currentState.day + 1;
		Double closePrice = data.get(day).getClose();

		State nextStateAfterBuying = new State(day, currentState.balance - closePrice, currentState.stockCount + 1); // buy on day
		statesToVisit.add(nextStateAfterBuying);

		State nextStateAfterHold = new State(day, currentState.balance, currentState.stockCount); // hold on day
		statesToVisit.add(nextStateAfterHold);

		if (currentState.stockCount > 0) {
			State nextStateAfterSelling = new State(day, currentState.balance + closePrice, currentState.stockCount - 1); // sell on day
			statesToVisit.add(nextStateAfterSelling);
		}
	}
	
	private static class State {
		int day;
		double balance;
		int stockCount;

		State(int day, double balance, int stockCount) {
			this.day = day;
			this.balance = balance;
			this.stockCount = stockCount;
		}

		@Override
		public int hashCode() {
			return Objects.hash(balance, day, stockCount);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			State other = (State) obj;
			return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && day == other.day
					&& stockCount == other.stockCount;
		}
	}
}