package domain;

import UI.BuildModeScreen;

import UI.PlayScreen;

import javax.swing.*;

public class Main {
    static JFrame frame;
    static BuildModeScreen buildMode;

    public static void main(String arr[]) {

        //main JFrame
        PlayScreen nfs = new PlayScreen();

        //starts with the build mode screen
        buildMode = new BuildModeScreen();
        buildMode.setVisible(true);
        buildMode.addListener(nfs);
        buildMode.addListener(Game.getInstance());

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
