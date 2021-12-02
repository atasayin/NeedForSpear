/*
package UI;

import domain.IRunListener;
import domain.IStartGameListener;
import domain.controller.LayoutController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuildModeScreen2 extends JPanel implements ActionListener, MouseListener {

    static final int SIMPLE_COUNT = 75;
    static final int FIRM_COUNT = 10;
    static final int EXPLOSIVE_COUNT = 5;
    static final int GIFT_COUNT = 10;

    static final int SCREEN_WIDTH = 1368;
    static final int SCREEN_HEIGHT = 766;

    private JTextField simpleObstacleCount;
    private JTextField firmObstacleCount;
    private JTextField explosiveObstacleCount;
    private JTextField giftObstacleCount;

    private JButton gameStart;
    private JTextField username;

    private HashMap<String, Double> obstacleSettings;
    private List<IStartGameListener> startlistener = new ArrayList<>();
    private List<IRunListener> runModeListeners = new ArrayList<>();
    private HashMap<String, Double> runSettings;

    public void addListener(IRunListener listener) {
        runModeListeners.add(listener);
    }

    public void removeListener(IRunListener listener) {
        runModeListeners.remove(listener);
    }
    private static final int TIMER_SPEED = 10;

    Timer timer = new Timer(TIMER_SPEED, this);

    LayoutController lc = new LayoutController();

    public void setObstacleSettings() {
        HashMap<String, Double> obstacleSettings = new HashMap<String, Double>();

        obstacleSettings.put("simpleObstacleCount", Double.parseDouble(simpleObstacleCount.getText()));
        obstacleSettings.put("firmObstacleCount", Double.parseDouble(firmObstacleCount.getText()));
        obstacleSettings.put("explosiveObstacleCount", Double.parseDouble(explosiveObstacleCount.getText()));
        obstacleSettings.put("giftObstacleCount", Double.parseDouble(giftObstacleCount.getText()));

        this.obstacleSettings = obstacleSettings;
    }

    public BuildModeScreen2() {
        initializeBuildScreen();
        add(initializeObstacleSettingsPanel());
    }

    // Initial Build Mode Screen and the timer
    public void initializeBuildScreen()  {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setFocusable(true);
        timer.start();
    }

    // UI Panel of Obstacle Settings
    private JPanel initializeObstacleSettingsPanel(){
        GridLayout gameObjLayout = new GridLayout(4, 3); // #Type of obstacles
        JPanel GameObjectPanel = new JPanel(gameObjLayout);

        // Text Fields
        simpleObstacleCount = new JTextField(Integer.toString(SIMPLE_COUNT), 30);
        firmObstacleCount = new JTextField(Integer.toString(FIRM_COUNT), 30);
        explosiveObstacleCount = new JTextField(Integer.toString(EXPLOSIVE_COUNT), 30);
        giftObstacleCount = new JTextField(Integer.toString(GIFT_COUNT), 30);

        // Simple Obstacle Row
        GameObjectPanel.add(new JLabel(new ImageIcon("assets/simple.png")));
        GameObjectPanel.add(new JLabel("Number of simple obstacles"));
        GameObjectPanel.add(simpleObstacleCount);

        // Firm Obstacle Row
        GameObjectPanel.add(new JLabel(new ImageIcon("assets/firm.png")));
        GameObjectPanel.add(new JLabel("Number of firm obstacles"));
        GameObjectPanel.add(firmObstacleCount);

        // Explosive Obstacle Row
        GameObjectPanel.add(new JLabel(new ImageIcon("assets/explosive.png")));
        GameObjectPanel.add(new JLabel("Number of explosive obstacles"));
        GameObjectPanel.add(explosiveObstacleCount);

        // Gift Obstacle Row
        GameObjectPanel.add(new JLabel(new ImageIcon("assets/gift.png")));
        GameObjectPanel.add(new JLabel("Number of gift obstacles"));
        GameObjectPanel.add(giftObstacleCount);

        return GameObjectPanel;

    }

    // Get random Layout after the obstacle settings
    private void getRandomLayout(){



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xMouse = e.getX();
        int yMouse = e.getY();




    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void notifyButtonisClickedListeners() {
        System.out.println("ALL LISTENERS ARE NOTIFIED THAT THE BUTTON IS CLICKED \n\n\n");

        for (IRunListener listener : runModeListeners) {
            listener.onClickEvent(this.runSettings, username.getText());
        }
        this.setVisible(false);
        this.dispose();
    }

    private JPanel initializeGameObjectPanel() {
        GridLayout gameObjLayout = new GridLayout(6, 2);
        JPanel GameObjectPanel = new JPanel(gameObjLayout);

        username = new JTextField("Curious Monkey", 30);

        GameObjectPanel.add(new JLabel("Username:"));
        GameObjectPanel.add(username);
        return GameObjectPanel;
    }
}


 */