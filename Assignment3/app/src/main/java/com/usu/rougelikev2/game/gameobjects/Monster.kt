package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject
import com.usu.rougelikev2.game.gameengine.Location
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt


class Monster(game: Game?) : GameObject(game!!) {
    var turnNumber = 0
    override fun update(elapsedTime: Long) {
        val isAlive = state.get<Boolean>("alive")
        val turn = game.gameState.get<String>("turn")
        if (turn !== "monster") return
        game.gameState["endTurn"] = true
        if (!isAlive) return
        turnNumber++
        if (turnNumber % 4 == 0) {
            return
        }
        val map = game.gameState.get<Array<Array<GameObject?>>>("map")
        if (checkForPlayer() < 5) {
            val player = game.getGameObjectWithTag("player")
            val playerLocation: Location = player!!.state.get("coords")
            val myLocation: Location = state.get("coords")
            if (myLocation.x != playerLocation.x && myLocation.y != playerLocation.y) {
                if (myLocation.y < playerLocation.y) {
                    val other = map[myLocation.y.toInt() + 1][myLocation.x.toInt()]
                    if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.y = myLocation.y + 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                if (myLocation.y > playerLocation.y) {
                    val other = map[myLocation.y.toInt() - 1][myLocation.x.toInt()]
                    if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.y = myLocation.y - 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                if (myLocation.x < playerLocation.x) {
                    val other = map[myLocation.y.toInt()][myLocation.x.toInt() + 1]
                    if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt() + 1] = null
                        myLocation.x = myLocation.x + 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                if (myLocation.x > playerLocation.x) {
                    val other = map[myLocation.y.toInt()][myLocation.x.toInt() - 1]
                    if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.x = myLocation.x - 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                moveRandom()
            } else if (myLocation.x === playerLocation.x) { // same column
                if (myLocation.y < playerLocation.y) {
                    val other = map[myLocation.y.toInt() + 1][myLocation.x.toInt()]
                    if (other is Player) {
                        // end the game
                        other.state["alive"] = false
                        game.gameState["playing"] = false
                        return
                    } else if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.y = myLocation.y + 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                if (myLocation.y > playerLocation.y) {
                    val other = map[myLocation.y.toInt() - 1][myLocation.x.toInt()]
                    if (other is Player) {
                        // end the game
                        other.state["alive"] = false
                        game.gameState["playing"] = false
                        return
                    } else if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.y = myLocation.y - 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                moveRandom()
            } else if (myLocation.y === playerLocation.y) { // same row
                if (myLocation.x < playerLocation.x) {
                    val other = map[myLocation.y.toInt()][myLocation.x.toInt() + 1]
                    if (other is Player) {
                        // end the game
                        other.state["alive"] = false
                        game.gameState["playing"] = false
                        return
                    } else if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.x = myLocation.x + 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                if (myLocation.x > playerLocation.x) {
                    val other = map[myLocation.y.toInt()][myLocation.x.toInt() - 1]
                    if (other is Player) {
                        // end the game
                        other.state["alive"] = false
                        game.gameState["playing"] = false
                        return
                    } else if (other == null) {
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                        myLocation.x = myLocation.x - 1
                        map[myLocation.y.toInt()][myLocation.x.toInt()] = this
                        return
                    }
                }
                moveRandom()
            }
        } else {
            moveRandom()
        }
    }

    private fun moveRandom() {
        val neighbors = mutableListOf<Int>()
        neighbors.add(1)
        neighbors.add(2)
        neighbors.add(3)
        neighbors.add(4)
        neighbors.shuffle()
        val map = game.gameState.get<Array<Array<GameObject?>>>("map")
        val myLocation: Location = state.get("coords")
        while (!neighbors.isEmpty()) {
            val `val` = neighbors.removeAt(0)
            if (`val` == 1) {
                if (myLocation.y > 0 && map[myLocation.y.toInt() - 1][myLocation.x.toInt()] == null) {
                    map[myLocation.y.toInt() - 1][myLocation.x.toInt()] = this
                    map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                    myLocation.y = myLocation.y - 1
                    return
                }
            }
            if (`val` == 2) {
                if (myLocation.x < map[0].size - 1 && map[myLocation.y.toInt()][myLocation.x.toInt() + 1] == null) {
                    map[myLocation.y.toInt()][myLocation.x.toInt() + 1] = this
                    map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                    myLocation.x = myLocation.x + 1
                    return
                }
            }
            if (`val` == 3) {
                if (myLocation.y < map.size - 1 && map[myLocation.y.toInt() + 1][myLocation.x.toInt()] == null) {
                    map[myLocation.y.toInt() + 1][myLocation.x.toInt()] = this
                    map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                    myLocation.y = myLocation.y + 1
                    return
                }
            }
            if (`val` == 4) {
                if (myLocation.x > 0 && map[myLocation.y.toInt()][myLocation.x.toInt() - 1] == null) {
                    map[myLocation.y.toInt()][myLocation.x.toInt() - 1] = this
                    map[myLocation.y.toInt()][myLocation.x.toInt()] = null
                    myLocation.x = myLocation.x - 1
                    return
                }
            }
        }
    }

    private fun checkForPlayer(): Double {
        val player = game.getGameObjectWithTag("player")
        val playerLocation: Location = player!!.state.get("coords")
        val myLocation: Location = state.get("coords")
        return sqrt(
            (playerLocation.x - myLocation.x).toDouble().pow(2.0) + (playerLocation.y - myLocation.y).toDouble()
                .pow(2.0)
        )
    }

    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        val cellSize: Int = game.gameState["cellSize"]
        val myX = coords.x.toInt() * cellSize
        val myY = coords.y.toInt() * cellSize
        val isAlive: Boolean = state["alive"]

        canvas.translate(myX.toFloat(), myY.toFloat())
        if (isAlive) {
            paint.color = Color.WHITE
            canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

            paint.color = Color.rgb(85, 0, 128)  // purple
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 10f
            canvas.drawCircle(cellSize.toFloat() * 2 / 5 - 10, cellSize.toFloat() * 2 / 5, cellSize.toFloat() / 10, paint)
            canvas.drawCircle(cellSize.toFloat() * 3 / 5 + 10, cellSize.toFloat() * 2 / 5, cellSize.toFloat() / 10, paint)
            canvas.drawLine(cellSize.toFloat() * 2 / 5 + 10, cellSize.toFloat() - 30f, cellSize.toFloat() * 3 / 5 + 10,cellSize.toFloat() - 30f, paint)

            paint.style = Paint.Style.FILL
            paint.color = Color.BLACK
            canvas.drawRect(cellSize.toFloat() / 8, 30f, cellSize.toFloat() * 3 / 8 + 10, 60f, paint)
            canvas.drawRect(cellSize.toFloat() * 5 / 8 - 10, 30f, cellSize.toFloat() * 7 / 8, 60f, paint)

        } else {
            paint.color = Color.GRAY
            canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)

            paint.color = Color.rgb(85, 0, 128)  // purple
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 10f
            canvas.drawLine(cellSize.toFloat() * 1 / 5, cellSize.toFloat() * 2 / 5, cellSize.toFloat() * 2 / 5, cellSize.toFloat() * 2 / 5, paint)
            canvas.drawLine(cellSize.toFloat() * 3 / 5, cellSize.toFloat() * 2 / 5, cellSize.toFloat() * 4 / 5, cellSize.toFloat() * 2 / 5, paint)
            canvas.drawCircle(cellSize.toFloat() / 2, cellSize.toFloat() * 3 / 4, 15f, paint)

            paint.style = Paint.Style.FILL
            paint.color = Color.BLACK
            canvas.drawRect(cellSize.toFloat() / 8, 30f, cellSize.toFloat() * 3 / 8 + 5, 55f, paint)
            canvas.drawRect(cellSize.toFloat() * 5 / 8 - 5, 30f, cellSize.toFloat() * 7 / 8, 55f, paint)

        }
    }

    init {
        state["alive"] = true
        turnNumber = (Math.random() * 4).toInt()
    }
}