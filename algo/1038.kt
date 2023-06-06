class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "${`val`} ($left / $right)"
    }
}

class Solution {
    var value = 0

    fun bstToGst(root: TreeNode?): TreeNode? {
        if (root != null) {
            bstToGst(root.right)
            value += root.`val`
            root.`val` = value
            bstToGst(root.left)
        }
        return root
    }
}

//val root = TreeNode(4)
//root.left = TreeNode(1)
//root.left?.left = TreeNode(0)
//root.left?.right = TreeNode(2)
//root.left?.right?.right = TreeNode(3)
//root.right = TreeNode(6)
//root.right?.left = TreeNode(5)
//root.right?.right = TreeNode(7)
//root.right?.right?.right = TreeNode(8)

val root = TreeNode(0)
root.right = TreeNode(1)

println(Solution().bstToGst(root))
