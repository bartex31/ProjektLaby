package entities;
import Game.Game;
import Game.Pos;

public class wolf extends animal {
    static public int ammount = 0;
    static private int id =0;
    public wolf(int x, int y, Game game) {
        this.x = x;
        id++;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.name = "Wolf" +id;
        this.type = 'w';
        this.game = game;
        this.game.setMapentity(x,y,'w');
        ammount +=1 ;
        this.targets = new char[]{'s','h'};
    }

}