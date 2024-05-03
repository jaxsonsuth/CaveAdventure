package GameState;


import Entity.MapObjectFacrory;
import TileMap.TileMapFactory;


public abstract class GameState {

    protected GameStateManager gsm;
    protected TileMapFactory tmFactory = new TileMapFactory();
    protected MapObjectFacrory moFactory = new MapObjectFacrory();


    public abstract void init();

    public abstract void update();

    public abstract void draw(java.awt.Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);


}
