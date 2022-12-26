package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location

class Barrier(game: Game) : GameObject(game) {
    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        var cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize
        canvas.translate(myX, myY)

        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.rgb(0, 51, 0)  // dark green
        canvas.drawRect(5f, 5f, cellSize.toFloat() - 5, cellSize.toFloat() - 5, paint)

        paint.color = Color.rgb(0, 204, 0)  // light green
        cellSize -= 5
        val radius = cellSize.toFloat() / 15
        for(i in 1..4){
            for(j in 1..4){
                canvas.drawCircle(cellSize.toFloat() * i / 5, cellSize.toFloat() * j / 5, radius, paint)
            }
        }
        paint.color = Color.rgb(0, 51, 0)  // dark green
        canvas.drawCircle(cellSize.toFloat() * 5 / 8, cellSize.toFloat() * 5 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() / 8, cellSize.toFloat() * 5 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 2 / 8, cellSize.toFloat() * 2 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 3 / 8, cellSize.toFloat() * 3 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 6 / 8, cellSize.toFloat() * 2 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 4 / 8, cellSize.toFloat() * 7 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 2 / 8, cellSize.toFloat() * 6 / 8, radius * 2, paint)
        canvas.drawCircle(cellSize.toFloat() * 6 / 8, cellSize.toFloat() * 2 / 8, radius * 2, paint)
        paint.color = Color.rgb(0, 204, 0)  // light green
        canvas.drawCircle(cellSize.toFloat() * 6 / 8, cellSize.toFloat() * 2 / 8, radius, paint)
        canvas.drawCircle(cellSize.toFloat() * 4 / 8, cellSize.toFloat() * 7 / 8, radius, paint)
        canvas.drawCircle(cellSize.toFloat() * 5 / 8, cellSize.toFloat() * 6 / 8, radius, paint)
        canvas.drawCircle(cellSize.toFloat() * 7 / 8, cellSize.toFloat() * 3 / 8, radius, paint)
    }
}