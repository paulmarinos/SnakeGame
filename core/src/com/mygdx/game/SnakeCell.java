package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeCell {

    int colIndex;
    int rowIndex;
    private Texture texture;

    SnakeCell(int colIndex, int rowIndex){
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
    }

    void draw() {
        loadTexture();
        Storage.batch.draw(texture, texture.getWidth() * colIndex, texture.getHeight() * rowIndex);
    }

    private void loadTexture() {
        if (texture == null){
            if (Storage.snake.head == this){
                texture = Storage.getTexture("snake_head.bmp");
            } else {
                texture = Storage.getTexture("snake_body.bmp");
            }
        }
    }


}
