package GameState;

import GMain.GamePanel;

import java.util.ArrayList;

public class GameStateManager{
    protected static GameStateManager instance;
    protected static GameStateFactory gameStateFactory = new GameStateFactory();

    private final ArrayList<GameState> gameStates;
    private int currentState;

    static final int MENUSTATE = 0;
    static final int LEVEL1STATE = 1;
    static final int BACKGROUNDTESTSTATE = 2;

    public static GameStateManager getInstance(){
        if (instance == null){
            synchronized(GameStateManager.class){
                if(instance == null){
                    instance = new GameStateManager();
                }
            }
        }
        return instance;
    }

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(gameStateFactory.createMenuState(this));
        gameStates.add(gameStateFactory.createLevel1State(this));
        gameStates.add(gameStateFactory.createBackgroundTestState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }


    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}

