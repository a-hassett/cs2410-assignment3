package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Input
import com.usu.rougelikev2.game.gameengine.Location


class Player(game: Game?) : GameObject(game!!) {
    override fun update(elapsedTime: Long) {
        val isAlive = state.get<Boolean>("alive")
        if (!isAlive) return
        if (game.gameState.get<String>("turn") === "player" && !game.inputQueue.isEmpty()) {
            val input: Input = game.inputQueue.removeAt(0)
            game.clearInputQueue()
            val cellSize = game.gameState.get<Int>("cellSize")
            val numRows = game.gameState.get<Int>("numRows")
            val maxY = cellSize * numRows
            if (input.location.y >= maxY) return
            val row = input.location.y.toInt() / cellSize
            val col = input.location.x.toInt() / cellSize
            val myLocation: Location = state["coords"]
            // check if neighbor
            if (myLocation.x.toInt() == col && (myLocation.y.toInt() == row + 1 || myLocation.y.toInt() == row - 1) ||
                myLocation.y.toInt() == row && (myLocation.x.toInt() == col + 1 || myLocation.x.toInt() == col - 1)
            ) {
                //check what we tapped on
                val map = game.gameState.get<Array<Array<GameObject?>>>("map")
                if (map[row][col] is Barrier) return  // don't move to barriers
                game.gameState["endTurn"] = true
                if (map[row][col] == null) {
                    map[row][col] = this
                    map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                    myLocation.x = col.toFloat()
                    myLocation.y = row.toFloat()
                }
                else if (map[row][col] is Key) {
                    map[row][col]!!.state["active"] = false
                    state["hasKey"] = true
                    map[row][col] = null
                }
                else if (map[row][col] is Monster) {
                    map[row][col]!!.state["alive"] = false
                    map[row][col] = null
                }
                else if (map[row][col] is BossMonster) {
                    val health = map[row][col]!!.state.get<Int>("health")
                    state["hasKey"] = true
                    if (health == 1) { //
                        map[row][col]!!.state["alive"] = false
                    } else {
                        map[row][col]!!.state["health"] = health - 1
                    }
                }
                val hasKey = state.get<Boolean>("hasKey")
                if (map[row][col] is Door && hasKey) {
                    game.gameState["nextLevel"] = true
                }
            }
        }
    }

    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        val cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)

        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

        paint.color = Color.rgb(21, 128, 0)  // green
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas.drawCircle(cellSize.toFloat() * 2 / 5 - 10, cellSize.toFloat() * 2 / 5, cellSize.toFloat() / 10, paint)
        canvas.drawCircle(cellSize.toFloat() * 3 / 5 + 10, cellSize.toFloat() * 2 / 5, cellSize.toFloat() / 10, paint)
        canvas.drawCircle(cellSize.toFloat() / 2, cellSize.toFloat() * 4 / 5, cellSize.toFloat() / 5 - 5, paint)

        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        canvas.drawRect(cellSize.toFloat() / 8, 15f, cellSize.toFloat() * 3 / 8 + 5, 40f, paint)
        canvas.drawRect(cellSize.toFloat() * 5 / 8 - 5, 15f, cellSize.toFloat() * 7 / 8, 40f, paint)
    }

    init {
        state["hasKey"] = false
        state["alive"] = true
    }
}
