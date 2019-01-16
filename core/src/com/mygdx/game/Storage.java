package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Storage {

    static SpriteBatch batch;
    static AssetManager assetManager;
    static Snake snake;
    static Pizza pizza;
    static Button closeBtn;
    static Button replayBtn;

    public static void loadTexture(String textureName){
        assetManager.load(textureName, Texture.class);
    }

    public static Texture getTexture(String textureName){
        return Storage.assetManager.get(textureName, Texture.class);
    }
}


