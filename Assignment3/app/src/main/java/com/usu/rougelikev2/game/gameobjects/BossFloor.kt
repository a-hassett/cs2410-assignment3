package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location


class BossFloor(game: Game?) : GameObject(game!!) {
    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        val cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)

        paint.color = Color.GRAY
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.WHITE
        val edge = cellSize.toFloat() / 5
        val spot = cellSize.toFloat() / 4
        canvas.drawRect(0f, 0f, edge, edge, paint)
        canvas.drawRect(spot * 3, spot, cellSize.toFloat(), spot + edge, paint)
        canvas.drawRect(spot, spot * 3, spot + edge, cellSize.toFloat(), paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawRect(spot * 2, 0f, spot * 2 + edge, edge, paint)
        canvas.drawRect(spot, spot, spot + edge, spot + edge, paint)
        canvas.drawRect(0f, spot * 2, edge, spot * 2 + edge, paint)
        canvas.drawRect(spot * 2, spot * 2, spot * 2 + edge, spot * 2 + edge, paint)
        canvas.drawRect(spot * 3, spot * 3, cellSize.toFloat() - 5, cellSize.toFloat() - 5, paint)

    }
}