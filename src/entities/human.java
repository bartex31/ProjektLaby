package entities;

import Game.Game;

/**
 * klasa człowieka
 */
public class human extends animal {
    /**
     * liczba żywych ludzi
     */
    static public int ammount = 0;
    /**
     * id człowieka
     */
    static private int id =0;
    /**
     * Człowiek
     * @param x koordynat x
     * @param y koordynat y
     * @param game wskazanie gry
     */
    public human(int x, int y, Game game) {
        super();
        id++;
        this.x = x;
        this.y = y;
        this.health = 100;
        this.food = 50;
        this.type = 'h';
        this.name = "human" +id;
        this.game = game;
        ammount +=1 ;
        this.game.setMapentity(x,y,'h');
        this.targets = new char[]{'s','w'};
    }

}
