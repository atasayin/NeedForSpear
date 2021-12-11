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

            //buildMode.addListener((IRunListener) Game.getInstance().gameState.getPC());
            //buildMode.addListener(Game.getInstance().getPlayers().get(0).getPlayerState());

    }

}
