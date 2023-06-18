class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.map { it.sum() }.max()
    }
}

//val accounts = arrayOf(intArrayOf(1,2,3),intArrayOf(3,2,1))

//val accounts = arrayOf(intArrayOf(1, 5), intArrayOf(7, 3), intArrayOf(3, 5))

val accounts = arrayOf(intArrayOf(2,8,7), intArrayOf(7,1,3),intArrayOf(1,9,5))
println(Solution().maximumWealth(accounts))
