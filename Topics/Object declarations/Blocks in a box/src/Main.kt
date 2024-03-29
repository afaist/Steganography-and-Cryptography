const val BLOCK_LENGTH = 6
const val BLOCK_WIDTH = 4

data class Block(val color: String) {
    object BlockProperties {
        var length: Int = BLOCK_LENGTH
        var width: Int = BLOCK_WIDTH
        fun blocksInBox(length: Int, width: Int) = length / this.length * (width / this.width)
    }
}
