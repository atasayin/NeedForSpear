package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import domain.*;
import domain.controller.LayoutController;


@SuppressWarnings("serial")
public class BuildModeScreen extends JFrame {

    /////////////////////////////////////////////////////////////////////////////////////

    // Frame Parameters
    static final int FRAME_WIDTH = 1368;
    static final int FRAME_HEIGHT = 766;

    // Obstacles
    static final int SIMPLE_COUNT = 75;
    static final int FIRM_COUNT = 10;
    static final int EXPLOSIVE_COUNT = 5;
    static final int GIFT_COUNT = 10;

    private JTextField simpleObstacle;
    private JTextField firmObstacle;
    private JTextField explosiveObstacle;
    private JTextField giftObstacle;

    private JButton gameStart;
    private JButton obstacleButton;

    private HashMap<String, Integer> obstacleSettings;

    private HashMap<String, Integer> runSettings;
    private List<IRunListener> runModeListeners = new ArrayList<>();

    // Layout
    Layout layout;

    // Layout Controller
    LayoutController lc = new LayoutController();

    // Game
    Game game = Game.getInstance();



    /////////////////////////////////////////////////////////////////////////////////////

    public void addListener(IRunListener listener) {
        runModeListeners.add(listener);
    }

    public void removeListener(IRunListener listener) {
        runModeListeners.remove(listener);
    }

    public void setRunSettings() {
        HashMap<String, Integer> runSettings = new HashMap<String, Integer>();

        runSettings.put("screenWidth", (int) FRAME_WIDTH);
        runSettings.put("screenHeight", (int) FRAME_HEIGHT);

        this.runSettings = runSettings;
    }

    public void setObstacleSettings() {
        HashMap<String, Integer> obstacleSettings = new HashMap<String, Integer>();

        obstacleSettings.put("simpleObstacleCount", Integer.parseInt(simpleObstacle.getText()));
        obstacleSettings.put("firmObstacleCount", Integer.parseInt(firmObstacle.getText()));
        obstacleSettings.put("explosiveObstacleCount", Integer.parseInt(explosiveObstacle.getText()));
        obstacleSettings.put("giftObstacleCount", Integer.parseInt(giftObstacle.getText()));

        this.obstacleSettings = obstacleSettings;
    }

    public BuildModeScreen() {
        initializeBuildScreen();
        add(initializeObstacleSettingsPanel());
        add(initializeLayoutPanel());
        add(runGamePanel(this));
    }

    private void initializeBuildScreen() {
        this.setLayout(new GridLayout(3, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
    }
    private JPanel initializeObstacleSettingsPanel(){
        GridLayout gameObjLayout = new GridLayout(5, 1); // #Type of obstacles + Button
        JPanel GameObjectPanel = new JPanel(gameObjLayout);

        // Text Fields
        simpleObstacle = new JTextField(Integer.toString(SIMPLE_COUNT), 30);
        firmObstacle = new JTextField(Integer.toString(FIRM_COUNT), 30);
        explosiveObstacle = new JTextField(Integer.toString(EXPLOSIVE_COUNT), 30);
        giftObstacle = new JTextField(Integer.toString(GIFT_COUNT), 30);

        // Simple Obstacle Row
        //GameObjectPanel.add(new JLabel(new ImageIcon("../assets/simple.png")));
        GameObjectPanel.add(new JLabel("Number of simple obstacles"));
        GameObjectPanel.add(simpleObstacle);

        // Firm Obstacle Row
        //GameObjectPanel.add(new JLabel(new ImageIcon("../assets/firm.png")));
        GameObjectPanel.add(new JLabel("Number of firm obstacles"));
        GameObjectPanel.add(firmObstacle);

        // Explosive Obstacle Row
        //GameObjectPanel.add(new JLabel(new ImageIcon("../assets/explosive.png")));
        GameObjectPanel.add(new JLabel("Number of explosive obstacles"));
        GameObjectPanel.add(explosiveObstacle);

        // Gift Obstacle Row
        //GameObjectPanel.add(new JLabel(new ImageIcon("../assets/gift.png")));
        GameObjectPanel.add(new JLabel("Number of gift obstacles"));
        GameObjectPanel.add(giftObstacle);

        // Obstacle Button
        obstacleButton = new JButton("Set Obstacles");
        obstacleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setObstacleSettings();
                getRandomLayout();
                //notifyButtonisClickedListeners();

            }
        });

        GameObjectPanel.add(obstacleButton);

        return GameObjectPanel;

    }


    // Get random Layout after the obstacle settings
    private void getRandomLayout(){


    }

    private JPanel initializeLayoutPanel() {
        GridLayout varLayout = new GridLayout(5, 3);
        JPanel GameSettingsPanel = new JPanel(varLayout);

        GameSettingsPanel.setBackground(Color.YELLOW);

        return GameSettingsPanel;

    }

    private JPanel runGamePanel(JFrame frame) {
        GridLayout panelLayout = new GridLayout(3, 0);
        JPanel runGamePanel = new JPanel(panelLayout);
        gameStart = new JButton("Click to start the game!");
        gameStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setRunSettings();
                notifyButtonisClickedListeners();

            }
        });

        runGamePanel.add(gameStart);
        return runGamePanel;
    }

    public void notifyButtonisClickedListeners() {
        System.out.println("ALL LISTENERS ARE NOTIFIED THAT THE BUTTON IS CLICKED \n\n\n");

        for (IRunListener listener : runModeListeners) {
            System.out.println(listener);
            listener.onClickEvent(this.runSettings, "sds");
        }
        this.setVisible(false);
        this.dispose();
    }

}