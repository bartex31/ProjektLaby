
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
    char[][] map;
    char[][] mapEmtity;
    int sizeMapx, sizeMapy;
    ArrayList<grass> grass = new ArrayList<grass>();


    public static void main(String[] args) {
        GameLoop g = new GameLoop();
        g.sizeMapx = 400;
        g.sizeMapy = 400;
        g.map = new char[g.sizeMapx][g.sizeMapy];
        g.mapEmtity = new char[g.sizeMapx][g.sizeMapy];
        g.worldGen();

        g.gameLopp();

    }
    private void worldGen() {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                int rand = (int) (Math.random() *5);
                map[x][y] = ' ';
                if (rand == 1) {
                    grass.add(new grass(x,y, map));
                }
            }
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
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

}