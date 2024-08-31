import collections
import heapq
from typing import List

# 96ms heapq로 구현
# class Solution:
#     def topKFrequent(self, nums: List[int], k: int) -> List[int]:
#         counter = collections.Counter(nums)
#         counter_heap = []
#         for i in counter:
#             heapq.heappush(counter_heap, (-counter[i], i))
#         result = []
#         for _ in range(k):
#             _, number = heapq.heappop(counter_heap)
#             result.append(number)
#         return result

# 100ms zip과 most_common을 이용한 풀이
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        return list(list(zip(*collections.Counter(nums).most_common(k)))[0])

nums = [1,1,1,2,2,3]
k = 2
z = Solution()
print(z.topKFrequent(nums, k))

nums = [1]
k = 1
print(z.topKFrequent(nums, k))

nums = [1,2]
k = 2
print(z.topKFrequent(nums, k))