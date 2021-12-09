import java.awt.Color
import java.awt.image.BufferedImage

const val IMG_SIZE = 200
const val START_POS = 50
const val TEXT = "Hello, images!"
fun drawStrings() = BufferedImage(IMG_SIZE, IMG_SIZE, BufferedImage.TYPE_INT_RGB).apply {
    with(this.createGraphics()) {
        val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE)
        for (i in 0..2) {
            color = colors[i]
            drawString(TEXT, START_POS + i, START_POS + i)
        }
    }
}