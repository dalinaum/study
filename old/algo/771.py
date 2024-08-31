import collections

# 32 ms 처음 생각한 답안

# class Solution:
#     def numJewelsInStones(self, jewels: str, stones: str) -> int:
#         result = 0
#         for stone in stones:
#             if stone in jewels:
#                 result += 1
#         return result

# 40ms 책의 첫 번째 풀이. 대체 왜?
# class Solution:
#     def numJewelsInStones(self, jewels: str, stones: str) -> int:
#         freqs = {}
#         counter = 0
#         for stone in stones:
#             if stone not in freqs:
#                 freqs[stone] = 1
#             else:
#                 freqs[stone] += 1
        
#         for jewel in jewels:
#             if jewel in freqs:
#                 counter += freqs[jewel]
#         return counter

# 책의 풀이. 33ms defaultdict를 이용. 이전보다 낫지만 의아하긴 함. 리스트와 비교하는게 싫었나?
# class Solution:
#     def numJewelsInStones(self, jewels: str, stones: str) -> int:
#         freqs = collections.defaultdict(int)
#         counter = 0
#         for stone in stones:
#             freqs[stone] += 1
        
#         for jewel in jewels:
#             if jewel in freqs:
#                 counter += freqs[jewel]
#         return counter

# 36ms 책의 풀이. 이건 깔끔하고 괜찮은 듯.
class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        freqs = collections.Counter(stones)
        counter = 0
        
        for jewel in jewels:
            if jewel in freqs:
                counter += freqs[jewel]
        return counter

# 31ms 책의 풀이. 이 풀이는 좋은 듯.
class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        return sum(stone in jewels for stone in stones)

jewels = "aA"
stones = "aAAbbbb"
print(Solution().numJewelsInStones(jewels, stones))

jewels = "z"
stones = "ZZ"
print(Solution().numJewelsInStones(jewels, stones))
