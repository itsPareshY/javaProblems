package com.paresh.practice.array.problems;

public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        int currPrice = prices[0];
        int maxProfit = 0;

        for (int i= 1 ;i < prices.length ; i++){
            if(currPrice < prices[i]){
                maxProfit = maxProfit + prices[i] - currPrice;
            }
            currPrice = prices[i];
        }
        return maxProfit;
    }
    public static void main(String args[]){
        BestTimeToBuyAndSellStockII obj = new BestTimeToBuyAndSellStockII();
        int [] prices = {7,1,5,3,6,4};
        System.out.println(obj.maxProfit(prices));
    }
}
