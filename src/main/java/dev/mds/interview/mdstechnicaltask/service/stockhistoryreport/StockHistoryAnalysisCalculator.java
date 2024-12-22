package dev.mds.interview.mdstechnicaltask.service.stockhistoryreport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        
        List<State> statesToVisit = new ArrayList<>();
        List<Double> possbileProfits = new ArrayList<>();
        
        // first day possible states
        statesToVisit.add(new State(0, -data.get(0).getClose(), 1)); // buy on first day
        statesToVisit.add(new State(0, 0.0, 0)); // hold on first day
        
        while (!statesToVisit.isEmpty()) {
        	State currentState = statesToVisit.remove(0);
			if (currentState.day == n - 1 && currentState.stockCount == 0 && currentState.balance > 0.0) {
				possbileProfits.add(currentState.balance);
			}
			if (currentState.day < n - 1) {
				addNextStates(data, statesToVisit, currentState);
			}
        }

        return possbileProfits.stream().max(Double::compareTo).orElse(0.0); 
	}

	private void addNextStates(List<StockHistory> data, List<State> statesToVisit, State currentState) {
		// buy on day
		State nextStateAfterBuying = new State(currentState.day+1, currentState.balance-data.get(currentState.day+1).getClose(), currentState.stockCount+1);
		if (!statesToVisit.contains(nextStateAfterBuying)) {
			statesToVisit.add(nextStateAfterBuying);
		}
		 // hold on day
		State nextStateAfterHold = new State(currentState.day+1, currentState.balance, currentState.stockCount);
		if (!statesToVisit.contains(nextStateAfterHold)) {
			statesToVisit.add(nextStateAfterHold);
		}
		if (currentState.stockCount > 0) {
			State nextStateAfterSelling = new State(currentState.day+1, currentState.balance+data.get(currentState.day+1).getClose(), currentState.stockCount-1); // sell on day
			if (!statesToVisit.contains(nextStateAfterSelling)) {
				statesToVisit.add(nextStateAfterSelling);
			}
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