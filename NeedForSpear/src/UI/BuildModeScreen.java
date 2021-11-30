package UI;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import domain.*;


@SuppressWarnings("serial")
public class BuildModeScreen extends JFrame {


    static final int FRAME_WIDTH = 1368;
    static final int FRAME_HEIGHT = 766;
    static final int UNITLENGTH_L = (int) (FRAME_HEIGHT * 0.1 + 0.5); // 10% of game height
    static final int STATISTICS_SCREEN_WIDTH = 200;

    static final String SAVE_METHOD_1 = "Database";
    static final String SAVE_METHOD_2 = "Local";


    private JTextField username;
    private JButton gameStart;

    private HashMap<String, Integer> runSettings;
    private List<IRunListener> runModeListeners = new ArrayList<>();

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

    private double isStationaryOrRotating(JRadioButton radiobtn, JRadioButton childRadiobtn) {
        if (radiobtn.isSelected()) {
            if (childRadiobtn.isSelected()) {
                return 1.0;
            } else {
                return 0.0;
            }
        } else {
            return -1.0;
        }
    }


    public BuildModeScreen() {
        initializeBuildScreen();
        add(initializeGameObjectPanel());
        add(initializeGameSettingsPanel());
        add(runGamePanel(this));
    }

    private void initializeBuildScreen() {
        this.setLayout(new GridLayout(3, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 1000);
        this.setLocationRelativeTo(null);
    }

    private JPanel initializeGameObjectPanel() {
        GridLayout gameObjLayout = new GridLayout(6, 2);
        JPanel GameObjectPanel = new JPanel(gameObjLayout);

        username = new JTextField("User Kelebisler", 30);

        GameObjectPanel.add(new JLabel("Number of Obstacle"));

        GameObjectPanel.add(new JLabel("Username:"));
        GameObjectPanel.add(username);
        return GameObjectPanel;
    }

    private JPanel initializeGameSettingsPanel() {
        GridLayout varLayout = new GridLayout(5, 3);
        JPanel GameSettingsPanel = new JPanel(varLayout);

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
            listener.onClickEvent(this.runSettings, username.getText());
        }
        this.setVisible(false);
        this.dispose();
    }

}