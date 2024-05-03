package Entity;
import TileMap.TileMap;

public class MapObjectFacrory {
    public Explorer createExplorer(TileMap tm){
        return new Explorer(tm);
    }
    public Player createPlayer(TileMap tm){
        return new Player(tm);
    }
    public Animation createAnimation(){
        return new Animation();
    }
}
