package entities;

import Game.pos;

public class animal extends entity{
    public int health;
    public int food;
    pos target;
    boolean isHunting = false;


    public void populate(char type) {
       game.spawnEntity(x,y,type); //do zmiany
    }

    public void move(int ax, int ay, char type) {
        if (game.checkborder(x +ax,y+ay)) {
            game.setMapentity(x, y, ' ');
            game.setMapentity(x + ax, y + ay, 'w');
            this.x += ax;
            this.y += ay;
        }
    }

    public pos randpos(){
        return new pos((int)(Math.random()*3)-1, (int)(Math.random()*3)-1);
    }

    public pos findnearest(char type){
        int ax, ay;
        return new pos(1, 1);

    }

    @Override
    public void update() {

    }
}

