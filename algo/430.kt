data class Node(
    var `val`: Int,
    var prev: Node? = null,
    var next: Node? = null,
    var child: Node? = null
) {
    override fun toString(): String {
        return "${`val`} -> ${next}"
    }
}

class Solution {
    fun flatten(root: Node?): Node? {
        var inputNode = root
        val stack = mutableListOf<Node>()

        val resultHead = Node(0)
        var resultNode: Node? = resultHead

        if (inputNode != null) {
            stack.add(inputNode)
        }
        while (stack.isNotEmpty()) {
            val current = stack.removeAt(stack.lastIndex)
            if (current.next != null) {
                stack.add(current.next!!)
            }
            if (current.child != null) {
                stack.add(current.child!!)
            }
            resultNode?.next = current
            current.prev = if (resultNode != resultHead) {
                resultNode
            } else {
                null
            }
            current.child = null
            resultNode = resultNode?.next
        }
        return resultHead.next
    }
}

val root = Node(1)
root.next = Node(2, root)
root.next?.next = Node(3, root.next)
root.next?.next?.next = Node(4, root.next?.next)
root.next?.next?.next?.next = Node(5, root.next?.next?.next)
root.next?.next?.next?.next?.next = Node(6, root.next?.next?.next?.next)

root.next?.next?.child = Node(7)
root.next?.next?.child?.next = Node(8, root.next?.next?.child)
root.next?.next?.child?.next?.next = Node(9, root.next?.next?.child?.next)
root.next?.next?.child?.next?.next?.next = Node(10, root.next?.next?.child?.next?.next)

root.next?.next?.child?.next?.child = Node(11)
root.next?.next?.child?.next?.child?.next = Node(12, root.next?.next?.child?.next?.child)

val result = Solution().flatten(root)
println(result)
