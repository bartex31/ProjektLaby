package entities;
import Game.GameLoop;
import Game.pos;

public class wolf extends animal {
    char[][] map;
    boolean isHunting = false;

    public wolf(int x, int y, GameLoop game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "Wolf";
        this.game = game;
        //this.mapEmtity = game.getMapemtity();
        this.game.changeTerrain(x,y,'w');
    }

    @Override
    public void update() {
        map = game.getMapemtity();
        if (hunger>0) {
            hunger =hunger -10;
        }
        if (hunger < 30){
            isHunting = true;
        }
        if (hunger <= 10){
            health = health - 10;
        }
        if (health <= 0) {
            this.die();
        }
        if (isHunting) {
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        int rand  = (int) (Math.random() * 1);

        if (rand == 0){
            pos pos = randpos();
            move(pos.x,pos.y, 'w');
        }
    }
    public void hunt(){

    }

}