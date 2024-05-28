package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle {

    int paddle_x, paddle_y;
    int width, height;


    // This initialises the Paddle
    // Input : width   this is the width of paddle
    // Input : height   this is the height of paddle
    // Output : None
    public Paddle(int width, int height, int paddle_x, int paddle_y){   // Build the Constructor
        this.width = width;
        this.height = height;
        this.paddle_x = paddle_x;
        this.paddle_y = paddle_y;
    }

    // keep method to Move the paddle while Keeping it within the screen
    // Input : paddle_left   this is whether the user presses the left key
    // Input : paddle_right    this is whether the user presses the right key
    // Input : screenWidth    this is the width of the screen
    // Output : None
    public void keep(boolean paddle_left, boolean paddle_right, int screenWidth){
        if(paddle_left)
            paddle_x = Math.max(0, paddle_x-6);   // Limit the left movement of the paddle
        if(paddle_right)
            paddle_x = Math.min(screenWidth - this.width, paddle_x+6);  // Limit the right movement of the paddle
    }
}
