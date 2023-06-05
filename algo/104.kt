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
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + max(maxDepth(root.left), maxDepth(root.right))
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
