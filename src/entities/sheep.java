package entities;

import Game.Game;
import Game.Pos;

public class sheep extends animal{
    char[] targets = {'g'};
    public sheep(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 100;
        this.name = "Sheep";
        this.type = 's';
        this.game = game;
        this.game.setMapentity(x,y,'s');
    }

    @Override
    public void update() {
        if(!game.getEntities().contains(this)) return;
        if (food >0) {
            food = food -10;
        }
        if (food < 90){

            isHunting = true;
            health = health - 10;
        }
        if (health <= 0) {
            this.die();
        }
        if (isHunting) {
            hunt(targets);
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        int rand  = (int) (Math.random() * 5);
        //gdy nie jest głodny randomi sie porusza
        if (rand == 0){
            Pos pos = randpos();
            move(pos.x,pos.y, 's');
        }
    }



}
