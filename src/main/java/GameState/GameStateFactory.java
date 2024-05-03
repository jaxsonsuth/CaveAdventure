package GameState;

public class GameStateFactory {
    public GameState createBackgroundTestState(GameStateManager gsm){
        return new BackgroundTestState(gsm);
    }
    public GameState createLevel1State(GameStateManager gsm){
        return new Level1State(gsm);
    }
    public GameState createMenuState(GameStateManager gsm){
        return new MenuState(gsm);
    }
}
