package entities;

public abstract class entity {
    public int health;
    public int hunger;
    public String name;
    public int x;
    public int y;


    abstract public void update(char[][] enitities);

    abstract public void die();

}


