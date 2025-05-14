package entities;
import Game.Game;

public class wolf extends animal {
    public wolf(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.name = "Wolf";
        this.type = 'w';
        this.game = game;
        this.game.setMapentity(x,y,'w');
        this.allowedTargets = new char[]{'s','h'};
    }

}