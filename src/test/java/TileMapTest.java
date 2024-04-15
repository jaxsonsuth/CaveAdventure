import TileMap.TileMap;
import org.junit.jupiter.api.Test;

public class TileMapTest {

    @Test
    public void tileMapTest(){
        TileMap tm = new TileMap(30);
    }

    @Test
    public void tileMapLoadTest(){
        TileMap tm = new TileMap(30);
        tm.loadTiles("/Tilesets/map-2.png");
        assert tm != null;
    }

    @Test
    public void tileMapLoadMapTest(){
        TileMap tm = new TileMap(30);
        tm.loadTiles("/Tilesets/map-2.png");
        tm.loadMap("/Maps/test.map");
    }

    @Test
    public void tileMapTestFun(){
        TileMap tm = new TileMap(30);
        tm.loadTiles("/Tilesets/map-2.png");
        tm.loadMap("/Maps/test.map");
        int x1 = tm.getX();
        int x2 = tm.getY();
        tm.getTileSize();
        tm.getWidth();
        tm.getHeight();
        tm.setPosition(2,0);
        tm.fixBounds();
    }
}
