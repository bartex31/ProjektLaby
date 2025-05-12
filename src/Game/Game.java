package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.*;

public class Game {
    int sizeMapx, sizeMapy;
    public char[][] terrain;
    public char[][] mapemtity;
    private int days;

    ArrayList<entity> entities = new ArrayList<entity>();

    public int eatenSheep = 0;
    public int eatenWolves = 0;
    public int eatenHumans = 0;
    public int eatenGrass = 0;

    public int spawnedSheep = 0;
    public int spawnedWolves = 0;
    public int spawnedHumans = 0;
    public int spawnedGrass = 0;


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
        g.mapemtity = new char[width][height];
        g.worldGen();
        System.out.println();
        g.entityGen(sheepCount, 's');
        System.out.println();
        g.entityGen(wolfCount, 'w');
        System.out.println();
        g.entityGen(humanCount, 'h');
        System.out.println();
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

            System.out.println("=== Statystyki symulacji ===");
            System.out.println("Stworzono: ");
            System.out.println(" - Trawy: " + spawnedGrass);
            System.out.println(" - Owce: " + spawnedSheep);
            System.out.println(" - Wilki: " + spawnedWolves);
            System.out.println(" - Ludzie: " + spawnedHumans);
            System.out.println("Zjedzono: ");
            System.out.println(" - Trawy: " + eatenGrass);
            System.out.println(" - Owce: " + eatenSheep);
            System.out.println(" - Wilki: " + eatenWolves);
            System.out.println(" - Ludzie: " + eatenHumans);
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

    private void entityGen(int count , char type) {
        int counted =0;
        int randx, randy;
        while (counted<  count){

            randx = (int) (Math.random() * sizeMapx);
            randy = (int) (Math.random() * sizeMapy);
            System.out.println("spawn" + randx +" " + randy + " " + type);
            if(spawnEntity(randx, randy, type)) counted++;
        }
    }







    private void writeMap() {
        for (int y = 0; y < sizeMapy;y++) {
             System.out.print("| ");
            for (int x = 0; x < sizeMapx; x++) {
                char temp;
                if (this.mapemtity[x][y] != ' ') {
                    temp = this.mapemtity[x][y];
                } else if (this.terrain[x][y] != ' ') {
                    temp = this.terrain[x][y];
                } else temp = ' ';
                //temp = this.terrain[x][y];
                //temp = mapemtity[x][y];
                System.out.print(temp + " | ");
            }
//            System.out.println();
//            for (int z = 0; z < sizeMapx*2; z++) {
//                System.out.print("--");
//            }
            System.out.println();
        }
    }


    public boolean checkborder(int ax, int ay) {
        return ax >= 0 && ax < sizeMapx && ay >= 0 && ay < sizeMapy;
    }



    public boolean spawnEntity(int x, int y, char type) {
        if(type != 'g' && mapemtity[x][y] != ' ') return false;
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
                System.out.println("ustawiam " + x + " " + y + " " + type);
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
        return true;
    }


    public void despawnEntity(entity et) {
        if (et instanceof grass) {
            terrain[et.getX()][et.getY()] = ' ';
        }
        if (et instanceof animal) {
            mapemtity[et.getX()][et.getY()] = ' ';
        }
        entities.remove(et);
    }


    public char[][] getMapemtity() {
        return mapemtity;
    }

    public void setMapentity(int ax, int ay, char type) {
        System.out.println("ustawiam " + ax + " " + ay + " " + type);
        this.mapemtity[ax][ay] = type;
    }



    public ArrayList<entity> getEntities() {
        return entities;
    }
    public char[][] getTerrain() {
        return terrain;
    }
    public void changeTerrain(int x, int y, char z) {
        terrain[x][y] = z;
    }
    public Pos check(int x , int y , char[] target) {
        for (int dx = -1; dx <= 1; dx++) {
            int ax = x + dx;
            for (int dy = -1; dy <= 1; dy++) {
                int ay = y + dy;
                if (checkborder(ax, ay)) {
                    for (char t : target) {
                        if (terrain[ax][ay] == t) {
                            return new Pos(ax, ay);
                        }
                    }
                }
            }
        }
        return null;
    }

}


