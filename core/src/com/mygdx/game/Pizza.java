package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Pizza {

    int colIndex;
    int rowIndex;
    private Texture texture;

    Pizza(Snake snake){
        Random random = new Random();
        System.out.println("!");
        do{
            colIndex = random.nextInt(Field.COL_COUNT);
            rowIndex = random.nextInt(Field.ROW_COUNT);
        } while (snake.contains(colIndex, rowIndex));

        texture = Storage.getTexture("pizza.bmp");

    }

    public void draw() {

        Storage.batch.draw(texture, texture.getWidth() * colIndex, texture.getHeight() * rowIndex);
    }
}
