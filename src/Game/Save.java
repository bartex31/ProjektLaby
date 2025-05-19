package Game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {
    public int wolf;
    public int sheep;
    public int human;
    public int grass;
    public int day;
    public int killedwolf;
    public int killedsheep;
    public int killedhuman;
    public int killedgrass;

    public Save(int day, int wolf, int sheep, int human, int grass, int killedwolf, int killedsheep, int killedhuman, int grasseaten) {
        this.day = day;
        this.wolf = wolf;
        this.sheep = sheep;
        this.human = human;
        this.grass = grass;
        this.killedwolf = killedwolf;
        this.killedsheep = killedsheep;
        this.killedhuman = killedhuman;
        this.killedgrass = grasseaten;
    }

    public static void saveCsv(ArrayList<Save> saves) {
        try(FileWriter writer = new FileWriter("symulacjaZapis.csv", true);) {


            if (saves.get(0).day == 1) {
                writer.write("Dzien;Wilki;Owce;Ludzie;Trawa;Zjedzone wilki;Zjedzone owce;Zjedzeni ludzie;Zjedzona trawa\n");
            }
            for (Save s : saves) {
                writer.write(
                        s.day + ";" +
                                s.wolf + ";" +
                                s.sheep + ";" +
                                s.human + ";" +
                                s.grass + ";" +
                                s.killedwolf + ";" +
                                s.killedsheep + ";" +
                                s.killedhuman + ";" +
                                s.killedgrass + "\n"
                );


            }
                }catch (IOException e) {
            System.out.println("Błąd przy zapisie: " + e.getMessage());
            }

    }

}

