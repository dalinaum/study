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

class Solution {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var longest = 0
        fun dfs(root: TreeNode?): Int {
            if (root == null) return -1
            val left = dfs(root.left)
            val right = dfs(root.right)
            longest = max(longest, left + right + 2)
            return max(left, right) + 1
        }

        dfs(root)
        return longest
    }
}

//root = [1,2,3,4,5]
val head = TreeNode(1)
head.left = TreeNode(2)
head.right = TreeNode(3)
head.left?.left = TreeNode(4)
head.left?.right = TreeNode(5)

println(Solution().diameterOfBinaryTree(head))
