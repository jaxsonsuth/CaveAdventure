package TileMap;

import java.awt.image.BufferedImage;

public class TileMapFactory {
    public TileMap createTileMap(int size){
        return new TileMap(size);
    }
    public Tile createTile(BufferedImage image, int type){
        return new Tile(image,type);
    }
    public Background createBackground(String s, double ms){
        return new Background(s,ms);
    }
}
