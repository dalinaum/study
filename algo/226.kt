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

// 재귀적인 풀이
// class Solution {
//     fun invertTree(root: TreeNode?): TreeNode? {
//         if (root == null) return null
//         val left = invertTree(root.right)
//         val right = invertTree(root.left)
//         root.left = left
//         root.right = right
//         return root
//     }
// }

// BFS 풀이
// class Solution {
//     fun invertTree(root: TreeNode?): TreeNode? {
//         if (root == null) return null

//         val queue = mutableListOf<TreeNode?>()
//         queue.add(root)

//         while (queue.isNotEmpty()) {
//             val node = queue.removeAt(0)
//             if (node == null) {
//                 continue
//             }
//             val right = node.left
//             val left = node.right
//             node.left = left
//             node.right = right
//             queue.add(node.left)
//             queue.add(node.right)
//         }
//         return root
//     }
// }

// DFS 풀이
// class Solution {
//     fun invertTree(root: TreeNode?): TreeNode? {
//         if (root == null) return null

//         val stack = mutableListOf<TreeNode?>()
//         stack.add(root)

//         while (stack.isNotEmpty()) {
//             val node = stack.removeAt(stack.lastIndex)
//             if (node == null) {
//                 continue
//             }
//             val right = node.left
//             val left = node.right
//             node.left = left
//             node.right = right
//             stack.add(node.left)
//             stack.add(node.right)
//         }
//         return root
//     }
// }

// DFS 후위 순
class Solution {
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val stack = mutableListOf<TreeNode?>()
        stack.add(root)

        while (stack.isNotEmpty()) {
            val node = stack.removeAt(stack.lastIndex)
            if (node == null) {
                continue
            }
            stack.add(node.left)
            stack.add(node.right)
            val right = node.left
            val left = node.right
            node.left = left
            node.right = right
        }
        return root
    }
}

var root = TreeNode(4)
root.left = TreeNode(2)
root.right = TreeNode(7)
root.left?.left = TreeNode(1)
root.left?.right = TreeNode(3)
root.right?.left = TreeNode(6)
root.right?.right = TreeNode(9)

print(Solution().invertTree(root))

//[4,2,7,1,3,6,9]
