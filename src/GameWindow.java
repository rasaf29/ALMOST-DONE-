

import utils.GamePanel;

import javax.swing.*;

public class GameWindow extends JFrame {


    public GameWindow() {
        setTitle("Bloodline");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1980, 1080));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }




}
