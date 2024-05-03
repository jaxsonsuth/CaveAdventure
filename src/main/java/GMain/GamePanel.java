package GMain;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static GamePanel instance;
    //dimensions
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    //game thread
    private Thread thread;
    private boolean running;
    private final int FPS = 60;
    private final long targetTime = 1000 / FPS;

    // image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    public static GamePanel getInstance(){
        if (instance == null){
            synchronized(GamePanel.class){
                if(instance == null){
                    instance = new GamePanel();
                }
            }
        }
        return instance;
    }


    private GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;

        gsm = GameStateManager.getInstance();
    }

    public void run() {
        init();

        // timers
        long start, elasped, wait;
        // main loop
        while (running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elasped = System.nanoTime() - start;

            wait = targetTime - elasped / 1000000;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics gDraw = getGraphics();
        gDraw.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        gDraw.dispose();
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}
