fun solution(cacheSize: Int, cities: Array<String>): Int {
    val cache = LinkedHashSet<String>()
    var cost = 0
    for (city in cities) {
        val uppercaseCity = city.uppercase()
        if (cache.add(uppercaseCity)) {
            cost += 5
            if (cache.size > cacheSize) {
                val iterator = cache.iterator()
                cache.remove(iterator.next())
            }
        } else {
            cost += 1
            cache.remove(uppercaseCity)
            cache.add(uppercaseCity)
        }
    }
    return cost
}

//val cacheSize = 3
//val cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA")

//val cacheSize = 3
//val cities = arrayOf("Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul")

val cacheSize = 2
val cities = arrayOf("Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome")

println(solution(cacheSize, cities))
