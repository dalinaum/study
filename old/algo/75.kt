class Solution {
    fun sortColors(nums: IntArray): Unit {
        val mid = 1
        var red = 0
        var white = 0
        var blue = nums.lastIndex

        while (white <= blue) {
            if (nums[white] < mid) {
                val temp = nums[white]
                nums[white] = nums[red]
                nums[red] = temp
                red++
                white++
            } else if (nums[white] > mid) {
                val temp = nums[blue]
                nums[blue] = nums[white]
                nums[white] = temp
                blue--
            } else {
                white++
            }
        }
    }
}

//val list = intArrayOf(2,0,2,1,1,0)
val list = intArrayOf(2,0,1)
Solution().sortColors(list)
println(list.contentToString())
