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
    public void update(char[][] enitities) {
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
        int rand  = (int) (Math.random() * 2);

        if (rand == 0){
            rand = (int)(Math.random()*4);
            if(rand == 0 && y+1 < enitities.length){
                if (enitities[x][y+1] != 'w'){
                    enitities[x][y] = ' ';
                    enitities[x][y+1] = 'w';
                }
            }else if (rand == 1 && x+1 < enitities[0].length){
                if (enitities[x+1][y] != 'w'){
                    enitities[x][y] = ' ';
                    enitities[x+1][y] = 'w';
                }
            }else if (rand == 2 && y-1 >= 0) {
                if (enitities[x][y-1] != 'w'){
                    enitities[x][y] = ' ';
                    enitities[x][y-1] = 'w';
                }
            }else if (rand == 3 && x-1 >=0){
                if (enitities[x-1][y] != 'w'){
                    enitities[x][y] = ' ';
                    enitities[x-1][y] = 'w';
                }
            }
        }
    }

    @Override
    public void die() {

    }

}