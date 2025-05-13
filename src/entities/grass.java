package entities;

import Game.Game;
import Game.Dir;

public class grass extends entity {
    int growth = 0;


    public grass(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.game.setTerrain(x,y,'g');
        this.type = 'g';
    }

    @Override
    public void update() {
        if(!game.getEntities().contains(this)) return;
        int random = (int)(Math.random()*2);
        if(random == 1) this.growth++;
        if(this.growth == 2){
            growth = 0;
            for (Dir dr: Dir.values()) {
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
