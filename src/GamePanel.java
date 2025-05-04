import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int[][][] board; // Tablica 3D [x][y][typ obiektu]

    public GamePanel(int width, int height) {
        this.board = new int[width][height][1]; // 1 oznacza typ obiektu (np. 0 = pusty, 1 = mur, 2 = gracz)
        initBoard(); // Inicjalizacja tablicy
    }

    private void initBoard() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (x == 0 || y == 0 || x == board.length - 1 || y == board[0].length - 1) {
                    board[x][y][0] = 1; // Ściany wokół planszy
                } else {
                    board[x][y][0] = 0; // Puste pola
                }
            }
        }
        board[2][2][0] = 2; // Przykładowy gracz na pozycji (2,2)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int tileSize = 40; // Rozmiar pojedynczej komórki

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int type = board[x][y][0];

                if (type == 1) {
                    g2d.setColor(Color.GRAY); // Ściany
                } else if (type == 2) {
                    g2d.setColor(Color.RED); // Gracz
                } else {
                    g2d.setColor(Color.WHITE); // Puste miejsce
                }

                g2d.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Board from 3D Array");
        GamePanel panel = new GamePanel(10, 10);
        frame.add(panel);
        frame.setSize(420, 440);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
