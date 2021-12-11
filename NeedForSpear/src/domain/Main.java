package domain;

import UI.AuthorizeScreen;
import UI.Playground;

public class Main {
    static AuthorizeScreen autoMode;

    public static void main(String arr[]) {
            //main JFrame
            Playground nfs = new Playground();
            autoMode = new AuthorizeScreen(nfs);
            autoMode.setVisible(true);
            autoMode.addListener(nfs);
    }

}
