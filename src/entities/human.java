package entities;

import Game.GameLoop;

public class human extends animal {

    public human(int x, int y, GameLoop game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "human";
        this.game = game;
        this.game.changeTerrain(x,y,'h');
    }

    @Override
    public void update() {
       // System.out.println("test1");
    }
    @Override
    public void die() {

    }
    @Override
    public void populate(){

    }



}
