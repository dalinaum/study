/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// 재귀 버전

// class Solution {
//     fun maxDepth(root: TreeNode?): Int {
//         if (root == null) return 0
//         return 1 + max(maxDepth(root.left), maxDepth(root.right))
//     }
// }

// BFS 버전

class Solution {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val queue = mutableListOf<TreeNode>(root)

        var depth = 0
        while (queue.isNotEmpty()) {
            depth += 1

            val length = queue.size
            for (i in 0 until(length)) {
                val node = queue.removeAt(0)
                if (node.left != null) {
                    queue.add(node.left as TreeNode)
                }
                if (node.right != null) {
                    queue.add(node.right as TreeNode)
                }
            }
        }
        return depth
    }
}

//val head = TreeNode(3)
//head.left = TreeNode(9)
//head.right = TreeNode(20)
//head.right?.left = TreeNode(15)
//head.right?.right = TreeNode(7)
//[3,9,20,null,null,15,7]

val head = TreeNode(1)
head.right = TreeNode(2)
//[1,null,2]

println(Solution().maxDepth(head))
