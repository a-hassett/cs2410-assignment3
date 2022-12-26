package com.usu.rougelikev2.game.gameobjects


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location


class Door(game: Game?) : GameObject(game!!) {
    override fun render(canvas: Canvas, paint: Paint) {
//        things you can do in this render method for reference.
//        val coords: Location = state["coords"] // gets the location of the object in the grid
//        val cellSize: Int = game.gameState["cellSize"] // gets the size of each cell in the game
//        val myX = coords.x * cellSize // gets the current x position (in pixels) in screen space
//        val myY = coords.y * cellSize // gets the current y position (in pixels) in screen space

        val coords: Location = state["coords"]
        val cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)

        paint.color = Color.rgb(84, 41, 0) // dark brown
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.rgb(112, 56, 0) // brown
        canvas.drawRect(cellSize.toFloat() / 10, 0f, cellSize.toFloat() / 10 + 20, cellSize.toFloat(), paint)
        canvas.drawRect(cellSize.toFloat() * 3 / 10, 0f, cellSize.toFloat() * 3 / 10 + 20, cellSize.toFloat(), paint)
        canvas.drawRect(cellSize.toFloat() / 2, 0f, cellSize.toFloat() / 2 + 20, cellSize.toFloat(), paint)
        canvas.drawRect(cellSize.toFloat() * 7 / 10, 0f, cellSize.toFloat() * 7 / 10 + 20, cellSize.toFloat(), paint)
        canvas.drawRect(cellSize.toFloat() * 9 / 10, 0f, cellSize.toFloat() * 9 / 10 + 20, cellSize.toFloat(), paint)

        paint.color = Color.YELLOW
        canvas.drawCircle(cellSize.toFloat() * 4 / 5, cellSize.toFloat() / 2, 10f, paint)
        canvas.drawRect(cellSize.toFloat() * 4 / 5 - 5, cellSize.toFloat() / 2, cellSize.toFloat() * 4 / 5 + 5, cellSize.toFloat() * 3 / 4, paint)

//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 2f
//        canvas.drawRect(10f, 10f, (cellSize - 10).toFloat(), (cellSize - 10).toFloat(), paint)
    }
}