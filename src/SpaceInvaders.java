import javax.swing.JFrame;

public class SpaceInvaders extends JFrame {

    public SpaceInvaders() {
        initUI();
    }

    private void initUI() {
        add(new GamePanel());

        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SpaceInvaders ex = new SpaceInvaders();
        ex.setVisible(true);
    }
}
