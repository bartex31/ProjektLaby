package entities;

public class human extends entity {
    public human(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "human";
    }

    @Override
    public void update() {
        System.out.println("test1");
    }
    @Override
    public void die() {

    }
}
