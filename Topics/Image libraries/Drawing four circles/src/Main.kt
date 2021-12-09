import java.awt.Color
import java.awt.image.BufferedImage

const val IMG_SIZE = 200
const val CENTER = 50
const val STEP = 25
const val RADIUS = 100
fun drawCircles() = BufferedImage(IMG_SIZE, IMG_SIZE, BufferedImage.TYPE_INT_RGB).apply {
    with(this.createGraphics()) {
        color = Color.RED
        drawOval(CENTER, CENTER, RADIUS, RADIUS)
        color = Color.YELLOW
        drawOval(CENTER, CENTER + STEP, RADIUS, RADIUS)
        color = Color.GREEN
        drawOval(CENTER + STEP, CENTER, RADIUS, RADIUS)
        color = Color.BLUE
        drawOval(CENTER + STEP, CENTER + STEP, RADIUS, RADIUS)
    }
}
