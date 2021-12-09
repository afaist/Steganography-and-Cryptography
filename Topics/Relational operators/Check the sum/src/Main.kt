fun main() {
    val (a, b, c) = IntArray(3) { readLine()!!.toInt() }
    println(if (a + b == 20 || a + c == 20 || b + c == 20) "true" else "false")
}