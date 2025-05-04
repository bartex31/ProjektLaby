
import java.util.ArrayList;
import java.util.Scanner;
import entities.*;

public class GameLoop {
    public char[][] map;
    public char[][] mapEmtity;
    int sizeMapx, sizeMapy;
    ArrayList<grass> grass = new ArrayList<grass>();
    ArrayList<entity> entities = new ArrayList<entity>();


    public static void main(String[] args) {
        GameLoop g = new GameLoop();
        g.sizeMapx = 40;
        g.sizeMapy = 40;
        g.map = new char[g.sizeMapx][g.sizeMapy];
        g.mapEmtity = new char[g.sizeMapx][g.sizeMapy];

        g.worldGen();
        g.entityGen();
        g.gameLopp();

    }
    private void worldGen() {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                int rand = (int) (Math.random() *5);
                map[x][y] = ' ';
                mapEmtity[x][y] = ' ';
                if (rand == 1) {
                    grass.add(new grass(x,y, map));
                }
            }
        }
    }
    private void entityGen() {
        int wolf = 40;
        int human =10;
        int sheep = 20;
        int randx, randy;

        for (int x = 0; x < wolf; x++) {
            randx = (int) (Math.random() *sizeMapx);
            randy = (int) (Math.random() *sizeMapy);
            entities.add(new wolf(randx,randy));
            mapEmtity[randx][randy] = 'w';

        }
        for (int x = 0; x < human; x++) {
            randx = (int) (Math.random() *sizeMapx);
            randy = (int) (Math.random() *sizeMapy);
            entities.add(new human(randx,randy));
            mapEmtity[randx][randy] = 'h';
        }
        for (int x = 0; x < sheep; x++) {
            randx = (int) (Math.random() *sizeMapx);
            randy = (int) (Math.random() *sizeMapy);
            entities.add(new sheep(randx,randy));
            mapEmtity[randx][randy] = 's';
        }
    }

    static void addEntity(){

    }
    public void gameLopp() {

        boolean loop = true;

        int day =0;
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("dzień = " + day);
            for(grass g : grass) {
                g.update(map);
            }
            int input = Integer.parseInt(sc.nextLine());
            if (input == 1) {
                writeMap();

            }

            if (day > 10) loop = false;
            day++;
        }

    }
    public void setMap(int x, int y, char z){
        map[x][y] = z;
    }
    private void writeMap(){
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                char temp;
                if (mapEmtity[x][y] != ' '){
                    temp = mapEmtity[x][y];
                }else if(map[x][y] != ' '){
                    temp = map[x][y];
                }else temp = ' ';
                System.out.print(temp + " ");
            }
            System.out.println();
        }
        if(map[0][0] == ' '){
            System.out.print("puste ");
        }
    }


}