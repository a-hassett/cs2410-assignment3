package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location


class Key(game: Game?) : GameObject(game!!) {
    override fun render(canvas: Canvas, paint: Paint) {
        val isActive: Boolean = state["active"]
        if (!isActive) return
        val coords: Location = state["coords"]
        val cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)

        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.YELLOW
        canvas.drawCircle(cellSize.toFloat() / 4, cellSize.toFloat() / 4, cellSize.toFloat() / 6, paint)

        paint.color = Color.WHITE
        canvas.drawCircle(cellSize.toFloat() / 4, cellSize.toFloat() / 4, cellSize.toFloat() / 10, paint)

        paint.color = Color.YELLOW
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 15f
        val startX: Float = cellSize.toFloat() * 3 / 8
        val startY: Float = cellSize.toFloat() * 3 / 8
        val stopX: Float = cellSize.toFloat() * 3 / 4
        val stopY: Float = cellSize.toFloat() * 3 / 4

        canvas.drawLine(startX, startY, stopX + 10, stopY + 10, paint)
        canvas.drawLine(stopX, stopY, stopX - 15, stopY + 15, paint)
        canvas.drawLine(stopX - 15, stopY - 15, stopX - 30, stopY, paint)
}

    init {
        state["active"] = true
    }
}