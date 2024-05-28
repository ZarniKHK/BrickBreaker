package com.mygdx.game;

import java.awt.*;

public class Brick {
     int brick_x, brick_y;
     int width, height;
     boolean destroyed;

    // Constructor
    public Brick(int width, int height, int brick_x, int brick_y) {
        this.width = width;
        this.height = height;
        this.brick_x = brick_x;
        this.brick_y = brick_y;
        this.destroyed = false;
    }

//    public void break(){
//
//    }

}

