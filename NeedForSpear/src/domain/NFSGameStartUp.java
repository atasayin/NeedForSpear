//package domain;
//
//import UI.OldPlayground;
//import UI.Playground;
//import domain.controller.PaddleController;
//
//import javax.swing.*;
//
//public class NFSGameStartUp {
//    private static OldPlayground oldPlayground;
//    private static PaddleController pc;
//
//    public static final int HEIGHT = 600;
//    public static final int WIDTH = 900;
//
//    public static void main(String[] args) {
//        Playground playScreen = new Playground();
//
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                CreateAndShowGUI();
//            }
//        });
//
//
//    }
//
//
//    private static void CreateAndShowGUI() {
//        pc = new PaddleController(HEIGHT,WIDTH);
//        oldPlayground = new OldPlayground(pc,HEIGHT,WIDTH);
//        //oldPlayground.setVisible(true);
//
//    }
//
//}
