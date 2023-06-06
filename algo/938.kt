class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "${`val`} ($left / $right)"
    }
}

// 재귀 방식의 DFS
//class Solution {
//    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
//        if (root == null) {
//            return 0
//        }
//        val nodeVal = root.`val`
//        return  if (low <= nodeVal && nodeVal <= high) {
//            nodeVal
//        } else {
//            0
//        } + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high)
//    }
//}

// 재귀방식 DFS + 가지치기
//class Solution {
//    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
//        if (root == null) {
//            return 0
//        }
//        val nodeVal = root.`val`
//        var sum = if (low <= nodeVal && nodeVal <= high) {
//            nodeVal
//        } else {
//            0
//        }
//        if (nodeVal < high) {
//            sum += rangeSumBST(root.right, low, high)
//        }
//        if (nodeVal > low) {
//            sum += rangeSumBST(root.left, low, high)
//        }
//        return sum
//    }
//}

// 반복 방식 DFS
//class Solution {
//    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
//        val queue = mutableListOf<TreeNode?>()
//        queue.add(root)
//
//        var sum = 0
//        while (queue.isNotEmpty()) {
//            val node = queue.removeAt(0)
//            if (node == null) {
//                continue
//            }
//
//            if (low <= node.`val` && node.`val` <= high) {
//                sum += node.`val`
//            }
//            if (node.`val` < high) {
//                queue.add(node.right)
//            }
//            if (node.`val` > low) {
//                queue.add(node.left)
//            }
//        }
//        return sum
//    }
//}

// BFS
class Solution {
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        val stack = mutableListOf<TreeNode?>()
        stack.add(root)

        var sum = 0
        while (stack.isNotEmpty()) {
            val node = stack.removeAt(stack.lastIndex)
            if (node == null) {
                continue
            }

            if (low <= node.`val` && node.`val` <= high) {
                sum += node.`val`
            }
            if (node.`val` < high) {
                stack.add(node.right)
            }
            if (node.`val` > low) {
                stack.add(node.left)
            }
        }
        return sum
    }
}

val root = TreeNode(10)
root.left = TreeNode(5)
root.left?.left = TreeNode(3)
root.left?.right = TreeNode(7)
root.right = TreeNode(15)
root.right?.right = TreeNode(18)
val low = 7
val high = 15

println(Solution().rangeSumBST(root, low, high))
