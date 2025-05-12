package Game;

public enum Dir {

    Up(0,1),Down(0,-1),Right(1,0),Left(-1,0);

    public int ax , ay;
    Dir(int x, int y) {
        this.ax = x;
        this.ay = y;
    }
}
