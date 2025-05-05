package Game;

public enum dir {
    Up(0,1),Down(0,-1),Right(1,0),Left(-1,0);
    public int ax , ay;
    dir(int x, int y) {
        this.ax = x;
        this.ay = y;
    }
}
