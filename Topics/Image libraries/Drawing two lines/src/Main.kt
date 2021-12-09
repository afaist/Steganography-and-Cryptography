import java.awt.Color
import java.awt.image.BufferedImage

const val IMG_HEIGHT = 200
const val IMG_WIDTH = 200

fun drawLines() = BufferedImage(IMG_HEIGHT, IMG_WIDTH, BufferedImage.TYPE_INT_RGB).apply {
    with(this.createGraphics()) {
        color = Color.RED
        drawLine(0, 0, IMG_HEIGHT, IMG_WIDTH)
        color = Color.GREEN
        drawLine(IMG_WIDTH, 0, 0, IMG_HEIGHT)
    }
}