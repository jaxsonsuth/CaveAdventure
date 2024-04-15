import TileMap.Tile;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TileTest {

    @Test
    public void tileTest() throws IOException {
        BufferedImage tileset = ImageIO.read(getClass().getResourceAsStream("/Tilesets/map-2.png"));
        BufferedImage subimage = tileset.getSubimage(0, 0, 30, 30);
        Tile t = new Tile(subimage, 1);
        t.getType();
        t.getImage();
    }
}
