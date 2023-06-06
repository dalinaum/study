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

import kotlin.math.max
import kotlin.math.abs

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "${`val`} ($left / $right)"
    }
}

class Solution {
    fun isBalanced(root: TreeNode?): Boolean {
        fun check(node: TreeNode?): Int {
            if (node == null) {
                return 0
            }
            val left = check(node.left)
            val right = check(node.right)
            if (left == -1 || right == -1 || abs(left - right) > 1) {
                return -1
            }
            return max(left, right) + 1
        }
        return check(root) != -1
    }
}

//val root = TreeNode(3)
//root.left = TreeNode(9)
//root.right = TreeNode(20)
//root.right?.left = TreeNode(15)
//root.right?.right = TreeNode(7)

val root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(2)
root.left?.left = TreeNode(3)
root.left?.right = TreeNode(3)
root.left?.left?.left = TreeNode(4)
root.left?.left?.right = TreeNode(4)

println(Solution().isBalanced(root))
