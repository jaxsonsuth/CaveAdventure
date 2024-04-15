import Entity.Player;
import TileMap.TileMap;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void playerTest() {
        TileMap tm = new TileMap(30);
        tm.loadTiles("/Tilesets/map-2.png");
        tm.loadMap("/Maps/test.map");
        Player player = new Player(tm);
        player.setFiring();
        player.setScratching();
        player.setGliding(true);
        player.update();
        player.getWidth();
        player.getHeight();
        player.getCwidth();
        player.getCheight();
        player.setPosition(1,1);
        player.setMapPosition();
        player.setVector(1,1);
        player.setLeft(true);
        player.setRight(true);
        player.notOnScreen();
    }
}
