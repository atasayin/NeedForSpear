package UI;

import domain.DomainObject;
import domain.Game;
import domain.Paddle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimplePaddleView implements Drawable {

    BufferedImage paddle_img;
    static SimplePaddleView instance;

    private SimplePaddleView() {
    }

    public static SimplePaddleView getInstance() {
        if (instance == null) {
            instance = new SimplePaddleView();
            try {
                instance.fillImgs();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    private void fillImgs() throws IOException {
        // TODO Auto-generated method stub
        paddle_img = ImageIO.read(new File("src/assets/paddle.png"));

        // scale to unit length L
        Image scaled = paddle_img.getScaledInstance(100, 10,
                BufferedImage.SCALE_SMOOTH);
        if (scaled instanceof BufferedImage)
            paddle_img = (BufferedImage) scaled;

        // Create a buffered image with transparency
        BufferedImage new_bimage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = new_bimage.createGraphics();
        bGr.drawImage(scaled, 0, 0, null);
        bGr.dispose();

        paddle_img = new_bimage;
    }

    @Override
    public void draw(Graphics2D g2d, DomainObject domainObject) {
        // TODO Auto-generated method stub
        Paddle paddle = (Paddle) domainObject;

        g2d.drawImage(paddle_img, paddle.getPosVector().getX(), paddle.getPosVector().getY(), null);

    }


}
