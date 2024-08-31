from typing import List

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        result = []

        def dfs(csum: int, index: int, path: List[int]):
            if csum < 0:
                return
            if csum == 0:
                result.append(path)
                return
            for i in range(index, len(candidates)):
                dfs(csum - candidates[i], i, path + [candidates[i]])
        
        dfs(target, 0, [])
        return result

a = Solution()

candidates = [2,3,6,7]
target = 7
print(a.combinationSum(candidates, target))

candidates = [2,3,5]
target = 8
print(a.combinationSum(candidates, target))

candidates = [2]
target = 1
print(a.combinationSum(candidates, target))