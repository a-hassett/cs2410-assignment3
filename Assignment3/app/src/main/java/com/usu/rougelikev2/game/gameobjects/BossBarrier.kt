package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location


class BossBarrier(game: Game?) : GameObject(game!!) {
    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        val cellSize: Float = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)

        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.rgb(255, 119, 51)  // orange
        canvas.drawRect(5f, 5f, cellSize - 5, cellSize - 5, paint)

        paint.color = Color.RED
        canvas.drawCircle(cellSize / 3, cellSize / 3, cellSize / 4, paint)
        canvas.drawCircle(cellSize * 2 / 3, cellSize / 2, cellSize / 4, paint)
        canvas.drawCircle(cellSize * 2 / 5, cellSize * 2 / 3, cellSize / 4, paint)

        paint.color = Color.YELLOW
        canvas.drawCircle(cellSize * 4 / 8, cellSize / 3, cellSize / 10, paint)
        canvas.drawCircle(cellSize * 6 / 8, cellSize * 4 / 5, cellSize / 10, paint)

        paint.color = Color.rgb(255, 119, 51)  // orange
        canvas.drawCircle(cellSize * 3 / 5, cellSize / 4, cellSize / 10, paint)
        canvas.drawCircle(cellSize / 4, cellSize * 2 / 3, cellSize / 10, paint)
        canvas.drawCircle(cellSize * 4 / 5, cellSize * 2 / 3, cellSize / 10, paint)
    }
}