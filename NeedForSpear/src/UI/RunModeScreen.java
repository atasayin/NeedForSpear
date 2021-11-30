package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import domain.controller.KeyboardController;
import domain.* ;
import domain.obstacle.Obstacle;


@SuppressWarnings("serial")
public class RunModeScreen extends JPanel implements ActionListener, KeyListener {

    Timer tm = new Timer(TIMER_SPEED, this);
    BufferedImage img; // background
    String infoString = "";
    int infoRefreshCount;
    KeyboardController kc = new KeyboardController();
    Game game = Game.getInstance();


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.drawImage(img, 0, 0, null);

        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
            drawComponent(g2d, domainObject);
            g2d.setTransform(old);

        }

        drawPaddle(g2d, Game.getInstance().PC.getPaddle());

        //drawBall(g2d, player.getShooter());
        g2d.setTransform(old);
        int textWidth = g.getFontMetrics().stringWidth(infoString);
        g2d.drawString(infoString, this.getWidth() / 2 - textWidth / 2, 20);
    }

    private void drawObstacle(Graphics2D g2d, Obstacle d) {
        // TODO Auto-generated method stub
        SimpleObstacleView.getInstance().draw(g2d, d);

    }
    private void drawPaddle(Graphics2D g2d, Paddle d) {
        // TODO Auto-generated method stub
        SimplePaddleView.getInstance().draw(g2d, d);

    }

    private void drawComponent(Graphics2D g2d, DomainObject d) {
        // TODO Auto-generated method stub
        if (d instanceof Obstacle) {
            SimpleObstacleView.getInstance().draw(g2d, d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        gameOverCheck();
        //Game.getInstance().gameState.checkCollisions();
        update();
        repaint();
        //dropObjects();
        //Game.getInstance().gameState.removeObjectsIfOutsideScreen();
        infoRefreshCount += TIMER_SPEED;
        if (infoRefreshCount >= INFO_REFRESH_PERIOD) {
            infoString = "";
            infoRefreshCount = 0;
        }

    }

    private boolean checkInventoryIsOut(HashMap<Integer, Integer> inventory) {
        if (inventory.size() == 0)
            return false;
        if (inventory.get(1) == 0 && inventory.get(2) == 0 && inventory.get(3) == 0 && inventory.get(4) == 0)
            return true;
        return false;
    }

    private boolean checkDomainObjectsAreOut(HashMap<Integer, Integer> inventory) {

        if (inventory.size() != 0) {
            if (inventory.get(1) == 0 && inventory.get(2) == 0 && inventory.get(3) == 0 && inventory.get(4) == 0)
                return true;
        }
        return false;
    }

    public void gameOverCheck() {

        double t = Game.getInstance().gameState.getTime();
        if (t <= 0)
            gameOver("time");
        if (Game.getInstance().getPlayers().get(0).getPlayerState().getChance_points() <= 0)
            gameOver("health");
        if (checkDomainObjectsAreOut(Game.getInstance().gameState.ObstacleCounts) && checkObstacleOnScreen())
            gameOver("domain");

    }

    private boolean checkObstacleOnScreen() {
        for (DomainObject d : Game.getInstance().getDomainObjectArr()) {
            if (d instanceof Obstacle) {
                return false;
            }
        }
        return true;
    }

    private void gameOver(String reason) {
        tm.stop();
        Game.getInstance().cancelTime();
        Object[] options = { "OK" };
        String message;
        switch (reason) {
            case "time":
                message = "Time is up!";
                break;
            case "health":
                message = "You are dead.";
                break;
            case "inventory":
                message = "Out of atoms!";
                break;
            case "domain":
                message = "Out of molecules.";
                break;
            default:
                message = "Time is up!";
                break;
        }

        int input = JOptionPane.showOptionDialog(null,
                "Score " + String.format("%.2f", Game.getInstance().getPlayers().get(0).getPlayerState().getScore()),
                "GameOver: " + message, JOptionPane.ERROR_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);

        if (input == JOptionPane.OK_OPTION || input == JOptionPane.CLOSED_OPTION) {
            PlayScreen.GameOver();
        }
    }

  /*
    private void dropObjects() {
        Game.getInstance().gameState.addAvailableDomainObjects(TIMER_SPEED);
    }

   */

    public void update() {
        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
            domainObject.updatePosition();
            domainObject.updateAngle();
        }
        Game.getInstance().gameState.updatePaddlePosition();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int input = e.getKeyCode();
        // pause resume
        switch (input) {
            case 80: // p: pause
                infoString = "Game Paused.";
                repaint();
                tm.stop();
                Game.getInstance().gameState.isRunning = false;
                return;
            case 82: // r: resume
                infoString = "Game Resumed.";
                tm.restart();
                Game.getInstance().gameState.isRunning = true;

                break;
            case 83: // s: save
                if (!tm.isRunning()) {
                    infoString = "Game Saved.";
                    kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle()); // only works if game was paused
                    return;
                } else {
                    infoString = "Press \"Pause\" Button before saving.";
                    return;
                }
            case 76: // l: load
                if (!tm.isRunning()) {
                    infoString = "Game Loaded.";
                    kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle());
                    tm.restart();// only works if game was paused
                    Game.getInstance().gameState.isRunning = true;
                    Game.getInstance().getPlayers().get(0).getPlayerState().notifyAllInventoryListeners("all");
                    return;
                } else {
                    infoString = "Press \"Pause\" Button before loading.";
                    return;
                }
            default:
        }

        if (kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle())) { // when returns true restart
            tm.restart();
            Game.getInstance().gameState.isRunning = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            kc.released(Game.getInstance().gameState.getPC().getPaddle());
    }

    public RunModeScreen() {
        this.setSize(1366, 768);
        try {
            initializeRunModeScreen();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void initializeRunModeScreen() throws IOException {
        this.setFocusable(true);
        //img = ImageIO.read(new File("src/UI.assets/space.png"));
        tm.start();


    }

    private static final int TIMER_SPEED = 10;
    private static final int INFO_REFRESH_PERIOD = 3000;

}



