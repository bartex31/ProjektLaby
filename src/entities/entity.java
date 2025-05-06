package entities;

import Game.GameLoop;

import java.awt.*;

public abstract class entity {

    public String name;
    protected int x;
    protected int y;
    public char[][] mapEmtity;
    protected GameLoop game;


    abstract public void update();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void die(){
        game.despawnEntity(this);
    }
}


