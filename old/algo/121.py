import sys
from typing import List

# class Solution:
#     def maxProfit(self, prices: List[int]) -> int:
#         profit = 0
#         min_price = sys.maxsize

#         for price in prices:
#             if min_price > price:
#                 min_price = price
#             else:
#                 current_profit = price - min_price
#                 if current_profit > profit:
#                     profit = current_profit
#         return profit

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        profit = 0
        min_price = sys.maxsize

        for price in prices:
            min_price = min(min_price, price)
            profit = max(profit, price - min_price)

        return profit


prices = [7,1,5,3,6,4]
print(Solution().maxProfit(prices))
5
prices = [7,6,4,3,1]
0
print(Solution().maxProfit(prices))