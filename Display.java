import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Display {
    public static void main(String[] args) {
        JFrame jframe = new JFrame("Image");
        //jframe.setLayout(new BorderLayout());
        jframe.setSize(600, 500);
        //jframe.setDefaultCloseOperation(
        //    JFrame.EXIT_ON_CLOSE);
        jframe.add(loadImage("img/pet/Cat1.jpg"));
        jframe.add(loadImage("img/pet/Cat2.jpg"));
        jframe.setVisible(true);
    }
    public static JLabel loadImage(String fileName) {
        BufferedImage image;
        JLabel imageContainer;

        try{
            image = ImageIO.read(new File(fileName));
            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

}