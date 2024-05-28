package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	int screen_width;
	int screen_height;
	Texture ball_img;
	Sound bounce_sound;
	Ball ball;
	Texture paddle_img;
	Paddle paddle;
	Texture brick_img;
	ArrayList<Brick> bricks;

	// The create declares the instances of Ball and Paddle
	// Input : None
	// Output : None
	@Override
	public void create () {
		batch = new SpriteBatch();

		screen_width = Gdx.graphics.getWidth();
		screen_height = Gdx.graphics.getHeight();

		// Assign ball's png, sounds and Create an object of Ball class
		bounce_sound = Gdx.audio.newSound(Gdx.files.internal("Meow1.mp3"));
		ball_img = new Texture("Ball.png");
		ball = new Ball(ball_img.getWidth(), ball_img.getHeight(),screen_width/2, screen_height/2);

		// Assign paddle's png and Create an object of Paddle class
		paddle_img = new Texture("Paddle.png");
		paddle = new Paddle(paddle_img.getWidth(), paddle_img.getHeight(), screen_width/2, 50);


		brick_img = new Texture("Brick.png");
		bricks = new ArrayList<Brick>();
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 100,350));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 200,350));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 300,350));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 400,350));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 500,350));

		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 100,400));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 200,400));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 300,400));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 400,400));
		bricks.add(new Brick(brick_img.getWidth(), brick_img.getHeight(), 500,400));

	}



	// The render method Keep the moving paddle within the screen
	// Input: None
	// Output: None
	@Override
	public void render () {
		// Set the background
		ScreenUtils.clear(0, 0, 0, 1);

		for (int i = 0; i < bricks.size(); i++) {
			Brick brick = bricks.get(i);
			if (isColliding(ball, brick)) {
				bricks.remove(i);

				if(ball.ball_x + ball.radius >= brick.brick_x && ball.ball_x - ball.radius <= brick.brick_x + brick.width
						&& ball.ball_y == brick.brick_y - brick.height/2 || ball.ball_y == brick.brick_y + brick.height/2){
					ball.dx *= -1;
				}
				else
					ball.dy *= -1;

				bounce_sound.play();
				break;
			}
		}

		batch.begin();

		batch.draw(ball_img, ball.ball_x, ball.ball_y);

		// Keep the moving paddle within the screen
		paddle.keep(Gdx.input.isKeyPressed(Input.Keys.LEFT), Gdx.input.isKeyPressed(Input.Keys.RIGHT), screen_width);
		batch.draw(paddle_img, paddle.paddle_x, paddle.paddle_y);

		// Bounce ball and Play the bounce_sound if it returns true
		boolean ball_bounce_sound = ball.bounce(paddle.paddle_x, paddle.paddle_y, paddle_img.getWidth(), paddle_img.getHeight(), screen_width, screen_height);
		if(ball_bounce_sound)
			bounce_sound.play();

		for(Brick brick: bricks){
			batch.draw(brick_img, brick.brick_x, brick.brick_y);
		}

		batch.end();
	}

	private boolean isColliding(Ball ball, Brick brick) {
		// Check if ball overlaps with brick
		return ball.ball_x < brick.brick_x + brick.width &&
				ball.ball_x + ball.radius > brick.brick_x &&
				ball.ball_y < brick.brick_y + brick.height &&
				ball.ball_y + ball.radius > brick.brick_y;
	}


	// The dispose to prevent memory loss
	// Input : None
	// Output : None
	@Override
	public void dispose () {
		batch.dispose();

		// Dispose png s of Ball and Paddle to prevent memory loss
		ball_img.dispose();
		paddle_img.dispose();
//		brick_img.dispose();
	}
}
