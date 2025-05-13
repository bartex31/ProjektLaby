package entities;

import Game.Game;
import Game.Pos;
public class human extends animal {
    char[] targets = {'s','w'};
    public human(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 100;
        this.type = 'h';
        this.name = "human";
        this.game = game;
        this.game.setMapentity(x,y,'h');
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
            move(pos.x,pos.y, 'h');
        }
    }

}
