import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private int x, y;
    private int dx;
    private final int SPEED = 5;
    private ArrayList<Projektil> projektile;

    public Player() {
        x = 400;
        y = 550;
        projektile = new ArrayList<>();
    }

    public void update() {
        x += dx;

        // Begrenzungen
        if (x < 0) x = 0;
        if (x > 760) x = 760;

        // Projektile aktualisieren
        for (int i = 0; i < projektile.size(); i++) {
            Projektil p = projektile.get(i);
            p.update();
            if (!p.isVisible()) {
                projektile.remove(i);
                i--;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 20);

        // Projektile zeichnen
        for (Projektil p : projektile) {
            p.draw(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -SPEED;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED;
        }

        if (key == KeyEvent.VK_SPACE) {
            schiessen();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void schiessen() {
        projektile.add(new Projektil(x + 17, y, -5));
    }

    public ArrayList<Projektil> getProjektile() {
        return projektile;
    }

    // Getter für x und y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
