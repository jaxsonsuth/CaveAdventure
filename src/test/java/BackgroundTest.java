import GMain.GamePanel;
import TileMap.Background;
import GMain.Game;
import org.junit.jupiter.api.Test;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class BackgroundTest {
    @Test
    public void backgroundTest(){
        Background bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
    }

    @Test
    public void setPositionTest(){
        Background bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        bg.setPosition(1 , 1);
    }

    @Test
    public void setVectorTest(){
        Background bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        bg.setVector(1,1);
    }

    @Test
    public void updateTest(){
        Background bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        bg.update();
    }

    @Test
    public void drawTest(){
        Background bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        BufferedImage image = new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        bg.draw(g);
    }
}
