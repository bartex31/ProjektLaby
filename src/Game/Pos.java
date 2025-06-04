package Game;

/**
 * klasa zawierająca pozycje w tablicy2d (tzw planszy)
 */
public class Pos {
    /**
     * liczba reprezentujaca koordynat x
     */
    public int x;
    /**
     * liczba reprezentujaca koordynat y
     */
    public int y;
    /**
     * klasa zaiwerająca koordynaty x i y
     * @param x koordynat x
     * @param y koordynat y
     */
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
