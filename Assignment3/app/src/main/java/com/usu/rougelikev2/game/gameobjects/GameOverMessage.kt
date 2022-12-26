package com.usu.rougelikev2.game.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.usu.rougelikev2.game.gameengine.Game
import com.usu.rougelikev2.game.gameengine.GameObject


class GameOverMessage(game: Game?) : GameObject(game!!) {
    var fontSize = 10f
    override fun update(elapsedTime: Long){
        super.update(elapsedTime)
        val ratio = elapsedTime / 1000000f / 5000f
        if(fontSize <= 100f){
            fontSize += 100f * ratio
        }
    }
    
    override fun render(canvas: Canvas, paint: Paint) {
        val isPlaying: Boolean = game.gameState["playing"]
        if (isPlaying) return

        paint.color = Color.RED
        canvas.drawRect(0f, game.height, game.width, 0f, paint)

        paint.color = Color.rgb(112, 56, 0) // brown
        canvas.drawRect(game.width / 4, game.height, game.width / 4 + 50, game.height / 3, paint)
        canvas.drawRect(game.width * 3 / 4 - 50, game.height, game.width * 3 /4, game.height / 3, paint)

        paint.color = Color.YELLOW
        canvas.drawRect(game.width / 8, game.height / 3 + 50, game.width * 7 / 8, game.height / 3 + 350, paint)
        canvas.drawRect(game.width / 8, game.height * 2 / 3 - 50, game.width * 7 / 8, game.height * 2 / 3 + 250, paint)

        paint.color = Color.RED
        paint.textSize = 200f
        canvas.drawText("GAME", game.width / 2 - 275, game.height / 2, paint)
        canvas.drawText("OVER", game.width / 2 - 275, game.height / 2 + 440, paint)
    }
}