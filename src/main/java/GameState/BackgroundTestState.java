package GameState;

import Entity.Explorer;
import Entity.Player;
import GMain.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BackgroundTestState extends GameState{
    private TileMap tileMap;
    private Background bg1;
    private Background bg2;
    private Background bg3;
    private Background bg4;
    private Background bg5;
    private Background bg6;
    private Background bg7;
    private Background bg8;
    private Background bg9;

    private Explorer explorer;

    public BackgroundTestState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            tileMap = new TileMap(30);
            tileMap.loadTiles("/Tilesets/map-2.png");
            tileMap.loadMap("/Maps/test.map");
            tileMap.setPosition(0, 0);

            bg1 = new Background("/Backgrounds/1.png", 0.0);
            bg2 = new Background("/Backgrounds/2.png", 0.05);
            bg3 = new Background("/Backgrounds/3fx.png", 0.01);
            bg4 = new Background("/Backgrounds/4.png", 0.05);
            bg5 = new Background("/Backgrounds/5.png", 0.05);
            bg6 = new Background("/Backgrounds/6fx.png", 0.1);
            bg7 = new Background("/Backgrounds/7.png", 0.05);
            bg8 = new Background("/Backgrounds/8fx.png", 0.1);
            bg9 = new Background("/Backgrounds/9.png", 0.05);


            explorer = new Explorer(tileMap);
            explorer.setPosition(100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        explorer.update();

        tileMap.setPosition(GamePanel.WIDTH /2 - explorer.getx(),GamePanel.HEIGHT / 2 - explorer.gety());
    }

    public void init(){}

    public void draw(Graphics2D g) {
        bg1.setPosition(-explorer.getx(), 0);
        bg2.setPosition(-explorer.getx(), 0);
        bg3.setPosition(-explorer.getx(), 0);
        bg4.setPosition(-explorer.getx(), 0);
        bg5.setPosition(-explorer.getx(), 0);
        bg6.setPosition(-explorer.getx(), 0);
        bg7.setPosition(-explorer.getx(), 0);
        bg8.setPosition(-explorer.getx(), 0);
        bg9.setPosition(-explorer.getx(), 0);
        bg3.setPosition(-explorer.getx(), 0);
        bg1.draw(g);
        bg2.draw(g);
        bg3.draw(g);
        bg4.draw(g);
        bg5.draw(g);
        bg6.draw(g);
        bg7.draw(g);
        bg8.draw(g);
        bg9.draw(g);

        tileMap.draw(g);
        explorer.draw(g);
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) explorer.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) explorer.setRight(true);
        if(k == KeyEvent.VK_UP) explorer.setUp(true);
        if(k == KeyEvent.VK_DOWN) explorer.setDown(true);
        if(k == KeyEvent.VK_W) explorer.setJumping(true);
        if(k == KeyEvent.VK_E) explorer.setGliding(true);
        if(k == KeyEvent.VK_R) explorer.setAttacking();
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) explorer.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) explorer.setRight(false);
        if(k == KeyEvent.VK_UP) explorer.setUp(false);
        if(k == KeyEvent.VK_DOWN) explorer.setDown(false);
        if(k == KeyEvent.VK_W) explorer.setJumping(false);
        if(k == KeyEvent.VK_E) explorer.setGliding(false);
    }
}
