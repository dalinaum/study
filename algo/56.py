from typing import List

# 146ms

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        merged = []
        for pair in sorted(intervals, key=lambda x: x[0]):
            if merged and pair[0] <= merged[-1][1]:
                merged[-1][1] = max(pair[1], merged[-1][1])
            else:
                merged += pair,
        return merged


s = Solution()
intervals = [[1,3],[2,6],[8,10],[15,18]]
print(s.merge(intervals))

intervals = [[1,4],[4,5]]
print(s.merge(intervals))