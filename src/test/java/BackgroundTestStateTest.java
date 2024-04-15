import GameState.BackgroundTestState;
import GameState.GameStateManager;
import org.junit.jupiter.api.Test;

public class BackgroundTestStateTest {

    @Test
    public void bgTest(){
        GameStateManager gsm = new GameStateManager();
        BackgroundTestState bg = new BackgroundTestState(gsm);
        bg.update();
        bg.keyReleased(1);
        bg.keyPressed(1);
        bg.init();
    }
}
