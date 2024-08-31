data class TrieItem(
    var word: Boolean = false,
    val children: MutableMap<Char, TrieItem> = mutableMapOf()
)


class Trie() {
    val root = TrieItem()

    fun insert(word: String) {
        var node = root
        for (ch in word) {
            if (node.children.contains(ch)) {
                node = node.children.getValue(ch)
            } else {
                val newItem = TrieItem()
                node.children[ch] = newItem
                node = newItem
            }
        }
        node.word = true
    }

    fun search(word: String): Boolean {
        var node = root
        for (ch in word) {
            if (!node.children.contains(ch)) {
                return false
            }
            node = node.children.getValue(ch)
        }
        return node.word
    }

    fun startsWith(prefix: String): Boolean {
        var node = root
        for (ch in prefix) {
            if (!node.children.contains(ch)) {
                return false
            }
            node = node.children.getValue(ch)
        }
        return true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */

val trie = Trie()
trie.insert("apple")
println(trie.search("apple"))   // return True
println(trie.search("app"))     // return False
println(trie.startsWith("app")) // return True
trie.insert("app")
println(trie.search("app"))     // return True
