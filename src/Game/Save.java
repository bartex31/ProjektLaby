package Game;

public class Save {
    public int wolf;
    public int sheep;
    public int human;
    public int grass;
    public int day;
    public int killedwolf;
    public int killedsheep;
    public int killedhuman;

    public Save(int day, int wolf, int sheep, int human, int grass, int killedwolf, int killedsheep, int killedhuman) {
        this.day = day;
        this.wolf = wolf;
        this.sheep = sheep;
        this.human = human;
        this.grass = grass;
        this.killedwolf = killedwolf;
        this.killedsheep = killedsheep;
        this.killedhuman = killedhuman;
    }
}
