package entities;

import Game.Pos;

import java.util.ArrayList;

public class animal extends entity{
    public int health;
    public int food;
    boolean isHunting = false;
    char[] allowedTargets;

    entity target;

    int prgoress = 0;




    public void populate(char type) {
        Pos free = check(new char[]{' '}, 1);
        if (free != null) {
            game.spawnEntity(free.x,free.y,type); //do zmiany
        }
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

    public Pos randpos(int range){
        return new Pos((int)(Math.random()*(range+3))-range, (int)(Math.random()*(range+3))-range);
    }


    

    public void hunt(char[] allowedTarget) {
        Pos pos = check(allowedTarget,1);

        if(pos != null){
            //System.out.println(" znaleziono ofiare "+ pos.x + " " + pos.y);
            //game.setMapentity(pos.x, pos.y, ' ');
            this.isHunting = false;
            ArrayList<entity> entities = game.getEntities();
            for (entity e: entities) {
                if (e != this && pos.x == e.x && pos.y == e.y) {
                   e.die();
                   this.food += 60;
                   target = null;
                   break;
                }
            }


            return;
        }
        if(target == null ){
            pos = check(allowedTarget,Math.max(game.getTerrain()[0].length, game.getTerrain().length));
            if (pos == null) {
                return;
            }
            for (entity e: game.getEntities()) {
                if (e != this && pos.x == e.x && pos.y == e.y) {
                    target = e;
                    break;
                }
            }
        }



        int ax = 0, ay =0;


        if ( x  == target.x) {
            if (y  -target.y > 0) {
                ay = -1;
            }else ay =1;
        }else if( y == target.y ) {
            if (x  -target.x > 0) {
                ax = -1;
            }else ax = 1;
        }else {
            if (x > target.x){
                ax = -1;
            }else {
                ax = 1;
            }
            if (y > target.y){
                ay = -1;
            } else {
                ay = 1;
            }
        }
        //System.out.println("ruch w kiuerunku ofiary " +ax +" " +ay+ " "+ type + " " + target.x + " " + target.y);
        move(ax,ay,type);
    }
    public Pos check(char[] target, int range) {
        for(int r=0;r<range ; r++){
            for (int dx = -1-r; dx <= 1+r; dx++) {
                int ax = x + dx;
                    for (int dy = -1-r; dy <= 1+r; dy++) {
                        int ay = y + dy;
                            if(Math.abs(dx)<r||Math.abs(dy) <r) continue;
                            if (game.checkborder(ax, ay)) {
                                for (char t : target) {
                                    if (game.getEntityMap()[ax][ay] == t || game.getTerrain()[ax][ay] == t) {
                                        return new Pos(ax, ay);
                                    }
                                }
                            }
                    }
            }
        }
        return null;
    }


    @Override
    public void update() {
        if(!game.getEntities().contains(this)) return;
        if (health <= 0) {
            System.out.println("umarł prze glod" + this.name + " " + this.x + " " + this.y);
            this.die();
            return;
        }
        if (food >0) {
            food = food -10;
        }
        if (food < 60){
            isHunting = true;
        }
        if (food < 0) health = health - 10;
        if (food >70) {
            prgoress++;
        }
        if (prgoress == 3){
            populate(type);
            food -= 40;
        }
        if (isHunting) {
            hunt(this.allowedTargets);
            return;
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        int rand  = (int) (Math.random() * 5);
        //gdy nie jest głodny randomi sie porusza
        if (rand == 0){
            Pos pos = randpos(3);
            move(pos.x,pos.y, type);
        }
    }
}

