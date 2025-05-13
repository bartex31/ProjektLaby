package entities;

import Game.Game;
import Game.Pos;

public class sheep extends animal{

    public sheep(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.name = "Sheep";
        this.type = 's';
        this.game = game;
        this.game.setMapentity(x,y,'s');
        this.targets = new char[]{'g'};
    }

}
