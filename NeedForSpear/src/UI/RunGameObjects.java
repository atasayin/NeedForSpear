package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.BitSet;

import javax.swing.*;

import domain.abilities.PaddleExpansion;
import domain.controller.KeyboardController;
import domain.* ;
import domain.obstacle.Obstacle;


@SuppressWarnings("serial")
public class RunGameObjects extends JPanel implements ActionListener, KeyListener, IGameListener,IChanceListener,ILoadListener {

    /////////////////////////////////////////////////////////////////////////////////////

    Timer tm = new Timer(TIMER_SPEED, this);

    BufferedImage img; // background
    String infoString = "";
    int infoRefreshCount;
    KeyboardController kc = new KeyboardController();
    Game game = Game.getInstance();
    CollisionChecker colCheck = CollisionChecker.getInstance();
    Boolean stop = false;
    private BitSet keyBits = new BitSet(256);
    private Integer chance =3;
    public static int frame_width;
    public static int frame_height;
    private JPanel chancePanel;
    private JPanel scorePanel;
    private JLabel scoreNameLabel;
    private JLabel scoreNumLabel;
    public int sil = 0;
    private  ImageIcon icon;
    private boolean update =false;

    public RunGameObjects(int width, int height) {
        this.frame_width = width;
        this.frame_height = height;
        try {
            initializeRunModeScreen();
            Game.getInstance().gameState.addListener(this);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

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

        try {
            drawPaddle(g2d, Game.getInstance().getPaddle(), frame_width, frame_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawBall(g2d, Game.getInstance().ball, frame_width, frame_height);

        //drawBall(g2d, player.getShooter());
        g2d.setTransform(old);
        int textWidth = g.getFontMetrics().stringWidth(infoString);
        g2d.drawString(infoString, this.getWidth() / 2 - textWidth / 2, 20);
    }

    private void drawObstacle(Graphics2D g2d, Obstacle d) {
        // TODO Auto-generated method stub
        SimpleObstacleView.getInstance().draw(g2d, d, frame_width, frame_height);

    }
    private void drawPaddle(Graphics2D g2d, Paddle d, int width, int height) throws IOException {
        // TODO Auto-generated method stub
        int width_ = Game.getInstance().getPaddle().getWidth();
        PaddleView.getInstance().draw(g2d, d, width_, height);

    }

    private void drawBall(Graphics2D g2d, Ball b, int width, int height) {
        // TODO Auto-generated method stub
        BallView.getInstance().draw(g2d, b, width, height);

    }

    private void drawComponent(Graphics2D g2d, DomainObject d) {
        // TODO Auto-generated method stub
        if (d instanceof Obstacle) {
            SimpleObstacleView.getInstance().draw(g2d, d, frame_width, frame_height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //Game.getInstance().gameState.checkCollisions();
            gameOverCheck();
            update();
            updateScore();
            if(update) {
                System.out.println("action preformed");
                updateChance();
                System.out.println(update);
                repaint();
            }
            repaint();
        try {
            ballChance();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //Game.getInstance().gameState.removeObjectsIfOutsideScreen();
            infoRefreshCount += TIMER_SPEED;
            if (infoRefreshCount >= INFO_REFRESH_PERIOD) {
                infoString = "";
                infoRefreshCount = 0;
            }
        }



    public void update() {
        // Obstacle positions will be updated when they start to move in the future
//        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
//            domainObject.updatePosition();
//            domainObject.updateAngle();
//        }
//        Game.getInstance().gameState.updatePaddlePosition();

        Obstacle toBeDeleted = null;
        Game.getInstance().getPaddle().updatePosition(0,0);
        Game.getInstance().ball.move();
        if (colCheck.checkPaddleBallCollision(Game.getInstance().ball, Game.getInstance().getPaddle())) {
            Game.getInstance().ball.reflectFromPaddle();
        }

        //if (Game.getInstance().ball.getPosVector().getY() < 0) Game.getInstance().ball.reflectFromHorizontal();

        for (Obstacle obs : Layout.obstacle_positions.keySet()) {
            if (colCheck.checkCollision(Game.getInstance().ball, obs)) {
                if (obs.getHit()){
                    Game.getInstance().getDomainObjectArr().remove(obs);
                    toBeDeleted = obs;
                }

                if (colCheck.findCollisionDirection(Game.getInstance().ball, obs)) {
                    Game.getInstance().ball.reflectFromVertical();
                } else {
                    Game.getInstance().ball.reflectFromHorizontal();
                }
            }
        }
        if (toBeDeleted != null) Layout.obstacle_positions.remove(toBeDeleted);
        sil++;
        System.out.println(sil);
        if (sil == 100) {
            PaddleExpansion pe = new PaddleExpansion();
            Thread t = new Thread(pe);
            t.start();
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.set(keyCode);

        // Pause (p)
        if (isKeyPressed(80)) {
            pauseGame();
            return;
        }

       // Resume (r)
        if (isKeyPressed(82)) {
            resumeGame();
            return;
        }

        // Save (s)
        if (isKeyPressed(80)) {
            saveGame();
            return;
        }

        // Load (l)
        if (isKeyPressed(76)){
            loadGame();
            return;
        }


        if (kc.getInput(keyBits)) { // when returns true restart
            tm.restart();
            Game.getInstance().gameState.isRunning = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            kc.released(Game.getInstance().gameState.getPC().getPaddle());
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return keyBits.get(keyCode);
    }

    private void pauseGame(){
        infoString = "Game Paused.";
        repaint();
        tm.stop();
        Game.getInstance().gameState.isRunning = false;
        kc.getInput(keyBits);

    }

    private void resumeGame(){
        infoString = "Game Resumed.";
        tm.restart();
        Game.getInstance().gameState.isRunning = true;
    }

    private void saveGame(){
        if (!tm.isRunning()) {
            infoString = "Game Saved.";
            kc.getInput(keyBits); // only works if game was paused
        } else {
            infoString = "Press \"Pause\" Button before saving.";
        }

    }

    private void loadGame(){
        if (!tm.isRunning()) {
            infoString = "Game Loaded.";
            kc.getInput(keyBits);
            tm.restart();// only works if game was paused
            Game.getInstance().gameState.isRunning = true;
            updateChance();
            System.out.println(Game.getInstance().gameState.getPlayer().getScore()+ "in loadgame run obj");
            scoreNumLabel.setText(Game.getInstance().getOldScore()+"");
        } else {
            infoString = "Press \"Pause\" Button before loading.";
        }

    }

    public void initializeRunModeScreen() throws IOException {
        this.setFocusable(true);
        scorePanel = initializeScorePanel();
        chancePanel =initializeChancePanel();
        chancePanel.setVisible(true);
        scorePanel.setVisible(true);
        this.add(chancePanel);
        this.add(scorePanel);
        tm.start();

    }

    private JPanel initializeScorePanel(){
        JPanel scoreP = new JPanel();
        scoreNameLabel = new JLabel("Score: ");
        scoreNumLabel = new JLabel("0");

        scoreP.add(scoreNameLabel);
        scoreP.add(scoreNumLabel);
        return scoreP;
    }
    private JPanel initializeChancePanel(){
        JPanel ChanceP = new JPanel();
        ImageIcon icon = new ImageIcon(new ImageIcon("src/assets/3heart.png").getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
        ChanceP.add(new JLabel(icon));

        return ChanceP;
    }

    private void updateScore(){
        int score = (int) Game.getInstance().getOldScore();
        scoreNumLabel.setText(score+"");
    }

    private static final int TIMER_SPEED = 50;
    private static final int INFO_REFRESH_PERIOD = 3000;

    @Override
    public void onClickEvent() {
        stop = true;
    }

    public void gameOverCheck() {

        Integer chance = Game.getInstance().gameState.getPlayer().getChance_points();

        Boolean isWin = Game.getInstance().getIsWin();

        Object[] options = { "OK" };
        if (chance <=0){
            tm.stop();
            //Game.getInstance().cancelTime();


            JOptionPane.showMessageDialog(Playground.jf,
                    "You are out of chance." + "Your score is "+(int) Game.getInstance().getOldScore(),
                    "Out of chance",
                    JOptionPane.WARNING_MESSAGE);
            Playground.jf.dispose();

        }
        else if (isWin){
            tm.stop();
            JOptionPane.showMessageDialog(Playground.jf,
                    "You win the game." + "Your score is "+(int) Game.getInstance().getOldScore());

            Playground.jf.dispose();
        }
    }



    public void ballChance() throws InterruptedException {
        if(stop){
            wait(200);

        }
    }

    @Override
    public void onLoseChance(Integer chance) {
        System.out.println("this chance is " +this.chance);
        System.out.println("chance is " +chance);
        this.chance = chance;
        System.out.println("this chance is " +this.chance);
        //updateChance();
        update =true;
        //chancePanel.removeAll();
    }

    public void updateChance(){

        chancePanel.removeAll();
        if(chance == 3){
            chancePanel.removeAll();
            icon = new ImageIcon(new ImageIcon("src/assets/3heart.png").getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
            chancePanel.add(new JLabel(icon));
            chancePanel.setVisible(true);
            update =false;
        }
        else if(chance == 2){
            chancePanel.removeAll();
             icon = new ImageIcon(new ImageIcon("src/assets/2heart.png").getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
             chancePanel.add(new JLabel(icon));
            chancePanel.setVisible(true);
            update =false;
        }
        else if(chance == 1){
            //chancePanel.removeAll();
             icon = new ImageIcon(new ImageIcon("src/assets/1heart.png").getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
            chancePanel.add(new JLabel(icon));
            chancePanel.setVisible(true);
            update =false;
        }
        else if(chance == 0){
            chancePanel.removeAll();
            icon = new ImageIcon(new ImageIcon("src/assets/0heart.png").getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT));
            chancePanel.add(new JLabel(icon));
            chancePanel.setVisible(true);
            update =false;
        }


    }

    @Override
    public void onClickEventDo() {
        System.out.println("OnclickEVetnDo");
        System.out.println(Game.getInstance().gameState.getPlayer().getScore()+ "in loadgame run obj");
        scoreNumLabel.setText(Game.getInstance().gameState.getPlayer().getScore()+"");

    }
}



