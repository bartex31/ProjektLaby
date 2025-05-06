package entities;

import Game.GameLoop;

public class animal extends entity{
    public int health;
    public int hunger;


    @Override
    public void update() {

    }


    
    public void populate() {

    }

    
    public void move(int ax, int ay, char type) {
        game.setMapentity(x,y, ' ');
        game.setMapentity(x +ax,y+ay, 'w');
        this.x += ax;
        this.y += ay;
    }
}
