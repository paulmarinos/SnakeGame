package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class SnakeGame extends ApplicationAdapter implements GestureDetector.GestureListener{


    Field field;
    final float MOVE_INTERVAL = .25f;
    Timer timer;
    BitmapFont font;
    BitmapFont moves;

    @Override
	public void create () {
		Storage.batch = new SpriteBatch();
		Storage.assetManager = new AssetManager();
		Storage.loadTexture("field_cell.bmp");
		Storage.loadTexture("pizza.bmp");
		Storage.loadTexture("snake_body.bmp");
		Storage.loadTexture("snake_head.bmp");
		Storage.assetManager.finishLoading();
		Storage.snake = new Snake();
		Storage.pizza = new Pizza(Storage.snake);
		field = new Field();
		timer = new Timer(MOVE_INTERVAL);
		font = new BitmapFont();
		moves = new BitmapFont();
		font.getData().setScale(5);
		moves.getData().setScale(5);
		Gdx.input.setInputProcessor(new GestureDetector(new SnakeGame()));
	}

	@Override
	public void render () {
	    if (Gdx.input.isKeyPressed(Input.Keys.UP)){
	    	Storage.snake.setCurDirection(Snake.Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            Storage.snake.setCurDirection(Snake.Direction.DOWN);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            Storage.snake.setCurDirection(Snake.Direction.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            Storage.snake.setCurDirection(Snake.Direction.LEFT);
        }

		if (timer.isOnTime()){
			Storage.snake.move();
			if (Storage.snake.isCrashed()){
				System.out.println("Game Over");
				Storage.closeBtn = new Button("close.png");
				Storage.closeBtn.setX(Gdx.graphics.getWidth() - Storage.closeBtn.getWidth());
				Storage.replayBtn = new Button("replay.png");
				Storage.replayBtn.setX(0);
			}
			if (Storage.snake.hasEatenPizza(Storage.pizza)){
				Storage.pizza = new Pizza(Storage.snake);
			}
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Storage.batch.begin();
		field.draw();
		Storage.snake.draw();
		Storage.pizza.draw();
		font.draw(Storage.batch, "Eaten Pizzas: " + Storage.snake.getEatenPizzaCount(), 0, Gdx.graphics.getHeight());
		moves.draw(Storage.batch, "Moves: " + Storage.snake.getMovesCount(), 650, Gdx.graphics.getHeight());

		if(Storage.snake.isCrashed()){
			Storage.closeBtn.draw();
			Storage.replayBtn.draw();
		}
		Storage.batch.end();
	}

	@Override
	public void dispose () {
		Storage.batch.dispose();
		Storage.assetManager.dispose();
		font.dispose();
		moves.dispose();
		if (Storage.closeBtn != null){
			Storage.closeBtn.free();
		}
		if (Storage.replayBtn != null){
			Storage.replayBtn.free();
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		y = Gdx.graphics.getHeight() - y;
    	if(Storage.closeBtn != null && Storage.closeBtn.isClicked(x, y)){
    		System.exit(0);
		}

		if(Storage.replayBtn != null && Storage.replayBtn.isClicked(x, y)){
    		Storage.snake = new Snake();
			Storage.pizza = new Pizza(Storage.snake);
    		Storage.replayBtn.free();
    		Storage.closeBtn.free();
		}

    	return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(Math.abs(deltaX) > Math.abs(deltaY)){
			if(deltaX > 0)
				Storage.snake.setCurDirection(Snake.Direction.RIGHT);
			else Storage.snake.setCurDirection(Snake.Direction.LEFT);
		}else{
			if(deltaY > 0)
				Storage.snake.setCurDirection(Snake.Direction.DOWN);
			else Storage.snake.setCurDirection(Snake.Direction.UP);
		}

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}
}

