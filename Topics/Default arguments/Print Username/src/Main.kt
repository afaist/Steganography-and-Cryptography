fun main() {
    readLine()!!.run {
        if (this == "HIDDEN") {
            sayUserHello()
        } else {
            sayUserHello(this)
        }
    }
}

fun sayUserHello(name: String = "secret user") = print("Hello, $name!")
