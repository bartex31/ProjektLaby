package entities;

import Game.pos;

public class animal extends entity{
    public int health;
    public int food;
    boolean isHunting = false;


    public void populate(char type) {
       game.spawnEntity(x,y,type); //do zmiany
    }

    public void move(int ax, int ay, char type) {

        if (game.checkborder(x +ax,y+ay) && game.getMapemtity()[x +ax][y+ay] == ' ') {
            //System.out.printf("move z (%d,%d) na (%d,%d)\n",x,y,x + ax, y + ay);
            game.setMapentity(x, y, ' ');

            this.x += ax;
            this.y += ay;
            game.setMapentity(x , y , 'w');
        }
    }

    public pos randpos(){
        return new pos((int)(Math.random()*3)-1, (int)(Math.random()*3)-1);
    }

    public pos findnearest(char[] target){
        char[][] terrain = game.getTerrain();
        for (int range = 1; range <= Math.max(terrain.length, terrain[0].length); range++) {
            for (int dx = -range; dx <= range; dx++) {
                int ax = x + dx;
                for (int dy = -range; dy <= range; dy++) {

                    int ay = y + dy;

                    if (Math.abs(dx) == range || Math.abs(dy) == range) {
                        if (game.checkborder(ax, ay)) {
                            for(char t : target) {
                                if (terrain[ax][ay] == t) {
                                    return new pos(ax, ay);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public void hunt(char[] target) {

        pos pos = findnearest(target);
        int ax, ay;
        if (pos == null) {
            return;
        }


      //  move();
    }


    @Override
    public void update() {

    }
}

