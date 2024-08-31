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
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if (nums.size == 1) {
            return TreeNode(nums[0])
        } else if (nums.size == 0) {
            return null
        }
        val mid = nums.size / 2
        val node = TreeNode(nums[mid])
        node.left = sortedArrayToBST(nums.sliceArray(0 until mid))
        node.right = sortedArrayToBST(nums.sliceArray(mid + 1..nums.lastIndex))
        return node
    }
}

//val nums = intArrayOf(-10,-3,0,5,9)

//val nums = intArrayOf(1, 3)
//val nums = intArrayOf(1)
val nums = intArrayOf()


println(Solution().sortedArrayToBST(nums))
