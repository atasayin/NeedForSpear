package UI;

import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.*;

import domain.*;


public class Playground implements IRunListener, IAuthorizeListener {
    static JFrame jf;
    protected BuildModeScreen bd;

    RunGameObjects screen;

    public void onClickEvent(HashMap<String, Integer> runSettings, String username) {
        int screenWidth = runSettings.get("screenWidth").intValue();
        int screenHeight = runSettings.get("screenHeight").intValue();
        initializeOuterFrameSettings(screenWidth, screenHeight);
        openRunModeScreen(screenWidth, screenHeight);

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                openRunModeScreen(screenWidth, screenHeight);
//                System.out.println("aaaaaaaaaaaaaaaaa");
//            }
//        });

    }

    public Playground() {


    }

    private void initializeOuterFrameSettings(int screenWidth, int screenHeight) {
        jf = new JFrame();
        jf.setTitle("Need For Spear");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout(5, 5));
        jf.setSize(screenWidth, screenHeight);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setFocusable(true);
        jf.setLocationRelativeTo(null);
    }

    private void openRunModeScreen(int width, int height) {
        screen = new RunGameObjects(width, height);
        screen.setVisible(true);
        jf.add(screen);
        jf.addKeyListener(screen);


    }

    public static void GameOver() {

        jf.dispose();
    }

    @Override
    public void onClickEvent(Playground nfs) {
        nfs.bd = new BuildModeScreen();
        nfs.bd.setVisible(true);
        nfs.bd.addListener(nfs);
        nfs.bd.addListener(Game.getInstance());
        nfs.bd.addLoadListener(Game.getInstance());
    }
}
