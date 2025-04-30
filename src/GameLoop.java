import buildings.*;

import java.util.Scanner;

public class GameLoop {
    tartak tartak = new tartak();
    mine mine = new mine();
    mlyn mlyn = new mlyn();
    building[] buildings = {tartak, mine , mlyn};


    public static void main(String[] args) {


        stockpile stock = new stockpile(0, 0, 0, 0);
        GameLoop g = new GameLoop();
        g.gameLopp(stock);
    }

    public void gameLopp(stockpile stock) {

        boolean loop = true;
        int day =0;
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("dzień = " + day);

            int input = Integer.parseInt(sc.nextLine());
            if (input == 1) {
                showResouces(stock);
            }
            if (input == 2) {
                showBuilding(buildings);
            }




            if (day > 10) loop = false;
            day++;
        }

    }
    private void showResouces(stockpile res) {
        System.out.println("stan surowców wynosi: ");
        System.out.print("żywność = " +res.getFood());
        System.out.print(" drewno = "+ res.getWood());
        System.out.print(" kamien = " +res.getStone());
        System.out.println(" populacja = "+res.getManpower() +'\n');
    }
    private void showBuilding(building[] building) {
        for(building b : building) {
            System.out.println("liczba " + b.name + " = " + b.getCount());
        }
    }
}