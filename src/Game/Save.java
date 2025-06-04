package Game;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * tworzenie klasy ktora zawiera dane o symulacji
     * @param day koordynat x
     *@param wolf liczba wilków podczas danego dnia
     * @param sheep liczba owiec podczas danego dnia
     * @param human liczba ludzi podczas danego dnia
     * @param grass liczba trawy podczas danego dnia
     * @param grasseaten liczba zjedzonej trawy podczas danego dnia
     *  @param killedwolf liczba zabitych wilków podczas danego dnia
     * @param killedhuman liczba zabitych  podczas danego dnia
     *@param killedsheep liczba zabitych owiec podczas danego dnia
     */
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

    //zapisywanie wyników z listy saves do pliku csv
    public static void saveCsv(ArrayList<Save> saves) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
        try(FileWriter writer = new FileWriter(date+ ".csv", true);) {


            if (saves.getFirst().day == 0) {
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

