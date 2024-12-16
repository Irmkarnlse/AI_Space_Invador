import java.awt.Graphics;
import java.awt.Color;

public class Projektil {

    private int x, y;
    private int dy;
    private int width = 5;
    private int height = 10;
    private boolean visible = true;

    public Projektil(int x, int y, int dy) {
        this.x = x;
        this.y = y;
        this.dy = dy; // Richtung: negativ für nach oben, positiv für nach unten
    }

    public void update() {
        y += dy;

        // Sichtbarkeit prüfen
        if (y < 0 || y > 600) {
            visible = false;
        }
    }

    public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
        }
    }

    // Getter und Setter
    public boolean isVisible() {
        return visible;
    }

    // Hier fügen wir die fehlende Methode hinzu
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
