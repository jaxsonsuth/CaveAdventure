import GameState.GameStateManager;
import org.junit.jupiter.api.Test;

public class GameStateManagerTest {

    @Test
    public void gsmTest() {
        GameStateManager gsm = new GameStateManager();
    }

    @Test
    public void setStateTest() {
        GameStateManager gsm = new GameStateManager();
        gsm.setState(1);
        gsm.update();
        gsm.keyPressed(1);
        gsm.keyReleased(1);
    }
}
