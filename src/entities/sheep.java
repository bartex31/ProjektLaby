package entities;

public class sheep extends entity{
    public sheep(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "Sheep";
    }

    @Override
    public void update(char[][] enitities) {
        //System.out.println("test1");
    }
    @Override
    public void die() {

    }
}
