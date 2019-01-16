package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Field {

    final static int COL_COUNT = 15;
    final static int ROW_COUNT = 23;
    private Texture texture;

    public Field() {
        texture = Storage.getTexture("field_cell.bmp");
    }

    void draw() {

        for (int iCol = 0; iCol < COL_COUNT; iCol++) {
            for (int iRow = 0; iRow < ROW_COUNT; iRow++){
                Storage.batch.draw(texture, texture.getWidth() * iCol, texture.getHeight() * iRow);
            }
        }
    }
}
