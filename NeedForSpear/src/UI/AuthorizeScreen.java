package UI;

import domain.IAuthorizeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuthorizeScreen extends JFrame  {


    static final int SCREEN_WIDTH = 1500;
    static final int SCREEN_HEIGHT = 766;

    static final String USERNAME = "Attila";


    private JTextField userName;
    private JTextField password;

    private JPanel info;
    private JPanel buttons;

    private java.util.List<IAuthorizeListener> autoModeListeners = new ArrayList<>();
    protected Playground nfs;

    public AuthorizeScreen(Playground nfs){
        this.nfs = nfs;
        initializeAuthorizeScreen();
        //add(initializeImagePanel());
        System.out.println("authorizationnn");
        info = initializeInfoPanel();
        buttons = initializeButtonPanel();
        add(info,BorderLayout.EAST);
        add(buttons,BorderLayout.SOUTH);

    }

    private void initializeAuthorizeScreen() {

        this.setTitle("Need For Spear");
        this.setLayout(new GridLayout(3, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);



    }
    public void addListener(IAuthorizeListener listener) {
        autoModeListeners.add(listener);
    }

    public void removeListener(IAuthorizeListener listener) { autoModeListeners.remove(listener);}


    // Logo of NFS
  /*  private JPanel initializeImagePanel(){
        GridLayout logoLayout = new GridLayout(1, 1);
        JPanel logoPanel = new JPanel(logoLayout);

        ImageIcon imageIcon = new ImageIcon("assets/simpleball.png");
        gameImage = new JLabel(imageIcon);
        logoPanel.add(gameImage);
        return logoPanel;
    }*/

    // Username and Password
    private JPanel initializeInfoPanel(){
        GridLayout infoLayout = new GridLayout(2, 2);
        JPanel infoPanel = new JPanel(infoLayout);

        userName = new JTextField(USERNAME, 30);
        infoPanel.add(userName);



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
                for (IAuthorizeListener listener : autoModeListeners) {
                    System.out.println(listener);
                    listener.onClickEvent(nfs);
                }
                // check database
            }
        });

        JButton signButton = new JButton("Sign Up");
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IAuthorizeListener listener : autoModeListeners) {
                    System.out.println(listener);
                    listener.onClickEvent(nfs);
                }


                // check database
            }

        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signButton);

        return buttonPanel;
    }





}
