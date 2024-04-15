import Entity.Explorer;
import Entity.Player;
import TileMap.TileMap;
import org.junit.jupiter.api.Test;

public class ExplorerTest {
    @Test
    public void explorerTest() {
        TileMap tm = new TileMap(30);
        tm.loadTiles("/Tilesets/map-2.png");
        tm.loadMap("/Maps/test.map");
        Explorer player = new Explorer(tm);
        player.setAttacking();
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
        player.getHealth();
        player.getMaxHealth();
        player.calculateCorners(1,1);
        player.checkTileMapCollision();
        player.setDown(true);
        player.setUp(true);
        player.setJumping(true);
    }
}
