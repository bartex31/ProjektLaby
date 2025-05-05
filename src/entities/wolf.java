package entities;
import Game.GameLoop;

public class wolf extends animal {
    char[][] map;
    boolean isHunting = false;

    public wolf(int x, int y, GameLoop game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "Wolf";
        this.game = game;
        //this.mapEmtity = game.getMapemtity();
        this.game.changeTerrain(x,y,'w');
    }

    @Override
    public void update() {
        map = game.getMapemtity();
        if (hunger>0) {
            hunger =hunger -10;
        }
        if (hunger < 30){
            isHunting = true;
        }
        if (hunger <= 10){
            health = health - 10;
        }
        if (health <= 0) {
            this.die();
        }

        int rand  = (int) (Math.random() * 2);

        if (rand == 0){
            rand = (int)(Math.random()*4);
            if(rand == 0 && y+1 < map.length){
                if (map[x][y+1] != 'w'){
                    map[x][y] = ' ';
                    map[x][y+1] = 'w';
                    this.y++;
                }
            }else if (rand == 1 && x+1 < map[0].length){
                if (map[x+1][y] != 'w'){
                    map[x][y] = ' ';
                    map[x+1][y] = 'w';
                    this.x++;
                }
            }else if (rand == 2 && y-1 >= 0) {
                if (map[x][y-1] != 'w'){
                    map[x][y] = ' ';
                    map[x][y-1] = 'w';
                    this.y--;
                }
            }else if (rand == 3 && x-1 >=0){
                if (map[x-1][y] != 'w'){
                    map[x][y] = ' ';
                    map[x-1][y] = 'w';
                    this.x--;
                }
            }
            game.setMapentity(map);
        }
    }

    @Override
    public void die() {

    }
    @Override
    public void populate(){

    }
    @Override
    public void move(int x, int y){

    }
    public void hunt(){

    }

}