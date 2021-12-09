package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.experimental.xor


/**
 * The class implements hiding information in a graphic file
 * and reading hidden information from a graphic file
 *
 */
class Steganography {
    private val endStringCharList = "000000000000000000000011".toCharArray().toList()
    private var inputImage: BufferedImage? = null
    private var inputImageFile: String? = null
    private var outputImageFile: String? = null
    fun readImageFile(fileName: String): Boolean {
        inputImageFile = fileName
        val file = File(inputImageFile!!)
        return try {
            inputImage = ImageIO.read(file)
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    /**
     * hides information in a graphic file
     *
     * @param toFile
     * @param message
     */
    fun hide(toFile: String, message: String, password: String) {
        outputImageFile = toFile
        try {
            val msg = encryptMessage(message.toByteArray(Charsets.UTF_8), password)
            messageToBitChars(msg).also {
                bitsToImage(it)
            }
            saveImage()
            println("Message saved in $outputImageFile image.")
        } catch (e: Exception) {
            println(e.message)
        }
    }

    /**
     * Encrypts the message using a password and xor operation
     *
     * @param message
     * @param password
     * @return encrypted message
     */
    private fun encryptMessage(message: ByteArray, password: String): ByteArray {
        if (password.isNotEmpty()) {
            val pswByte = password.toByteArray(Charsets.UTF_8)
            for ((i, byte) in message.withIndex()) {
                val j = i % pswByte.size
                message[i] = byte xor pswByte[j]
            }
        }
        return message
    }

    /**
     * Converts the message to a string of bits and adds a sign of the end of the message
     *
     * @param message
     * @return
     */
    private fun messageToBitChars(message: ByteArray): CharArray {
        val bitsets = ArrayList<String>()
        message.forEach { i ->
            bitsets.add(i.toString(2).padStart(8, '0'))
        }
        return bitsets.joinToString("").toCharArray() + endStringCharList
    }

    /**
     * Encodes a string of bits in a graphic file
     *
     * @param bitsChars
     */
    private fun bitsToImage(bitsChars: CharArray) {
        val width = inputImage!!.width
        val height = inputImage!!.height
        if (width * height < bitsChars.size) {
            throw ExceptionNotEnoughSpace()
        }
        //outputImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        var x: Int
        var y: Int
        for ((i, bit) in bitsChars.withIndex()) {
            if (bit != '0' && bit != '1') {
                throw ExceptionBadSymbolInString()
            }
            x = i % width
            y = i / width
            val rgb = inputImage!!.getRGB(x, y)
            var blue: Int = rgb and 0xff
            blue = ((blue shr 1) shl 1) or bit.digitToInt()
            val green: Int = rgb and 0xff00 shr 8
            val red: Int = rgb and 0xff0000 shr 16
            val newColor = Color(red, green, blue)
            inputImage!!.setRGB(x, y, newColor.rgb)
        }
    }

    /**
     * Outputs a hidden message from a graphic file
     *
     */
    fun show(password: String) {
        val width = inputImage!!.width
        val height = inputImage!!.height
        val sizeEnd = endStringCharList.size
        val bitsets = mutableListOf<Char>()
        var isMessageFound = false
        loop@ for (y in 0 until height) {
            for (x in 0 until width) {
                val rgb = inputImage!!.getRGB(x, y)
                val blue: Int = rgb and 0xff
                bitsets.add(((blue and 1) + '0'.code).toChar())
                if (bitsets.size >= sizeEnd) {
                    if (bitsets.subList(bitsets.size - sizeEnd, bitsets.size) == endStringCharList) {
                        isMessageFound = true
                        break@loop
                    }
                }
            }
        }
        if (isMessageFound)
            decode(bitsets.subList(0, bitsets.size - sizeEnd).joinToString(""), password)
        else {
            throw ExceptionNotFoundMessage()
        }
    }

    /**
     * Converts a string of zeros and ones to text
     *
     * @param charsOfBits
     */
    private fun decode(charsOfBits: String, password: String) {
        val bytes = mutableListOf<Byte>()
        for (i in charsOfBits.indices step 8) {
            try {
                bytes.add(charsOfBits.substring(i, i + 8).toByte(2))
            } catch (e: Exception) {
                println(e.message)
            }
        }
        val message = encryptMessage(bytes.toByteArray(), password)
        println("Message:")
        println(message.toString(Charsets.UTF_8))
    }

    /**
     * Save image to file
     *
     */
    private fun saveImage() {
        if (inputImage != null && outputImageFile != null) {
            ImageIO.write(inputImage, "png", File(outputImageFile!!))
        }
    }
}