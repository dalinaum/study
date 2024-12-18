from typing import List
import collections

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagrams = collections.defaultdict(list)
        for word in strs:
            anagrams[''.join(sorted(word))].append(word)
        return anagrams.values()

strs = ["eat","tea","tan","ate","nat","bat"]
print(Solution().groupAnagrams(strs))