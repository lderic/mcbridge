fun main() {
    (1..10).forEach {
        if (it == 2) {
            return@forEach
        }
        println(it)
    }
}