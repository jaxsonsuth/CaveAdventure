import GameState.BackgroundTestState;
import GameState.GameStateManager;
import GameState.MenuState;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class MenuStateTest {

    @Test
    public void menuTest() {
        GameStateManager gsm = new GameStateManager();
        MenuState bg = new MenuState(gsm);
        bg.update();
        bg.keyReleased(1);
        bg.keyPressed(1);
        bg.init();
        bg.keyPressed(KeyEvent.VK_ENTER);
    }
}
