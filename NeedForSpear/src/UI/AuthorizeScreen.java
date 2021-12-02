package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizeScreen extends JFrame {

    static final int SCREEN_WIDTH = 1368;
    static final int SCREEN_HEIGHT = 766;

    static final String USERNAME = "Attila";

    private JLabel gameImage;
    private JTextField userName;
    private JTextField password;

    public AuthorizeScreen(){
        initializeAuthorizeScreen();
        add(initializeImagePanel());

    }

    private void initializeAuthorizeScreen() {
        this.setTitle("Need For Spear");
        this.setLayout(new GridLayout(3, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    // Logo of NFS
    private JPanel initializeImagePanel(){
        GridLayout logoLayout = new GridLayout(1, 1);
        JPanel logoPanel = new JPanel(logoLayout);

        ImageIcon imageIcon = new ImageIcon("assets/simpleball.png");
        gameImage = new JLabel(imageIcon);
        logoPanel.add(gameImage);
        return logoPanel;
    }

    // Username and Password
    private JPanel initializeInfoPanel(){
        GridLayout infoLayout = new GridLayout(2, 2);
        JPanel infoPanel = new JPanel(infoLayout);

        userName = new JTextField(USERNAME, 30);

        return infoPanel;

    }

    // Sign Up or Login
    private JPanel initializeButtonPanel(){
        GridLayout buttonLayout = new GridLayout(1, 2);
        JPanel buttonPanel = new JPanel(buttonLayout);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check database
            }
        });

        JButton signButton = new JButton("Sign Up");
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check database
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signButton);

        return buttonPanel;
    }





}
