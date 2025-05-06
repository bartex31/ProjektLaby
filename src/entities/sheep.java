package entities;

import Game.GameLoop;

public class sheep extends animal{

    public sheep(int x, int y,GameLoop game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "Sheep";
        this.game = game;
        this.game.changeTerrain(x,y,'s');
    }

    @Override
    public void update() {

        //System.out.println("test1");
    }
    @Override
    public void die() {

    }
    @Override
    public void populate(){

    }


}
