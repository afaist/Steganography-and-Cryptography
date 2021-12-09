fun main() = Array(3) { readLine()!! }.let {
    print(it.joinToString(readLine()!!.replace("NO SEPARATOR", " ")))
}