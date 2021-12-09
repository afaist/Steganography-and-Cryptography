import java.awt.Color
import java.awt.image.BufferedImage

const val IMG_SIZE = 500
const val START_POS = 100
const val LENGTH = 300
fun drawSquare() = BufferedImage(IMG_SIZE, IMG_SIZE, BufferedImage.TYPE_INT_RGB).apply {
    with(this.createGraphics()) {
        color = Color.RED
        drawRect(START_POS, START_POS, LENGTH, LENGTH)
    }
}