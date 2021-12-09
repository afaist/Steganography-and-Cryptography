fun main() {
    // put your code here
    val (h1, h2, h3) = IntArray(3) { readLine()!!.toInt() }
    println(h2 in h3..h1 || h2 in h1..h3)
}