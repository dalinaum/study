## 다시 풀어볼 문제

6. 가장 긴 팰린드럼 부분 문자열 (p.159, 5)
 - https://leetcode.com/problems/longest-palindromic-substring/

8. 빗물 트래핑 (p180, 42)
 - https://leetcode.com/problems/trapping-rain-water/
 - 양쪽에서 더하는 방식과 스택 방식 모두 이해해야.

9. 세 수의 합 (p184, 15)
 - https://leetcode.com/problems/3sum/
 - 브루트 포스 방식에서 스킵할 수 있는 것. 투 포인트 모두 생각해봐야.

10. 배열 파티션 (p190, 561)
 - https://leetcode.com/problems/array-partition/
 - min을 쓰지 않고 푸는 방법을 이해해야. (파이썬만 쓸 수 있는 슬라이스 방식도 있음.)

11. 자신을 제외한 배열의 곱 (p193, 238)
 - https://leetcode.com/problems/product-of-array-except-self/

12. 주식을 사고 팔기 가장 좋은 시점 (p195, 121)
 - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 - 생각외로 쉬운 문제. 쫄지 말자.

13. 펠린드럼 연결 리스트 (p201, 234)
 - https://leetcode.com/problems/palindrome-linked-list
 - 러너를 이용하는 방법과 리스트를 이용한 방법 모두 생각해보자.

21. 중복 문자 제거 (p247, 316)
 - https://leetcode.com/problems/remove-duplicate-letters/
 - 재귀를 이용한 방법, 스택을 이용한 방법 다 해보자.

22. 일일 온도 (p252, 739)
 - https://leetcode.com/problems/daily-temperatures/
 - 브루트 포스로 하면 안됨. 스택으로

27. K개 정렬 리스트 병합 (p274, 23)
 - https://leetcode.com/problems/merge-k-sorted-lists/
 - 우선순위 큐 풀이 법 이해 필요. 파이썬와 코틀린 우선순위 큐 API 이해 필요. 파이썬에서는 중복된 값을 못 넣는다는 것 유념.
 - 리스트의 요소를 전부 풀어서 우선순위 큐에 넣지 않고 푸는 방법도 시도. Comparable 구현해야.

30. 중복 문자 없는 가장 긴 부분 무자열 (p304, 3)
 - https://leetcode.com/problems/longest-substring-without-repeating-characters/
 - 투 포인터로 풀어야 함.

32. 섬의 개수 (p331, 200)
 - https://leetcode.com/problems/number-of-islands/
 - DFS로 풀어야 함.

33. 전화 번호 문자 조합 (p338, 17)
 - https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 - DFS로 풀어야 함. 백트래킹 이해.

37. 부분 집합 (p355, 78)
 - https://leetcode.com/problems/subsets/
 - 리턴부터 하고 시작하면 코드를 더 간결하게 할 수 있음.

38. 일정 재구성 (p357, 332)
 - https://leetcode.com/problems/reconstruct-itinerary/
 - 시작 경로 주의해야 함. append 시점 주의.

39. 코스 스케쥴 (p364, 207)
 - https://leetcode.com/problems/course-schedule/
 - 가지 치기를 해야한다는 것을 인식하는게 중요.

40. 네트워크 딜레이 타임 (p373, 743)
 - https://leetcode.com/problems/network-delay-time/
 - 데이크스트라 알고리즘을 적용해야.

41. K 경유지 내 가장 저렴한 항공권 (p379, 787)
 - https://leetcode.com/problems/cheapest-flights-within-k-stops/
 - 목적지에 가격을 찾으면 종료할 것. 가격 누적과 경유지를 카운트해서 더 싸거나 더 낮은 경유지만 업데이트하게. (타임 아웃 조심)

42. 이진 트리의 최대 깊이 (p387, 104)
 - https://leetcode.com/problems/maximum-depth-of-binary-tree/
 - 재귀 버전은 생각했는데 BFS 버전도 생각해보기.

43. 이진 트리의 직경 (p390, 543)
 - https://leetcode.com/problems/diameter-of-binary-tree/
 - 루트까지 오지 않고 중간에 긴 경로가 만들어질 수 있다는 점을 유의. DFS로 풀어야.

44. 가장 긴 동일 값의 경로 (p393, 687)
 - https://leetcode.com/problems/longest-univalue-path/
 - 중간 노드는 left와 right가 있지만 상위 노드는 하나만 연결될 수 있다는 것 주의. DFS. 부모와 값이 같을 경우에는 1을 더해야 하지만 다를 경우 아예 0을 넣어야 한다는 것도 유의.

45. 이진 트리 반전 (p397, 226)
 - https://leetcode.com/problems/invert-binary-tree/
 - 재귀적인 방법 외에 DFS와 BFS 모두 풀이 해보자. DFS 후위 순회도 가능.

46. 두 이진 트리 병합 (p404, 617)
 - https://leetcode.com/problems/merge-two-binary-trees/
 - 문제를 잘 읽자.

48. 균형 이진 트리 (p413, 110)
 - https://leetcode.com/problems/balanced-binary-tree/
 - 높이 계산에서 -1을 전달해서 실패를 전달하는 아이디어.

49. 최소 높이 트리 (p416, 310)
 - https://leetcode.com/problems/minimum-height-trees/
 - 양방향이기 때문에 그래프에 값을 두개씩 넣어야 함. 값을 지워가며 평가.

50. 정렬된 배열의 이진 탐색 트리 변환 (p425, 108)
 - https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 - 이진 탐색을 할 때와 같은 방식으로 트리를 만듬.

51. 이진 탐색 트리(BST)를 더 큰 수 함계 트리로 (p428, 1038)
 - https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 - 오른쪽에서부터 중위 순회. 전역으로 누적 합을 가질 변수도 필요.

52. 이진 탐색 트리(BST) 합의 범위 (p431. 938)
 - https://leetcode.com/problems/range-sum-of-bst/
 - 가지치기를 신경써야 함. BFS, DFS 풀이 모두 가능하니 생각해야함.

53. 이진 탐색 트리(BST) 노드 간 최소거리 (p435, 783)
 - https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 - 중위 순회를 통해 처음부터 끝까지 순회하며 prev 값을 유지해야한다는 점 주의. 반복문으로 풀 수 있어야 함.

54. 전위, 중위 순회 결과로 이진 트리 구축 (p444, 105)
 - https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 - 전위의 값을 순차적으로 가져와서 중위에서 분할 정복으로 해결해야.

55. 배열의 K번째 큰 요소 (p456, 215)
 - https://leetcode.com/problems/kth-largest-element-in-an-array/
 - 정렬로 하는 풀이를 먼저 했음. 힙을 이용한 풀이도 생각해야.

56. 트라이 구현 (p451, 208)
 - https://leetcode.com/problems/implement-trie-prefix-tree/
 - 트라이를 맵으로 구현.

57. 팰린드롬 페어 (p466, 336)
 - https://leetcode.com/problems/palindrome-pairs/
 - 코틀린으로 타임 오버를 해결하는데 실패. 거꾸로 트라이를 만들어야 한다는 점과 알고리즘만 이해하자.

58. 리스트 정렬 (p489, 148)
 - https://leetcode.com/problems/sort-list/
 - 머지 소트를 해야함.

59. 구간 병합 (p497, 56)
 - https://leetcode.com/problems/merge-intervals/
 - 경계를 유념해야 함.

60. 삽입 정렬 리스트 (p500, 147)
 - https://leetcode.com/problems/insertion-sort-list/
 - 뒤에서부터 비교할 수 없는 것은 어쩔 수 없지만 항상 제일 앞부터 비교하지 말자.

61. 가장 큰 수 (p504, 179)
 - https://leetcode.com/problems/largest-number/
 - 정렬을 할 때 문자열을 기준으로 해야한다는 것을 주의해야.

63. 색 정렬 (p508, 75)
 - https://leetcode.com/problems/sort-colors/
 - 네덜란드 국기 문제 응용 풀이를 해야함.

64. 원점에 K번째로 가까운 점 (p511, 973)
 - https://leetcode.com/problems/k-closest-points-to-origin/
 - 소트 대신에 힙큐도 이용하는 것을 생각해야.

66. 회전 정렬된 배열 검색 (p525, 33)
 - https://leetcode.com/problems/search-in-rotated-sorted-array/
 - 이진 탐색으로 pivot을 찾는 것. 정렬하지 않고 이진 탐색으로 타겟을 찾는 것을 생각해봐야.

67. 두 배열의 교집합 (p529, 349)
 - https://leetcode.com/problems/intersection-of-two-arrays/
 - 한 쪽을 정렬하고 이진 탐색하거나, 양쪽 정렬하고 포인터를 진행시켜 찾음. 둘 다 알아야.

68. 두 수의 합 2 (p532, 167)
 - https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 - 브루트 포스로 했는데 투 포인터와 이진 검색으로도 풀어야 함. 이진 검색의 범위에 유의.

69. 2D 행렬 2 (p536, 240)
 - https://leetcode.com/problems/search-a-2d-matrix-ii/
 - 첫 번째 로우 마지막 컬럼에서, 이동하면서 확인하는 방법 + any 이용.

70. 싱글 넘버 (p552, 136)
 - https://leetcode.com/problems/single-number/
 - XOR을 이용해서 값이 사라지지 않는 것을 찾음.

72. 두 정수의 합 (p555, 371)
 - https://leetcode.com/problems/sum-of-two-integers/
 - 전가산기를 제대로 구현하는 것도 중요하며, 자리수를 위해 문자열로 바꾸는 것도 방법, Int.toString(2)과 String.toInt(2)같은게 원하는 대로 동작하지 않는다는 점을 유의.

74. 1비트의 개수 (p565, 191)
 - https://leetcode.com/problems/number-of-1-bits/
 - and로 1작은 값을 하는 방식으로 1의 갯수를 확인하는 방법도 알아야 함.

75. 최대 슬라이딩 윈도우 (p571, 239)
 - https://leetcode.com/problems/sliding-window-maximum/
 - 책의 풀이로 풀리지 않음. 데크를 통해 관리하고 의미없는 앞의 값 제거. 뒤의 값 중에 작은 값은 제거.

76. 부분 문자열이 포함된 최소 윈도우 (p575. 575)
 - https://leetcode.com/problems/minimum-window-substring/
 - missing 카운터과 need 맵, 투포인터로 풀이.

77. 가장 긴 반복 문자 대체 (p581, 424)
 - https://leetcode.com/problems/longest-repeating-character-replacement/
 - right - left - max_char_length == k인 것을 찾아야함. 길이만 리턴하는 것이라서 최대 길이가 아니라도 교체하면 됨. left는 한번에 한번 땡기면 됨.

78. 주식을 사고팔기 가장 좋은 시점 2 (p590, 122)
 - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 - 이전 값 보다 큰 값만 체크하면 됨. 쉬운 문제

79. 키에 따른 대기열 재구성 (p593, 406)
 - https://leetcode.com/problems/queue-reconstruction-by-height/
 - 우선순위 큐를 통해 키를 큰 순으로, 인덱스를 작은 순서대로 삽입하고 꺼냈을 때는 인덱스를 이용해서 삽입을 해야.

80. 태스크 스케쥴러 (p595, 621)
 - https://leetcode.com/problems/task-scheduler/
 - 카운트를 계산한 수, 우선순위 큐에서 한번에 n+1개씩 가져오고, 집계한 후, 카운트를 적게 해서 다시 집어넣는 방식으로 풀어야.

81. 주유소 (p599, 134)
 - https://leetcode.com/problems/gas-station/
 - 답이 하나만 있기 때문에 출발할 수 있는지 체크하고 몇 스텝만 더 살펴보기.

82. 쿠키 부여 (p603, 455)
 - https://leetcode.com/problems/assign-cookies/
 - 소트 후. 두개의 포인터로 만족하는 것을 체크하는 방법. 소트후 이진 탐색으로 하나 큰 값의 인덱스를 찾고 그게 찾은 인원보다 크면 카운트.

83. 과반수 엘리먼트 (p610, 169)
 - https://leetcode.com/problems/majority-element/
 - seen을 체크하는 방법, 분할 정복, 소트를 해서 중간을 찾는 법. (과반수라 가능) 방법등이 있음.

84. 괄호를 삽입하는 여러 가지 방법 (p616, 241)
 - https://leetcode.com/problems/different-ways-to-add-parentheses/
 - 분할 정복으로 풀어야 한다. 괄호를 기준으로 분할하고 결과를 합산.

85. 피보나치 수열 (p627, 509)
 - https://leetcode.com/problems/fibonacci-number/
 - 메모이제이션 (하향식), 타블레이션 (상향식), 변수 둘만 쓰는 버전 모두 풀 수 있어야 함.

86. 최대 서브 배열 (p636, 53)
 - https://leetcode.com/problems/maximum-subarray/
 - 책에는 메모이제이션이라고 적혀있지만 타뷸레이션인 듯. 처음부터 누적하며 더하다 이전 값이 0 이하면 새로 누적.

87. 계단 오르기 (p639, 70)
 - https://leetcode.com/problems/climbing-stairs/
 - 재귀로 풀수 있지만 타임 오버. (n-1, n-2에서 올라간 것을 합산.) 메모이제이션을 하면 됨.

88. 집 도둑 (p642, 198)
 - https://leetcode.com/problems/house-robber/
 - i번째 집의 값을 (-1)과 (-2) + i의 값을 비교해서 큰 값으로 저장. 단순 재귀는 타임오버.
