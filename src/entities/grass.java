package entities;

import Game.GameLoop;
import Game.dir;

public class grass extends entity {
    int growth = 0;


    public grass(int x, int y, GameLoop game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.game.changeTerrain(x,y,'g');
    }

    @Override
    public void update() {
        int random = (int)(Math.random()*3);
        if(random == 1) this.growth++;
        if(this.growth == 2){

            die();
            growth = 0;
            random = (int)(Math.random()*4);
            for (dir dr: dir.values()) {
                int ax = x+ dr.ax;
                int ay = y+ dr.ay;
                var terrain = game.getTerrain();
                if (game.checkborder(ax, ay) && terrain[ax][ay] == ' ') {
                    game.spawnEntity(ax,ay,'g');
                }
            }
        }
    }
}
