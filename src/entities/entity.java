package entities;

import Game.Game;

public abstract class entity {
    public String name;
    protected int x;
    protected int y;
    protected Game game;


    abstract public void update();
    public void move(int ax, int ay) {

    }
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


