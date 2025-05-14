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

    public static void loadPetDisplay(Pet pet) {
        JFrame jframe = new JFrame("PetInfo");
        
        int width = 0;
        int height = 0;
        try{
            BufferedImage bimg = ImageIO.read(new File(pet.getImage()));
            width = bimg.getWidth();
            height = bimg.getHeight();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        //jframe.setLayout(new BorderLayout());
        jframe.setSize(700, 400);
        //jframe.setDefaultCloseOperation(
        //    JFrame.EXIT_ON_CLOSE);

        JLabel petImg = loadImage(pet.getImage());
        petImg.setBounds(jframe.getWidth()/2 - (width/2), 50, width, height); // auto place image in top center
        jframe.add(petImg);

        JLabel petInfo = new JLabel(pet.toString()); // format is odd in jlabel for some reason
        //petInfo.setBounds(jframe.getWidth()/2, 0, 300, 80); why doesn't this work?
        jframe.add(petInfo);


        jframe.setVisible(true);
    }


}