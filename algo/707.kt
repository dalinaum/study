data class Node(
    val `val`: Int,
    var next: Node? = null
)

class MyLinkedList() {
    var head: Node? = null

    fun get(index: Int): Int {
        var remain = index
        var node = head
        while (remain > 0 && node?.next != null) {
            node = node.next
            remain--
        }
        return if (remain != 0 || node == null) {
            -1
        } else {
            node.`val`
        }
    }

    fun addAtHead(`val`: Int) {
        head = Node(`val`, head)
    }

    fun addAtTail(`val`: Int) {
        if (head == null) {
            head = Node(`val`)
            return
        }
        var node = head
        while (node?.next != null) {
            node = node?.next
        }
        node?.next = Node(`val`)
    }

    fun addAtIndex(index: Int, `val`: Int) {
        if (index == 0) {
            return addAtHead(`val`)
        }
        var remain = index
        var node = head
        var prev: Node? = null
        while (remain > 0 && node != null) {
            prev = node
            node = node?.next
            remain--
        }
        if (remain != 0) {
            return
        }
        prev?.next = Node(`val`, node)
    }

    fun deleteAtIndex(index: Int) {
        if (index == 0) {
            head = head?.next
            return
        }
        var remain = index
        var node = head
        var prev: Node? = null
        while (remain > 0 && node?.next != null) {
            prev = node
            node = node?.next
            remain--
        }
        if (remain != 0) {
            return
        }
        prev?.next = node?.next
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(`val`)
 * obj.addAtTail(`val`)
 * obj.addAtIndex(index,`val`)
 * obj.deleteAtIndex(index)
 */

 //val s = MyLinkedList()
//s.addAtHead(7)
//println(s.head)
//s.addAtHead(2)
//println(s.head)
//s.addAtHead(1)
//println(s.head)
//s.addAtIndex(3, 0)
//println(s.head)
//s.deleteAtIndex(2)
//println(s.head)
//s.addAtHead(6)
//println(s.head)
//s.addAtTail(4)
//println(s.head)
//println(s.get(4))
//s.addAtHead(4)
//println(s.head)
//s.addAtIndex(5, 0)
//println(s.head)
//s.addAtHead(6)
//println(s.head)

val s = MyLinkedList()
println(s.head)
s.addAtTail(1)
println(s.head)
s.addAtTail(3)
println(s.head)
println(s.get(1))
