from typing import List

class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        stack: List[int] = []
        answer = [0] * len(temperatures)

        for i, temperature in enumerate(temperatures):
            while stack and temperature > temperatures[stack[-1]]:
                last = stack.pop()
                answer[last] = i - last
            stack.append(i)

        return answer


d = Solution()
temperatures = [73,74,75,71,69,72,76,73]
print(d.dailyTemperatures(temperatures))
# Input: temperatures = [73,74,75,71,69,72,76,73]
# Output: [1,1,4,2,1,1,0,0]

temperatures = [30,40,50,60]
print(d.dailyTemperatures(temperatures))
# Input: temperatures = [30,40,50,60]
# Output: [1,1,1,0]

temperatures = [30,60,90]
print(d.dailyTemperatures(temperatures))
# Input: temperatures = [30,60,90]
# Output: [1,1,0]
 