package domain;

import UI.AuthorizeScreen;
import UI.BuildModeScreen;

import UI.Playground;

import javax.swing.*;

public class Main {
    static JFrame frame;
    static BuildModeScreen buildMode;
    static AuthorizeScreen autoMode;

    public static void main(String arr[]) {
            //main JFrame
            Playground nfs = new Playground();
            autoMode = new AuthorizeScreen(nfs);
            autoMode.setVisible(true);
            autoMode.addListener(nfs);
    }

}
