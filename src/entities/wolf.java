package entities;
import Game.Game;
import Game.Pos;
/**
 * klasa wilka
 */
public class wolf extends animal {
    /**
     * liczba żywych wilków
     */
    static public int ammount = 0;
    /**
     * id wilka
     */
    static private int id =0;
    /**
     * wilk
     * @param x koordynat x
     * @param y koordynat y
     * @param game wskazanie gry
     */
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