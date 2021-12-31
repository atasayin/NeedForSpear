package domain;

import UI.AuthorizeScreen;
import UI.PlaygroundScreen;

public class Main {
    static AuthorizeScreen autoMode;

    public static void main(String arr[]) {
            //main JFrame
            PlaygroundScreen nfs = new PlaygroundScreen();
            autoMode = new AuthorizeScreen(nfs);
            autoMode.setVisible(true);
            autoMode.addListener(nfs);
            // delete after this
        /*    Ymir ymir = new Ymir();
            Thread t = new Thread(ymir);
            t.start();*/
    }

}
