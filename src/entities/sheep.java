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


    public void eatgrass() {
        char[][] terrain = game.getTerrain();
        for (int range = 1; range <= Math.max(terrain.length, terrain[0].length); range++) {
            for (int dx = -range; dx <= range; dx++) {
                int ax = x + dx;
                for (int dy = -range; dy <= range; dy++) {

                    int ay = y + dy;

                    if (Math.abs(dx) == range || Math.abs(dy) == range) {
                        if (game.checkborder(ax, ay)) {
                            if (terrain[ax][ay] == 'g') {
                                game.changeTerrain(ax, ay, ' ');
                                this.food += 30;
                                if (this.food > 100) this.food = 100;
                                this.isHunting = false;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    


}
