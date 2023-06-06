/**
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

class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val queue = mutableListOf<TreeNode?>()
        queue.add(root)

        val serializedList = mutableListOf<Int?>()
        while (queue.isNotEmpty()) {
            val node = queue.removeAt(0)
            if (node == null) {
                serializedList.add(null)
            } else {
                serializedList.add(node.`val`)
                queue.add(node.left)
                queue.add(node.right)
            }
        }
        while (serializedList.isNotEmpty() && serializedList[serializedList.lastIndex] == null) {
            serializedList.removeAt(serializedList.lastIndex)
        }
        return serializedList.joinToString(",", "[", "]")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.length < 3) {
            return null
        }
        val onlyData = data.substring(1, data.lastIndex)
        val list = onlyData.split(",").map { string -> string.toIntOrNull() }.toMutableList()
        val queue = mutableListOf<TreeNode?>()
        val head = TreeNode(list.removeAt(0)!!)
        queue.add(head)

        while (queue.isNotEmpty()) {
            val node = queue.removeAt(0)
            if (node == null) {
                continue
            }
            val remainListSize = list.size
            if (remainListSize == 0) {
                break
            }
            if (remainListSize > 0) {
                val leftValue = list.removeAt(0)
                if (leftValue != null) {
                    val left = TreeNode(leftValue)
                    node.left = left
                    queue.add(left)
                } else {
                    queue.add(null)
                }
            }
            if (remainListSize > 1) {
                val rightValue = list.removeAt(0)
                if (rightValue != null) {
                    val right = TreeNode(rightValue)
                    node.right = right
                    queue.add(right)
                } else {
                    queue.add(null)
                }
            }
        }
        return head
    }
}

val root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.right?.left = TreeNode(4)
root.right?.right = TreeNode(5)

val serialized = Codec().serialize(root)
println(serialized)
val tree = Codec().deserialize(serialized)
println(tree)

println(Codec().deserialize("[]"))
/**
 * Your Codec object will be instantiated and called as such:
 * var ser = Codec()
 * var deser = Codec()
 * var data = ser.serialize(longUrl)
 * var ans = deser.deserialize(data)
 */
