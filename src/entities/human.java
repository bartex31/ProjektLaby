package entities;

import Game.Game;
import Game.Pos;
public class human extends animal {
    public human(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 100;
        this.type = 'h';
        this.name = "human";
        this.game = game;
        this.game.setMapentity(x,y,'h');
        this.targets = new char[]{'s','w'};
    }


}
