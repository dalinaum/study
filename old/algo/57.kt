// 타임 오버가 난 풀이. 파이썬 풀이와 알고리즘이 유사한데 풀리지 않음.

data class TrieItem(
    var wordId: Int = -1,
    val children: MutableMap<Char, TrieItem> = mutableMapOf(),
    val palindromeWordIds: MutableList<Int> = mutableListOf()
)

fun CharSequence.isPalandrome(): Boolean {
    val list = this.toMutableList()
    while (list.size > 1) {
        val first = list.removeAt(0)
        val last = list.removeAt(list.lastIndex)
        if (first != last) {
            return false
        }
    }
    return true
}

// 타임 오버가 난 풀이. 파이썬 풀이와 알고리즘이 유사한데 풀리지 않음.

class Trie {
    val root = TrieItem()
    val result = mutableListOf<List<Int>>()

    fun insert(index: Int, word: String) {
        var node = root
        for ((i, ch) in word.reversed().withIndex()) {
            if (word.substring(0, word.length - i).isPalandrome()) {
                node.palindromeWordIds.add(index)
            }
            node.children.putIfAbsent(ch, TrieItem())
            node = node.children.getValue(ch)
        }
        node.wordId = index
    }

    fun search(index: Int, word: String) {
        var node = root

        for ((i, ch) in word.withIndex()) {
            if (node.wordId >= 0) {
                if (word.substring(i, word.length).isPalandrome()) {
                    result.add(mutableListOf(index, node.wordId))
                }
            }
            if (!node.children.contains(ch)) {
                return
            }
            node = node.children.getValue(ch)
        }
        if (node.wordId >= 0 && index != node.wordId) {
            result.add(mutableListOf(index, node.wordId))
        }
        for (id in node.palindromeWordIds) {
            result.add(listOf(index, id))
        }
    }
}

class Solution {
    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val trie = Trie()
        for ((i, word) in words.withIndex()) {
            trie.insert(i, word)
        }
        val result = mutableListOf<List<Int>>()
        for ((i, word) in words.withIndex()) {
            trie.search(i, word)
        }
        return trie.result
    }
}

val words = arrayOf("abcd", "dcba", "lls", "s", "sssll")
//val words = arrayOf("bat","tab","cat")
//val words = arrayOf("a", "")



println(Solution().palindromePairs(words))
