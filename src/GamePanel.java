import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private Thread animator;
    private boolean running = false;

    private Player player;
    private ArrayList<Alien> aliens;

    public GamePanel() {
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new TAdapter());

        initGame();
    }

    private void initGame() {
        player = new Player();
        aliens = new ArrayList<>();

        // Aliens initialisieren
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                aliens.add(new Alien(50 + col * 60, 30 + row * 40));
            }
        }

        running = true;
        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        while (running) {
            updateGame();
            repaint();

            try {
                Thread.sleep(15); // ~60 FPS
            } catch (InterruptedException e) {
                System.out.println("Interruption: " + e.getMessage());
            }
        }
    }

    private void updateGame() {
        player.update();

        for (Alien alien : aliens) {
            alien.update();
        }

        // Kollisionserkennung
        checkCollisions();
    }

    private void checkCollisions() {
        ArrayList<Projektil> projektile = player.getProjektile();

        for (Projektil p : projektile) {
            for (Alien alien : aliens) {
                if (p.isVisible() && alien.isVisible()) {
                    if (p.getX() < alien.getX() + alien.getWidth() &&
                        p.getX() + 5 > alien.getX() &&
                        p.getY() < alien.getY() + alien.getHeight() &&
                        p.getY() + 10 > alien.getY()) {

                        p.setVisible(false);
                        alien.setVisible(false);
                    }
                }
            }
        }

        // Entferne getroffene Aliens
        aliens.removeIf(alien -> !alien.isVisible());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        player.draw(g);

        for (Alien alien : aliens) {
            alien.draw(g);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
