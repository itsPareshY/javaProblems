package com.paresh.practice.array.problems;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i= 0 ;i < prices.length ; i++){
            if(minPrice > prices[i]){
                minPrice = prices[i];
            }
            else if(maxProfit< prices[i] - minPrice){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String args[]){
        BestTimeToBuyAndSellStock obj = new BestTimeToBuyAndSellStock();
        int [] prices = {7,1,5,3,6,4};
        System.out.println(obj.maxProfit(prices));
    }
}
