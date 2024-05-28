package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallTest {

    @Test
    public void bounceWallTest() {
        Ball ball = new Ball(10, 10, 800, 5);
        boolean bounced = ball.bounce(0, 0, 0, 0, 800, 600);
        assertEquals(true,bounced);
        assertEquals(-1,ball.dx);
    }
    @Test
    public void bouncePaddleTest() {
        Ball ball = new Ball(10, 10, 400, 550);
        boolean bounced = ball.bounce(400, 550, 50, 10, 800, 600);
        assertEquals(true,bounced);
        assertEquals(-1, ball.dy);
    }
    @Test
    public void collideTest() {
        Ball ball = new Ball(10, 10, 100, 100);
        boolean collided = ball.collide(90, 90, 20, 10);
        assertEquals(true,collided);
        assertEquals(-1, ball.dy);
    }
}
