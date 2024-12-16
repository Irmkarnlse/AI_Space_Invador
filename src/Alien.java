import java.awt.Graphics;
import java.awt.Color;

public class Alien {

    private int x, y;
    private int dx = 1;
    private int width = 40;
    private int height = 20;
    private boolean visible = true;

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += dx;

        // Bewegung begrenzen und Richtung ändern
        if (x < 0 || x > 760) {
            dx *= -1;
            y += 20; // Eine Reihe nach unten gehen
        }
    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    // Getter und Setter
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
