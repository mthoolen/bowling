package nl.codesquad.demo.bowling

class Line(rolls: String) {
    private val frames = rolls.split("|").map { frameOf(it) }

    fun getScore(): Int = frames.map { it.getScore() }.sum()

    private fun frameOf(string: String): Frame {
        val scores = string.split(",")
        return Frame(scores[0], scores.getOrNull(1), scores.getOrNull(2))
    }

    private inner class Frame(val first: String, val second: String?, val third: String?) {
        fun getScore(): Int = when {
                isStrike() -> getPins() + nextRolls()
                isSpare() -> getPins() + nextRoll()
                else -> getPins()
            }

        private fun getNextFrame(frame: Frame?) = frame?.let { frames.getOrNull(frames.indexOf(it) + 1) }

        private fun nextRoll(): Int = getNextFrame(this)?.first.score()

        private fun nextRolls(): Int {
            val nextFrame = getNextFrame(this)
            if(nextFrame?.isSpare() == true) return 10
            return nextFrame?.first.score() + (nextFrame?.second ?: getNextFrame(nextFrame)?.first).score()
        }

        private fun getPins(): Int = when {
            isStrike() -> first.score() + second.score() + third.score()
            isSpare() -> second.score() + third.score()
            isSpare() -> second.score() + third.score()
            else -> first.score() + second.score()
        }

        private fun isSpare() = this.second == "/"
        private fun isStrike() = this.first == "X"
    }
}

private fun String?.score(): Int = when (this) {
    "X" -> 10
    "/" -> 10
    "-" -> 0
    else -> this?.toInt() ?: 0
}

