package entities;

import Game.Game;
import Game.pos;
public class human extends animal {

    public human(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 100;
        this.name = "human";
        this.game = game;
        this.game.changeTerrain(x,y,'h');
    }

    @Override
    public void update() {
        if (food >0) {
            food = food -10;
        }
        if (food < 30){
            isHunting = true;
            health = health - 20;
        }
        if (health <= 0) {
            this.die();
        }
        if (isHunting) {
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        int rand  = (int) (Math.random() * 1);
        //gdy nie jest głodny randomi sie porusza
        if (rand == 0){
            pos pos = randpos();
            move(pos.x,pos.y, 'h');
        }
    }

    public void poluj(){
        //szukaj i poluj na owce wilki
    }

}
