package entities;

import Game.Game;

public class sheep extends animal{
    static public int ammount = 0;
    static private int id =0;
    public sheep(int x, int y, Game game) {
        id++;
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.name = "Sheep" +id;
        this.type = 's';
        this.game = game;
        ammount +=1 ;
        this.game.setMapentity(x,y,'s');
        this.targets = new char[]{'g'};
    }

}
