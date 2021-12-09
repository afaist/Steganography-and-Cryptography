package cryptography

fun main() {
    var isRun = true
    val steganography = Steganography()
    do {
        println("Task (hide, show, exit):")
        when (val item = readLine()!!) {
            "hide" -> {
                println("Input image file:")
                if (steganography.readImageFile(readLine()!!)) {
                    println("Output image file:")
                    val outputFile = readLine()!!
                    println("Message to hide:")
                    val message = readLine()!!
                    println("Password:")
                    val password = readLine()!!
                    steganography.hide(outputFile, message, password)
                } else {
                    continue
                }
            }
            "show" -> {
                println("Input image file:")
                val imageFile = readLine()!!
                println("Password:")
                val password = readLine()!!
                try {
                    if (steganography.readImageFile(imageFile)) {
                        steganography.show(password)
                    }
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "exit" -> {
                isRun = false
                println("Bye!")
            }
            else -> println("Wrong task: $item")
        }
    } while (isRun)
}