package entities;

import Game.GameLoop;
import Game.pos;
import java.sql.Struct;

public class animal extends entity{
    public int health;
    public int hunger;


    @Override
    public void update() {

    }


    
    public void populate(char type) {
       game.spawnEntity(x,y,type);
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

}


}

