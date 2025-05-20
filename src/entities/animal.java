package entities;

import Game.Game;
import Game.Pos;

import java.util.ArrayList;

public class animal extends entity{
    public int health;
    public int food;
    boolean isHunting = false;
    char[] targets;
    int prgoress = 0;






    public void populate(char type) {

        Pos free = check(new char[]{' '}, 1);
        if (free != null) {
            food -= 40;
            prgoress = 0;
            game.spawnEntity(free.x,free.y,type); //do zmiany
        }
    }


    public void move(int ax, int ay, char type) {

        if (game.checkborder(x +ax,y+ay) && game.getEntityMap()[x +ax][y+ay] == ' ') {
            System.out.printf("move z (%d,%d) na (%d,%d)\n",x,y,x + ax, y + ay);
            game.setMapentity(x, y, ' ');

            this.x += ax;
            this.y += ay;
            game.setMapentity(x , y , type);
        }
    }

    public Pos randpos(){
        Pos pos = new Pos((int)(Math.random()*4)-1, (int)(Math.random()*4)-1);
        //System.out.println(pos.x+ " " + pos.y);
        if (game.checkborder(x+ pos.x,y+ pos.y)) {
            //System.out.println("przeszło " +pos.x+ " " + pos.y);
            return pos;
        }else{
            randpos();
        }


        return new Pos(0,0);
    }


    

    public void hunt(char[] target) {
        Pos pos = check(target,1);

        if(pos != null ){
            //System.out.println(" znaleziono ofiare "+ pos.x + " " + pos.y);
            //game.setMapentity(pos.x, pos.y, ' ');
            this.isHunting = false;
            if(this instanceof sheep){
                game.getTerrain()[pos.x][pos.y] = ' ';
                this.food += 60;
                return;
            }
            for (entity e: game.getEntities()) {
                if (e != this && pos.x == e.x && pos.y == e.y) {
                   e.die();
                   this.food += 60;
                   break;
                }
            }


            return;
        }
        pos = check(target,Math.max(game.getTerrain()[0].length, game.getTerrain().length));
        int ax = 0, ay =0;
        if (pos == null) {
            return;
        }

        if ( x  == pos.x) {
            if (y  -pos.y > 0) {
                ay = -2;
            }else ay =2;
        }else if( y == pos.y ) {
            if (x  -pos.x > 0) {
                ax = -2;
            }else ax = 2;
        }else {
            if (x > pos.x){
                ax = -2;
            }else {
                ax = 2;
            }
            if (y > pos.y){
                ay = -2;
            } else {
                ay = 2;
            }
        }
        //System.out.println("ruch w kiuerunku ofiary " +ax +" " +ay+ " "+ type + " " + pos.x + " " + pos.y);
        move(ax,ay,type);
    }
    public Pos check(char[] target, int range) {


        //System.out.println(range);
        for(int r=0;r<range ; r++){
            for (int dx = -1-r; dx <= 1+r; dx++) {
                int ax = x + dx;
                    for (int dy = -1-r; dy <= 1+r; dy++) {
                        int ay = y + dy;
                        //System.out.println(ax + " " + ay);
                            if(Math.abs(dx)<r||Math.abs(dy) <r) continue;
                            if (game.checkborder(ax, ay)) {
                                for (char t : target) {
                                    if (game.getEntityMap()[ax][ay] == t || game.getTerrain()[ax][ay] == t) {
                                        //System.out.print(game.getEntityMap()[ax][ay]);
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
        //System.out.println(this.name + " " +health + " " + food);
        if (health <= 0) {
            System.out.println("umarł prze glod" + this.name + " " + this.x + " " + this.y);
            this.die();
            return;
        }
        if (food >0) {
            food = food - 10;
        }
        if (food < 60){
            isHunting = true;
        }
        if (food < 10) health = health - 10;
        if (food >70) {
            prgoress++;
        }
        if (prgoress == 3){
            populate(type);

        }
        if (isHunting) {
            hunt(this.targets);
            return;
            //wyszukiwanie owcy i ruch w jego strone i return
        }

        Pos pos = randpos();
        move(pos.x,pos.y, type);

    }
}

