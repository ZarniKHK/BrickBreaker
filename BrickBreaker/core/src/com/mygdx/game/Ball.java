package com.mygdx.game;

public class Ball {
    int radius;
    int ball_x, ball_y;
    int dx = 1, dy = 1;

    // This initialises the ball
    // Input width: this is the width of the ball
    // Input Height : this is the height of the ball
    // Output : None
    public Ball(int width, int height, int ball_x, int ball_y){
        this.radius = (width + height) / 4;
        this.ball_x = ball_x;
        this.ball_y = ball_y;
    }


    public boolean bounce (int paddle_x, int paddle_y, int paddleWidth, int paddleHeight, int screenWidth, int screenHeight) {

        // Get the ball moving
        ball_x += dx;
        ball_y += dy;

        // Bounce the ball when it touches the left or right screen,
        if(ball_x <= 0 || ball_x >= screenWidth - radius) {
            dx *= -1;
            return true;      // return true to play bounce_sound in Gdx
        }

        // Bounce the ball when it touches the upper screen or paddle
        if( (ball_y >= paddle_y - paddleHeight && ball_y <= paddle_y + paddleHeight
                && ball_x >= paddle_x-paddleWidth && ball_x <= paddle_x+paddleWidth)
        || (ball_y >= screenHeight - radius) ) {
            dy *= -1;
            return true;     // return true to play bounce_sound in Gdx
        }
        return false;
    }

    public boolean collide (int brick_x, int brick_y, int brick_width, int brick_height){
        int brick_right = brick_x + brick_width;
        int brick_bottom = brick_y + brick_height;

        // Check if the ball collides with the brick
        if (this.ball_x >= brick_x - brick_width  && this.ball_x <= brick_x + brick_width
                && (this.ball_y == brick_y - brick_height || this.ball_y == brick_y + brick_height)) {
            this.dy *= -1;
            return true;
        }

        if(this.ball_y >= brick_y && this.ball_y <= brick_y + brick_height
            && (this.ball_x == brick_x || this.ball_x == brick_x + brick_width)){
            this.dy *= -1;
            return true;
        }
        return false;
    }

}
