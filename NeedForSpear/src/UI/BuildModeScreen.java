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
import domain.obstacle.Obstacle;


@SuppressWarnings("serial")
public class BuildModeScreen extends JFrame {

    /////////////////////////////////////////////////////////////////////////////////////

    public static final int FRAME_WIDTH = 1368;
    public static final int FRAME_HEIGHT = 766;

    // Obstacles
    static final int SIMPLE_COUNT = 75;
    static final int FIRM_COUNT = 10;
    static final int EXPLOSIVE_COUNT = 5;
    static final int GIFT_COUNT = 10;

    private JTextField simpleObstacle;
    private JTextField firmObstacle;
    private JTextField explosiveObstacle;
    private JTextField giftObstacle;

    private JButton gameStartButton;
    private JButton obstacleButton;
    private JButton loadGameButton;

    private HashMap<String, Integer> obstacleSettings;

    private HashMap<String, Integer> runSettings;
    private List<IRunListener> runModeListeners = new ArrayList<>();
    private List<ILoadListener> loadModeListeners = new ArrayList<>();

    // Layout
    private Layout layout;

    // Layout Controller
    private LayoutController LC = LayoutController.getInstance();

    // Layout Panel
    // private LayoutPanel layoutPanel;

    private JPanel obstacleSettingsPanel;
    private JPanel viewPanel;

    private String  username;
    private  String id;

    /////////////////////////////////////////////////////////////////////////////////////

    public void addListener(IRunListener listener) {
        runModeListeners.add(listener);
    }

    public void removeListener(IRunListener listener) {
        runModeListeners.remove(listener);
    }

    public void addLoadListener(ILoadListener listener) { loadModeListeners.add(listener) ;}

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

        LC.setObstacleSettings(obstacleSettings);
    }

    public BuildModeScreen() {
        initializeBuildScreen();
        viewPanel = initializeViewPanel();
        add(viewPanel);
        obstacleSettingsPanel = initializeObstacleSettingsPanel();
        add(obstacleSettingsPanel);
        add(runGamePanel(this));


//        initializeBuildScreen();
//        obstacleSettingsPanel = initializeObstacleSettingsPanel();
//        add(obstacleSettingsPanel,BorderLayout.CENTER);
//        layoutPanel = initializeLayoutPanel();
//        add(layoutPanel,BorderLayout.NORTH);
//        add(runGamePanel(this),BorderLayout.SOUTH);
    }

    private void initializeBuildScreen() {
        //this.setLayout(new BorderLayout(0, 0));
        this.setLayout(new GridLayout(3,0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
    }

    private JPanel initializeViewPanel(){
        JPanel viewP = new JPanel();
        viewP.setBackground(Color.BLACK);
        return viewP;
    }

    private JPanel initializeObstacleSettingsPanel(){
        GridLayout gameObjLayout = new GridLayout(5,3); // #Type of obstacles + Button
        JPanel GameObjectPanel = new JPanel(gameObjLayout);
        GameObjectPanel.setBackground(Color.orange);

        // Text Fields
        simpleObstacle = new JTextField(Integer.toString(SIMPLE_COUNT), 30);
        firmObstacle = new JTextField(Integer.toString(FIRM_COUNT), 30);
        explosiveObstacle = new JTextField(Integer.toString(EXPLOSIVE_COUNT), 30);
        giftObstacle = new JTextField(Integer.toString(GIFT_COUNT), 30);

        // Simple Obstacle Row
        ImageIcon simpleObsIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/simple_obstacle.png").getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
        GameObjectPanel.add(new JLabel(simpleObsIcon));
        GameObjectPanel.add(new JLabel("Number of simple obstacles"));
        GameObjectPanel.add(simpleObstacle);

        // Firm Obstacle Row
        ImageIcon firmObsIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/firm_obstacle_empty.png").getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
        GameObjectPanel.add(new JLabel(firmObsIcon));
        GameObjectPanel.add(new JLabel("Number of firm obstacles"));
        GameObjectPanel.add(firmObstacle);

        // Explosive Obstacle Row
        ImageIcon expObsIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/explosive_obstacle.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        GameObjectPanel.add(new JLabel(expObsIcon));
        GameObjectPanel.add(new JLabel("Number of explosive obstacles"));
        GameObjectPanel.add(explosiveObstacle);

        // Gift Obstacle Row
        ImageIcon giftObsIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/gift_obstacle.png").getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
        GameObjectPanel.add(new JLabel(giftObsIcon));
        GameObjectPanel.add(new JLabel("Number of gift obstacles"));
        GameObjectPanel.add(giftObstacle);

        // Obstacle Button
        obstacleButton = new JButton("Set Obstacles");
        obstacleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setObstacleSettings();
                layout = LC.getRandomLayout();
                gameStartButton.setEnabled(true);


            }
        });

        GameObjectPanel.add(new JLabel(""));
        GameObjectPanel.add(obstacleButton);
        GameObjectPanel.add(new JLabel(""));

        return GameObjectPanel;

    }


    private LayoutPanel initializeLayoutPanel() {
        return new LayoutPanel(layout,FRAME_WIDTH,FRAME_HEIGHT);
    }

    private JPanel runGamePanel(JFrame frame) {
        GridLayout panelLayout = new GridLayout(2, 0);
        JPanel runGamePanel = new JPanel(panelLayout);
        loadGameButton = new JButton("Load Game");
        gameStartButton = new JButton("Click to start the game!");
        gameStartButton.setEnabled(false);

        gameStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setRunSettings();
                notifyButtonisClickedListeners(username, id);

            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                for (ILoadListener listener : loadModeListeners) {
                    System.out.println(listener);
                    listener.onClickEvent();
                    gameStartButton.setEnabled(true);
                }

            }
        });

        runGamePanel.add(loadGameButton);
        runGamePanel.add(gameStartButton);
        return runGamePanel;
    }

    private JPanel ust () {
        GridLayout panelLayout = new GridLayout(5, 3);
        JPanel GameObjectPanel = new JPanel(panelLayout);
        GameObjectPanel.setBackground(Color.BLACK);

        return GameObjectPanel;
    }

    public void notifyButtonisClickedListeners(String username, String id) {
        System.out.println("ALL LISTENERS ARE NOTIFIED THAT THE BUTTON IS CLICKED \n\n\n");

        for (IRunListener listener : runModeListeners) {
            System.out.println(listener);
            listener.onClickEvent(this.runSettings, username, id);
        }
        this.setVisible(false);
        this.dispose();
    }

    public void setID(String  ID){
        this.id = ID;
    }
    public void setUserName(String  username){
        this.username = username;

    }

}