package entities;

import Game.Game;
/**
 * klasa owcy
 */
public class sheep extends animal{
    /**
     * liczba żywych owiec
     */
    static public int ammount = 0;
    /**
     * id owcy
     */
    static private int id =0;
    /**
     * Owca
     * @param x koordynat x
     * @param y koordynat y
     * @param game wskazanie gry
     */
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
