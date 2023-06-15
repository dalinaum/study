import kotlin.math.max

data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

//class Solution {
//    fun maxLevelSum(root: TreeNode?): Int {
//        if (root == null) {
//            return 0
//        }
//        val sizes = mutableMapOf<Int, Int>()
//        val queue = mutableListOf<Pair<Int, TreeNode>>()
//        queue.add(1 to root)
//
//        while (queue.isNotEmpty()) {
//            val (level, node) = queue.removeAt(0)
//            val size = sizes.getOrDefault(level, 0) + node.`val`
//            sizes[level] = size
//            if (node.left != null) {
//                queue.add(level + 1 to node.left!!)
//            }
//            if (node.right != null) {
//                queue.add(level + 1 to node.right!!)
//            }
//        }
//
//        return sizes.maxBy { it.value }.key
//    }
//}

// generateSequence를 쓰는게 좋은 접근인 듯.

class Solution {
    fun maxLevelSum(root: TreeNode?): Int {
        return with(mutableListOf<TreeNode>()) {
            root?.let { add(it) }

            generateSequence {
                if (isEmpty()) {
                    null
                } else {
                    (0..lastIndex).map {
                        with(removeAt(0)) {
                            `val`.also {
                                left?.let { add(it) }
                                right?.let { add(it) }
                            }
                        }
                    }.sum()
                }
            }.withIndex()
                .map { (index, value) -> IndexedValue(index + 1, value) }
                .maxBy { it.value }
                .index
        }
    }
}

//val head = TreeNode(1)
//head.left = TreeNode(7)
//head.right = TreeNode(0)
//head.left?.left = TreeNode(7)
//head.left?.right = TreeNode(-8)

//[1,0,0,7,8,null,null]
//val head = TreeNode(1)
//head.left = TreeNode(0)
//head.right = TreeNode(0)
//head.left?.left = TreeNode(7)
//head.left?.right = TreeNode(8)

//[989,null,10250,98693,-89388,null,null,null,-32127]
val head = TreeNode(989)
head.right = TreeNode(10250)
head.right?.left = TreeNode(98693)
head.right?.right = TreeNode(-89388)
head.right?.right?.right = TreeNode(-32127)


println(Solution().maxLevelSum(head))
