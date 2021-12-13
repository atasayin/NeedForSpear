package UI;

import domain.IAuthorizeListener;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizeScreen extends JFrame  {


    static final int SCREEN_WIDTH = 1500;
    static final int SCREEN_HEIGHT = 766;

    static final String USERNAME = "Attila";

    String FILEPATH = "NeedForSpear/src/saves/players.json";

    private JTextField userName;
    private JTextField ID;

    private JPanel info;
    private JPanel buttons;

    private boolean isUsernameExist = false;
    private boolean isLogin = false;
    private boolean isSignup = false;

    private java.util.List<IAuthorizeListener> autoModeListeners = new ArrayList<>();
    protected Playground nfs;

    public AuthorizeScreen(Playground nfs){
        this.nfs = nfs;
        initializeAuthorizeScreen();
        //add(initializeImagePanel());
        info = initializeInfoPanel();
        buttons = initializeButtonPanel();
        ImageIcon icon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/intro.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        add(new JLabel(icon));
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

        JLabel usernameLabel = new JLabel("  Username: ", SwingConstants.CENTER);
        ImageIcon usernameIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/user.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        usernameLabel.setIcon(usernameIcon);
        infoPanel.add(usernameLabel);
        userName = new JTextField(USERNAME, 30);
        infoPanel.add(userName);

        JLabel passwordLabel = new JLabel("Password: ", SwingConstants.CENTER);
        ImageIcon passwordIcon = new ImageIcon(new ImageIcon("NeedForSpear/src/assets/password.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        passwordLabel.setIcon(passwordIcon);
        infoPanel.add(passwordLabel);
        ID = new JTextField("1111", 8);
        infoPanel.add(ID);

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
                checkPlayerLogs();
                String username = userName.getText();
                String id = ID.getText();
                if(isLogin){
                    for (IAuthorizeListener listener : autoModeListeners) {
                        listener.onClickEvent(nfs, username, id);
                    }
                }

                // check database
            }
        });

        JButton signButton = new JButton("Sign Up");
        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean succSign = savePlayerLogs();
                String username = userName.getText();
                String id = ID.getText();
                if(succSign){
                    for (IAuthorizeListener listener : autoModeListeners) {
                        listener.onClickEvent(nfs,username, id);
                    }
                }
                // check database
            }

        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signButton);

        return buttonPanel;
    }

    private void checkPlayerLogs() {

        String checking = userName.getText();
        String id = ID.getText();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FILEPATH)) {
            Object obj = jsonParser.parse(reader);
            JSONObject doc = (JSONObject) obj;

            JSONArray usernameList = (JSONArray) doc.get("Username");
            JSONArray IDList = (JSONArray) doc.get("ID");

            for(int i=0; i<usernameList.size(); i++){
                String s = usernameList.get(i).toString();
                String m = IDList.get(i).toString();

                if(s.equals(checking)){
                    isUsernameExist = true;
                    if(m.equals(id)){
                        isLogin = true;
                        System.out.println("User is found.");
                        break;
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean savePlayerLogs() {
        checkPlayerLogs();

        if(isUsernameExist){
            System.out.println("User is already exist");
            return false;
        }

        String signing = userName.getText();
        String id = ID.getText();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FILEPATH)) {
            Object obj = jsonParser.parse(reader);
            JSONObject doc = (JSONObject) obj;

            JSONArray usernameList = (JSONArray) doc.get("Username");
            JSONArray IDList = (JSONArray) doc.get("ID");

            Document document = new Document();
            ArrayList<String> temp = new ArrayList<>();
            ArrayList<String> tempID = new ArrayList<>();

            for(int i=0; i<usernameList.size(); i++){
                String s = usernameList.get(i).toString();
                String m = IDList.get(i).toString();

                temp.add(s);
                tempID.add(m);
            }

            temp.add(signing);
            tempID.add(id);
            document.put("Username", temp);
            document.put("ID", tempID);

            try {
                FileWriter file = new FileWriter(FILEPATH);
                file.write(document.toJson());
                file.close();
                System.out.println("Signed up successfully.");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }




}
