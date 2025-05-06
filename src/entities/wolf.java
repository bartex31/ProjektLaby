package entities;
import Game.Game;
import Game.pos;

public class wolf extends animal {
    public wolf(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 100;
        this.name = "Wolf";
        this.game = game;
        this.game.setMapentity(x,y,'w');
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

        int rand  = (int) (Math.random() * 5);
        //gdy nie jest głodny randomi sie porusza
        if (rand == 0){
            //System.out.println(x + " " + y );
            pos pos = randpos();
            move(pos.x,pos.y, 'w');
        }
    }
    public void hunt(){

    }

}