fun main() {
    val cups = readLine()!!.toInt()
    val isWeekend = readLine()!!.toBoolean()
    println(
        isWeekend && cups in 15..25 || !isWeekend && cups in 10..20
    )
}