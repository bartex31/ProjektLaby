package entities;

public class wolf extends entity {
    boolean isHunting = false;
    public wolf(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.hunger = 100;
        this.name = "Wolf";
    }

    @Override
    public void update() {
        System.out.println("test");
        if (hunger>0) {
            hunger =hunger -10;
        }
        if (hunger < 30){
            isHunting = true;
        }
        if (hunger <= 10){
            health = health - 10;
        }
        if (health <= 0) {
            this.die();
        }
    }

    @Override
    public void die() {

    }

}