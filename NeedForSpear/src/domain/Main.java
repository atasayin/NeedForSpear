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

            //adds the player
            Game.getInstance().addPlayer(new Player(88999,"kelebis"));
            //buildMode.addListener((IRunListener) Game.getInstance().gameState.getPC());
            //buildMode.addListener(Game.getInstance().getPlayers().get(0).getPlayerState());


            // add listener to the playerstate to update statisticsScreen
            //Game.getInstance().getPlayers().get(0).getPlayerState().addListener(nfs.getStatisticsScreen());
            //Game.getInstance().getPlayers().get(0).getPlayerState().addTimelistener(nfs.getStatisticsScreen());

            // add listener to the shooter to update button and add shields
           // nfs.getStatisticsScreen().addListener(Game.getInstance().gameState.getPC());

    }

}
