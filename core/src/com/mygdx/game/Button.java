package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Button {
    private Texture texture;
    private int x;
    private int y;

    Button(String textureName){
        texture = new Texture(textureName);
    }

    public void free(){
        texture.dispose();
    }

    public void draw(){
        Storage.batch.draw(texture, x, y);
    }

    public boolean isClicked(float touchX, float touchY){
        if(touchX >= x && touchX <= Gdx.graphics.getWidth() && touchY >= y && touchY <= y + texture.getHeight()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth(){
        return texture.getWidth();
    }

}
