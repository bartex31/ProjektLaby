package entities;

import Game.Game;

public abstract class entity {
    public String name;
    protected int x;
    protected int y;
    protected Game game;
    public char type;


    abstract public void update();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void die(){
        System.out.println("umarł " + this.name + " " + this.x + " " + this.y);
        game.despawnEntity(this);
    }
}


