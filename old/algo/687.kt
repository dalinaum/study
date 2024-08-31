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

    override fun toString(): String {
        return "${`val`} ($left / $right)"
    }
}

class Solution {
    fun longestUnivaluePath(root: TreeNode?): Int {
        var longest = 0
        fun dfs(node: TreeNode?): Int {
            if (node == null) {
                return 0
            }
            var left = dfs(node.left)
            var right = dfs(node.right)
            left = if (node.left?.`val` == node.`val`) {
                left + 1
            } else {
                0
            }
            right = if (node.right?.`val` == node.`val`) {
                right + 1
            } else {
                0
            }
            longest = max(longest, left + right)
            return max(left, right)
        }
        dfs(root)
        return longest
    }
}

//[5,4,5,1,1,null,5]
val head = TreeNode(5)
head.left = TreeNode(4)
head.right = TreeNode(5)
head.left?.left = TreeNode(1)
head.left?.right = TreeNode(1)
head.right?.right = TreeNode(5)

println(Solution().longestUnivaluePath(head))
