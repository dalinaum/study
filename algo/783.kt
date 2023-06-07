class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "${`val`} ($left / $right)"
    }
}

// 재귀버전
//class Solution {
//    var minimum = Int.MAX_VALUE
//    var prev = Int.MIN_VALUE
//
//    fun minDiffInBST(root: TreeNode?): Int {
//        if (root == null) {
//            return minimum
//        }
//        if (root.left != null) {
//            minDiffInBST(root.left)
//        }
//        if (prev != Int.MIN_VALUE) {
//            minimum = min(minimum, root.`val` - prev)
//        }
//        prev = root.`val`
//        if (root.right != null) {
//            minDiffInBST(root.right)
//        }
//        return minimum
//    }
//}

class Solution {
    fun minDiffInBST(root: TreeNode?): Int {
        var minimum = Int.MAX_VALUE
        var prev = Int.MIN_VALUE
        var node = root
        val stack = mutableListOf<TreeNode>()

        while (stack.isNotEmpty() || node != null) {
            while (node != null) {
                stack.add(node)
                node = node.left
            }

            node = stack.removeAt(stack.lastIndex)

            if (prev != Int.MIN_VALUE) {
                minimum = min(minimum, node.`val` - prev)
            }
            prev = node.`val`
            node = node.right
        }
        return minimum
    }
}

//[4,2,6,1,3]
val root = TreeNode(4)
root.left = TreeNode(2)
root.left?.left = TreeNode(1)
root.left?.right = TreeNode(3)
root.right = TreeNode(6)

//[1,0,48,null,null,12,49]
//val root = TreeNode(1)
//root.left = TreeNode(0)
//root.right = TreeNode(48)
//root.right?.left = TreeNode(12)
//root.right?.right = TreeNode(49)

//[90,69,null,49,89,null,52]
//val root = TreeNode(90)
//root.left = TreeNode(69)
//root.left?.left = TreeNode(49)
//root.left?.left?.right = TreeNode(52)
//root.left?.right = TreeNode(89)

println(Solution().minDiffInBST(root))
