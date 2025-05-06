package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.*;

public class GameLoop {
    int sizeMapx, sizeMapy;


    public char[][] terrain;
    public char[][] mapemtity;

    ArrayList<entity> entities = new ArrayList<entity>();


    public static void main(String[] args) {
        GameLoop g = new GameLoop();
        g.sizeMapx = 4;
        g.sizeMapy = 4;
        g.terrain = new char[g.sizeMapx][g.sizeMapy];
        g.mapemtity = new char[g.sizeMapx][g.sizeMapy];

        g.worldGen();
        g.entityGen();
        g.gameLopp();

    }
    public void gameLopp() {

        boolean loop = true;

        int day = 0;
        Scanner sc = new Scanner(System.in);
        while (loop) {

            System.out.println("dzień = " + day);
            List<entity> copy = new ArrayList<>(entities);
            for (entity e : copy) {
                e.update();
            }


            int input = Integer.parseInt(sc.nextLine());
            if (input == 1) writeMap();
            if (day > 10) loop = false;
            day++;
        }

    }

    private void worldGen() {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                int rand = (int) (Math.random() * 14);
                this.terrain[x][y] = ' ';
                this.mapemtity[x][y] = ' ';
                if (rand == 0) {
                    spawnEntity(x,y,'g');
                }
            }
        }
    }

    private void entityGen() {
        int wolf = 1;
        int human = 1;
        int sheep = 1;
        int randx, randy;

        for (int x = 0; x < wolf; x++) {
            randx = (int) (Math.random() * sizeMapx);
            randy = (int) (Math.random() * sizeMapy);
            spawnEntity(randx, randy, 'w');
//            entities.add(new wolf(randx, randy, this));
//            mapemtity[randx][randy] = 'w';
        }
        for (int x = 0; x < human; x++) {
            randx = (int) (Math.random() * sizeMapx);
            randy = (int) (Math.random() * sizeMapy);
            spawnEntity(randx, randy, 'h');
//            entities.add(new human(randx, randy, this));
//            mapemtity[randx][randy] = 'h';
        }
        for (int x = 0; x < sheep; x++) {
            randx = (int) (Math.random() * sizeMapx);
            randy = (int) (Math.random() * sizeMapy);
            spawnEntity(randx, randy, 's');
//            entities.add(new sheep(randx, randy, this));
//            mapemtity[randx][randy] = 's';
        }
    }







    private void writeMap() {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                char temp;
//                if (mapemtity[x][y] != ' ') {
//                    temp = mapemtity[x][y];
//                } else if (terrain[x][y] != ' ') {
//                    temp = terrain[x][y];
//                } else temp = ' ';
//                temp = this.terrain[x][y];
                temp = mapemtity[x][y];
                System.out.print(temp + " ");
            }
            System.out.println();
        }
    }


    public boolean checkborder(int ax, int ay) {
        return ax >= 0 && ax < sizeMapx && ay >= 0 && ay < sizeMapy;
    }
    public void spawnEntity(int x, int y, char type) {
        switch (type) {
            case 'g':
                grass g = new grass(x, y, this);
                entities.add(g);
                break;
            case 'h':
                human h = new human(x, y, this);
                entities.add(h);
                break;
            case 's':
                sheep s = new sheep(x, y, this);
                entities.add(s);
                break;
            case 'w':
                wolf w = new wolf(x, y, this);
                entities.add(w);
                break;
            default:
                break;
        }
    }
    public void despawnEntity(entity et) {
        if (et instanceof grass) {
            terrain[et.getX()][et.getY()] = ' ';
        }
        if (et instanceof animal) {
            mapemtity[et.getY()][et.getX()] = ' ';
        }
        entities.remove(et);
    }


    public char[][] getMapemtity() {
        return mapemtity;
    }
    public void setMapentity(int ax, int ay, char type) {
        System.out.println(ax + " " + ay + " " + type);
        this.mapemtity[ax][ay] = type;
    }



    public void addEmtity(entity entity) {
        this.entities.add(entity);
    }


    public ArrayList<entity> getEntities() {
        return entities;
    }
    public void setEntities(ArrayList<entity> entities) {
        this.entities = entities;
    }


    public char[][] getTerrain() {
        return terrain;
    }
    public void changeTerrain(int x, int y, char z) {
        terrain[x][y] = z;
    }
    public void setTerrain(char[][] maps) {
        this.terrain = maps;
    }

}