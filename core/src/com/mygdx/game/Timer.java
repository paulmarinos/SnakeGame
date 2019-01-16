package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Timer{

    float secElapsed;
    float interval;

    Timer(float interval){
        this.interval = interval;
    }

    boolean isOnTime(){
        secElapsed += Gdx.graphics.getDeltaTime();
        if (secElapsed >= interval){
            secElapsed -= interval;
            return true;
        }
        else{
            return false;
        }
    }
}