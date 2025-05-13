package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.*;

public class Game {
    int sizeMapx, sizeMapy;
    public char[][] terrain;
    public char[][] entityMap;
    private int days;
    public int sheepskilled = 0;
    public int wolfskilled = 0;
    public int grasseaten =0;
    public int humankilled =0;

    ArrayList<entity> entities = new ArrayList<entity>();


    public static void main(String[] args) {
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj szerokość planszy: ");
        int width = Integer.parseInt(sc.nextLine());
        System.out.print("Podaj wysokość planszy: ");
        int height = Integer.parseInt(sc.nextLine());
        System.out.print("Podaj liczbę owiec: ");
        int sheepCount = Integer.parseInt(sc.nextLine());
        System.out.print("Podaj liczbę wilków: ");
        int wolfCount = Integer.parseInt(sc.nextLine());
        System.out.print("Podaj liczbę ludzi: ");
        int humanCount = Integer.parseInt(sc.nextLine());
        g.sizeMapx = width;
        g.sizeMapy = height;
        g.terrain = new char[width][height];
        g.entityMap = new char[width][height];


        g.initStart(sheepCount,wolfCount,humanCount);
        g.gameLopp();
    }

    public void gameLopp() {
        boolean loop = true;
        int day = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("dzień = " + day);
        writeMap();
        day++;
        while (loop) {
            System.out.println("dzień = " + day);

            List<entity> copy = new ArrayList<>(entities);
            for (entity e : copy) {
                e.update();
            }

            refreshWorld();
            writeMap();

            //int input = Integer.parseInt(sc.nextLine());
            //if (input == 1)=
            day++;
            if (day > 40) loop = false;
        }

    }
    private void initStart(int sheepCount, int wolfCount, int humanCount) {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                if (Math.random()<0.1) {
                    spawnEntity(x,y,'g');
                }else terrain[x][y] = ' ';
                entityMap[x][y] = ' ';
            }
        }
        entityGen(sheepCount, 's');
        entityGen(wolfCount, 'w');
        entityGen(humanCount, 'h');
    }
    private void entityGen(int count , char type) {
        int counted =0;
        int randx, randy;
        while (counted<  count){

            randx = (int) (Math.random() * sizeMapx);
            randy = (int) (Math.random() * sizeMapy);
            //System.out.println("spawn" + randx +" " + randy + " " + type);

            if(spawnEntity(randx, randy, type)) counted++;
        }
    }






    private void refreshWorld() {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                entityMap[x][y] = ' ';
                terrain[x][y] = ' ';
            }
        }
        for(entity e : entities) {
            if (e instanceof animal) entityMap[e.getX()][e.getY()] = e.type;
            if(e instanceof  grass) terrain[e.getX()][e.getY()] = e.type;
        }

    }
    private void writeMap() {
        System.out.print("   |");
        for (int test =0 ; test < sizeMapx ; test++) {
            System.out.printf("%2d |",test);
        }
        System.out.println();
        for (int y = 0; y < sizeMapy;y++) {

            System.out.printf("%2d | ", y);
            for (int x = 0; x < sizeMapx; x++) {
                char temp;
                if (this.entityMap[x][y] != ' ') {
                    temp = this.entityMap[x][y];
                } else if (this.terrain[x][y] != ' ') {
                    temp = this.terrain[x][y];
                } else temp = ' ';
                System.out.print(temp + " | ");
            }
            System.out.println();
        }
    }


    public boolean checkborder(int ax, int ay) {
        return ax >= 0 && ax < sizeMapx && ay >= 0 && ay < sizeMapy;
    }



    public boolean spawnEntity(int x, int y, char type) {
        if(type != 'g' && entityMap[x][y] != ' ') return false;
        switch (type) {
            case 'g':
                //System.out.println("ustawiam " + x + " " + y + " " + type);
                grass g = new grass(x, y, this);
                entities.add(g);
                break;
            case 'h':
                //System.out.println("ustawiam " + x + " " + y + " " + type);
                human h = new human(x, y, this);
                entities.add(h);
                break;
            case 's':
                //System.out.println("ustawiam " + x + " " + y + " " + type);
                sheep s = new sheep(x, y, this);
                entities.add(s);
                break;
            case 'w':
                //System.out.println("ustawiam " + x + " " + y + " " + type);
                wolf w = new wolf(x, y, this);
                entities.add(w);
                break;
            default:
                break;
        }
        return true;
    }


    public void despawnEntity(entity et) {
        if (et instanceof grass) {
            terrain[et.getX()][et.getY()] = ' ';

        }
        if (et instanceof animal) {
            entityMap[et.getX()][et.getY()] = ' ';
        }
        entities.remove(et);
    }



    public char[][] getEntityMap() {
        return entityMap;
    }

    public void setMapentity(int ax, int ay, char type) {
        //System.out.println("ustawiam " + ax + " " + ay + " " + type);
        this.entityMap[ax][ay] = type;
    }

    public ArrayList<entity> getEntities() {
        return entities;
    }
    public char[][] getTerrain() {
        return terrain;
    }
    public void setTerrain(int x, int y, char z) {
        terrain[x][y] = z;
    }


}


