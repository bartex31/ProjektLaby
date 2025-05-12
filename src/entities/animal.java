package entities;

import Game.Pos;

public class animal extends entity{
    public int health;
    public int food;
    boolean isHunting = false;
    char type;


    public void populate(char type) {
       game.spawnEntity(x,y,type); //do zmiany
    }

    public void move(int ax, int ay, char type) {

        if (game.checkborder(x +ax,y+ay) && game.getEntityMap()[x +ax][y+ay] == ' ') {
            //System.out.printf("move z (%d,%d) na (%d,%d)\n",x,y,x + ax, y + ay);
            game.setMapentity(x, y, ' ');

            this.x += ax;
            this.y += ay;
            game.setMapentity(x , y , type);
        }
    }

    public Pos randpos(){
        return new Pos((int)(Math.random()*3)-1, (int)(Math.random()*3)-1);
    }

    public Pos findnearest(char[] target){
        char[][] terrain = game.getTerrain();
        for (int range = 1; range <= Math.max(terrain.length, terrain[0].length); range++) {
            for (int dx = -range; dx <= range; dx++) {
                int ax = x + dx;
                for (int dy = -range; dy <= range; dy++) {

                    int ay = y + dy;

                    if (Math.abs(dx) == range || Math.abs(dy) == range) {
                        if (game.checkborder(ax, ay)) {
                            for(char t : target) {
                                if (game.getEntityMap()[ax][ay] == t || terrain[ax][ay] == t) {
                                    return new Pos(ax, ay);
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
        Pos pos = game.check(x,y, target);

        if(pos != null){
            System.out.println(" znaleziono ofiare "+ pos.x + " " + pos.y);
            //game.setMapentity(pos.x, pos.y, ' ');
            this.isHunting = false;
            for (entity e: game.getEntities()) {
                if (pos.x == e.x && pos.y == e.y) {
                   e.die();
                   break;
                }
            }

            this.food += 60;
            return;
        }
        pos = findnearest(target);
        int ax = 0, ay =0;

        if (pos == null) {

            return;
        }
        if ( x  == pos.x) {
            if (y  -pos.y > 0) {
                ay = -1;
            }else ay =1;
        }else if( y == pos.y ) {
            if (x  -pos.x > 0) {
                ax = -1;
            }else ax = 1;
        }else {
            if (x > pos.x){
                ax = -1;
            }else {
                ax = 1;
            }
            if (y > pos.y){
                ay = -1;
            } else {
                ay = 1;
            }

        }
        System.out.println("szukanie " +ax +" " +ay+ " "+ type);
        move(ax,ay,type);
    }


    @Override
    public void update() {

    }
}

