package entities;

import Game.Game;

public class grass extends entity {
    static public int ammount = 0;

    public grass(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.name = "grass";
        this.game.setTerrain(x,y,'g');
        this.type = 'g';
        ammount +=1 ;
    }

    @Override
    public void update() {
//        if(!game.getEntities().contains(this)) return;
//        int random = (int)(Math.random()*3);
//        if(random == 1) this.growth++;
//        if(this.growth == 5){
//            growth = 0;
//            for (Dir dr: Dir.values()) {
//                int ax = x+ dr.ax;
//                int ay = y+ dr.ay;
//                var terrain = game.getTerrain();
//                if (game.checkborder(ax, ay) && terrain[ax][ay] == ' ') {
//                    game.spawnEntity(ax,ay,'g');
//                }
//            }
//        }
    }
}
