fun main() {
    val list = mutableListOf<Int>()
    val n = readLine()!!.toInt()
    var length = 0
    out@ for (i in 1..n) {
        for (j in 1..i) {
            list.add(i)
            if (n == ++length) {
                break@out
            }
        }
    }
    println(list.joinToString(" "))
}
