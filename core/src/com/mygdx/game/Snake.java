package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Snake {

    enum Direction{UP, DOWN, LEFT, RIGHT}
    private Direction curDirection = Direction.RIGHT;
    private Direction pendingDirection = curDirection;
    private int eatenPizzaCount;
    private int movesCount;
    private int oldTailRowIndex;
    private int oldTailColIndex;
    private boolean hasCrashed;

    ArrayList<SnakeCell> cells;

    SnakeCell head;

    Snake(){
        cells = new ArrayList<SnakeCell>();
        cells.add(new SnakeCell(Field.COL_COUNT / 2, Field.ROW_COUNT / 2));
        cells.add(new SnakeCell(Field.COL_COUNT / 2 - 1,Field.ROW_COUNT / 2));
        head = cells.get(0);
    }

    void move(){
        if(hasCrashed){return;}

        curDirection = pendingDirection;

        int oldHeadColIndex = head.colIndex;
        int oldHeadRowIndex = head.rowIndex;

        switch (curDirection){
            case UP:    head.rowIndex++; break;
            case DOWN:  head.rowIndex--; break;
            case RIGHT: head.colIndex++; break;
            case LEFT:  head.colIndex--; break;
        }
        //if snake crashes into wall
        if(!hasValidHeadPos()){
            head.colIndex = oldHeadColIndex;
            head.rowIndex = oldHeadRowIndex;
            hasCrashed = true;

        } else{
            SnakeCell tail = cells.get(cells.size() - 1);
            oldTailColIndex = tail.colIndex;
            oldTailRowIndex = tail.rowIndex;
            tail.colIndex = oldHeadColIndex;
            tail.rowIndex = oldHeadRowIndex;
            cells.add(1, tail);
            cells.remove(cells.size() - 1);
            movesCount++;
        }
    }

    private boolean hasValidHeadPos(){

        for (SnakeCell curCell : cells){
            if (curCell == head){continue;}
            if (curCell.colIndex == head.colIndex && curCell.rowIndex == head.rowIndex){
                return false;
            }
        }

        if(head.colIndex >= 0 && head.colIndex < Field.COL_COUNT && head.rowIndex >= 0 && head.rowIndex < Field.ROW_COUNT){
            return true;
        } else{

            return false;
        }
    }

    public void draw() {
        //batch.draw(texture, 0, 0);
        for (SnakeCell curCell : cells ){
            curCell.draw();
        }

    }

    public boolean contains(int colIndex, int rowIndex) {
        for (SnakeCell curCell : cells) {
            if (colIndex == curCell.colIndex && rowIndex == curCell.rowIndex) {
                return true;
            }
        }
        return false;
    }

    public void setCurDirection(Direction newDirection) {

        if(newDirection == Direction.UP && curDirection == Direction.DOWN){return;}
        if(newDirection == Direction.DOWN && curDirection == Direction.UP){return;}
        if(newDirection == Direction.LEFT && curDirection == Direction.RIGHT){return;}
        if(newDirection == Direction.RIGHT && curDirection == Direction.LEFT){return;}
        pendingDirection = newDirection;
    }

    public boolean hasEatenPizza(Pizza pizza){
        if (head.colIndex == pizza.colIndex && head.rowIndex == pizza.rowIndex){
            eatenPizzaCount++;
            cells.add(new SnakeCell(oldTailColIndex,oldTailRowIndex));
            return true;
        } else{
            return false;
        }
    }

    public int getEatenPizzaCount() {
        return eatenPizzaCount;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public boolean isCrashed() {
        return hasCrashed;
    }
}

