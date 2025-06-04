package entities;

import Game.Game;

/**
 * klasa bazowa dla wszystklich zwierząt
 */
public abstract class entity {
    /**
     * nazwa zwierzecia/obiektu
     */
    public String name;
    /**
     * pozycja x na planszy
     */
    protected int x;
    /**
     * pozycja y na planszy
     */
    protected int y;
    /**
     * referencja do obiektu symulacji
     */
    protected Game game;
    /**
     * symbol obiektu/zwierzęcia
     */
    public char type;


    abstract public void update();


    /**
     * podaje wartośc x
    * @return  x
     */
    public int getX() {
        return x;
    }
    /**
     * podaje wartośc y
     * @return  y
     */
    public int getY() {
        return y;
    }




    /**
     * zabija zwierze i zmienia licznik danego zwierzęcia i dodaje do zabityh zwierząt i usuwa z listy

     */
    public void die(){
        System.out.println("umarł " + this.name + " " + this.x + " " + this.y);
//        if (this instanceof grass){
//            game.grasseaten +=1;
//            grass.ammount --;
//        }
        if (this instanceof sheep) {
            game.sheepskilled += 1;
            sheep.ammount --;
        }
        if (this instanceof wolf) {
            game.wolfskilled += 1;
            wolf.ammount -= 1;
        }
        if (this instanceof human) {
            game.humankilled +=1;
            human.ammount -= 1;
        }

        game.despawnEntity(this);

    }
}


