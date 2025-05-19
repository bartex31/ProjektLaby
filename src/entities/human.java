package entities;

import Game.Game;

public class human extends animal {
    static public int ammount = 0;
    public human(int x, int y, Game game) {
        super();
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.type = 'h';
        this.name = "human";
        this.game = game;
        ammount +=1 ;
        this.game.setMapentity(x,y,'h');
        this.targets = new char[]{'s','w'};
    }
    public void addammount(){
        ammount+=1;
    }


}
