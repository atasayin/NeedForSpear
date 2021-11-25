package domain;

import UI.PlayScreen;
import UI.Playground;

import javax.swing.*;

public class NFSGameStartUp {
    //private static Playground playground;
    private static PaddleController pc;

    public static final int HEIGHT = 600;
    public static final int WIDTH = 900;

    public static void main(String[] args) {
        PlayScreen playScreen = new PlayScreen();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CreateAndShowGUI();
            }
        });


    }


    private static void CreateAndShowGUI() {
        pc = new PaddleController(HEIGHT,WIDTH);
        playground = new Playground(pc,HEIGHT,WIDTH);
        //playground.setVisible(true);

    }

}
