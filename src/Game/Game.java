package Game;

import java.util.*;

import entities.*;


/**
 * klasa symulacji
 */
public class Game {
    /**
     * rozmiar mapy
     */
    int sizeMapx, sizeMapy;
    /**
     * tablica zaiwerająca symbole terenu
     */
    public char[][] terrain;
    /**
     * tablica zawierająca symbole zwierząt
     */
    public char[][] entityMap;
    /**
     * liczba zmarłych owiec
     */
    public int sheepskilled = 0;
    /**
     * liczba zmarłych wilków
     */
    public int wolfskilled = 0;
    /**
     * liczba zjedzonych traw
     */
    public int grasseaten =0;
    /**
     * liczba zmarłych ludzi
     */
    public int humankilled =0;
    /**
     * licznik oznaczający ktory jest dzien symulacjio
     */
    private int currentDay = 0;
    /**
     * lista zawierajaca zwierzeta
     */
    ArrayList<animal> entities = new ArrayList<animal>();
    /**
     * lista zawierajaca zbiór klass save posiadająca dane symulacji na dany dzien
     */
    ArrayList<Save> saves = new ArrayList<Save>();

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
        System.out.println("podaj liczbe dni");
        int days = Integer.parseInt(sc.nextLine());
        g.sizeMapx = width;
        g.sizeMapy = height;
        g.terrain = new char[width][height];
        g.entityMap = new char[width][height];

        g.initStart(sheepCount,wolfCount,humanCount);
        g.gameLopp(days);
    }


    /**
     * głowna pętla symulacji
     * @param days entity
     */
    public void gameLopp(int days) {
        boolean loop = true;
        boolean ignorePreq = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("dzień = " + currentDay);
        writeMap();
        saves.add(new Save(currentDay,wolf.ammount,sheep.ammount,human.ammount,grass.ammount,wolfskilled,sheepskilled,humankilled,grasseaten));
        currentDay++;

        while (loop) {
            System.out.println("dzień = " + currentDay);

            refreshWorld();
            growGrass();
            List<entity> copy = new ArrayList<>(entities);
            for (entity e : copy) e.update();
            saves.add(new Save(currentDay,wolf.ammount,sheep.ammount,human.ammount,grass.ammount,wolfskilled,sheepskilled,humankilled,grasseaten));
            refreshWorld();
            writeMap();
            currentDay++;
            if ( wolf.ammount ==0 &&sheep.ammount ==0&& human.ammount== 0 && !ignorePreq ){
                System.out.println("zwierzeta wymarły w " + currentDay + " czy kontnuowac symulacje: 0 - nie  1-tak");
                int input = Integer.parseInt(sc.nextLine());
                if (input == 0){
                    loop = false;

                }else {
                    ignorePreq = true;
                    continue;
                }
            }
            if (currentDay > days) loop = false;

        }
        Save.saveCsv(saves);
        System.out.println("umarło " + sheepskilled + " owc ");
        System.out.println("umarło " + humankilled + " ludzi ");
        System.out.println("umarło " + wolfskilled + " wilków ");
    }
    private void initStart(int sheepCount, int wolfCount, int humanCount) {
        for (int x = 0; x < sizeMapx; x++) {
            for (int y = 0; y < sizeMapy; y++) {
                terrain[x][y] = ' ';
                entityMap[x][y] = ' ';
            }
        }
        growGrass();
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
            }
        }
        for(entity e : entities) {
            if (e instanceof animal) entityMap[e.getX()][e.getY()] = e.type;
           // if(e instanceof  grass) terrain[e.getX()][e.getY()] = '■';
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

    /**
     * zwraca true gdy podane ax i ay nie wychodzi z tablicy
     * @param ax koordynat x
     * @param ay koordynat y
     * @return true/false
     */
    public boolean checkborder(int ax, int ay) {
        return ax >= 0 && ax < sizeMapx && ay >= 0 && ay < sizeMapy;
    }
    /**
     * tworzenie zwierzecia zależnego od type<br>
     * type = h pojawia sie czlowiek,<br>
     * type = s pojawia sie owca,<br>
     * type = w pojawia sie wilk
     * @param x koordynatx
     * @param type typ entity 'h,s,w'
     * @param y koordynat y
     * @return zwraca true gdy tworzenie nie zadziałało
     */
    public boolean spawnEntity(int x, int y, char type) {
        if(type != 'g' && entityMap[x][y] != ' ') return false;
        switch (type) {
//            case 'g':
//                //System.out.println("ustawiam " + x + " " + y + " " + type);
//                grass g = new grass(x, y, this);
//                entities.add(g);
//                break;
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
    /**
     * tworzenie trawy w ilości zależnej od rozmiaru mapy
     */
    public void growGrass(){
        for(int i = 0; i< sizeMapx; i++){
            int ax = (int) (Math.random() * sizeMapx);
            int ay = (int) (Math.random() * sizeMapy);
            if(terrain[ax][ay] == ' ') {
                terrain[ax][ay] = 'g';
                grass.ammount++;
            }
        }
    }
    /**
     * usuwa entity et z listy i tablicy entity
     * @param et entity
     */
    public void despawnEntity(entity et) {
        if (et instanceof animal) {
            entityMap[et.getX()][et.getY()] = ' ';
        }
        entities.remove(et);

    }


    /**
     * podaje wartośc x
     * @return  x
     */
    public char[][] getEntityMap() {
        return entityMap;
    }

    /**
     * ustawia wartośc z w tablicy[x][y]
     * @param ax parametr x
     *@param ay parametr y
     * @param type parametr typu charakteru wstawienia do tablicy zwierząt
     */

    public void setMapentity(int ax, int ay, char type) {
        //System.out.println("ustawiam " + ax + " " + ay + " " + type);
        this.entityMap[ax][ay] = type;
    }

    /**
     * zwraca tabice zwierząt
     * @return  tablica entities
     */
    public ArrayList<animal> getEntities() {
        return entities;
    }
    /**
     * zwraca tabice terenu
     * @return  tablica terenu
     */
    public char[][] getTerrain() {
        return terrain;
    }
    /**
     * ustawia wartośc z w tablicy[x][y] do tablicy tereny
     * @param x koorydnat x
     *@param y koorydnat y
     * @param z parametr typu charakteru wstawienia
     */
    public void setTerrain(int x, int y, char z) {
        terrain[x][y] = z;
    }


}


