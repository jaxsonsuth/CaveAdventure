package GMain;

import javax.swing.*;

public class Game {

    public static void main(String[] args) {
        JFrame window = new JFrame("Cave Adventure");
        window.setContentPane(GamePanel.getInstance());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
