import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Display {
    public static void main(String[] args) {
        // JFrame jframe = new JFrame("Image");
        // //jframe.setLayout(new BorderLayout());
        // jframe.setSize(600, 500);
        // //jframe.setDefaultCloseOperation(
        // //    JFrame.EXIT_ON_CLOSE);
        // jframe.add(loadImage("img/pet/Cat1.jpg"));
        // jframe.add(loadImage("img/pet/Cat2.jpg"));

        // jframe.setVisible(true);
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

    public static void initMainDisplay() {
        JFrame frame = new JFrame("Main Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(null);

        // create buttons
        JButton buyPet = createButton("buy a pet", 10, 10, 100, 200);
        JButton createTask = createButton("create task", 100, 100, 200, 400);     
        frame.add(buyPet);
        frame.add(createTask);

        // listeners
        buyPet.addActionListener(e -> {
            System.out.println("buy pet clicked");
        });
        createTask.addActionListener(e -> {
            System.out.println("create task clicked");
            AnswerBox box = new AnswerBox("Task name?");
            // need to add a way to get the text from answer box once it's submitted without halting everythin else
        });

        frame.setVisible(true);

    }

    //SORRY I was messing around with this method since i added some sprites -LIANA
    public static void loadPetDisplay(Pet pet) {
        JFrame jframe = new JFrame("PetInfo");
        
        int width = 0;
        int height = 0;
        try{
            BufferedImage bimg = ImageIO.read(new File(pet.getImage()));
            width = bimg.getWidth()/2;
            height = bimg.getHeight()/2;
            bimg = resize(bimg, 200, 200);

            
            //jframe.setLayout(new BorderLayout());
            jframe.setSize(700, 400);
            //jframe.setDefaultCloseOperation(
            //    JFrame.EXIT_ON_CLOSE);

            //JLabel petImg = loadImage(pet.getImage());
            JLabel petImg = new JLabel();
            petImg.setIcon(new ImageIcon(bimg)); //Ok i added this so that petImg is the bimg that was set up earlier -LIANA
            petImg.setBounds(jframe.getWidth()/2 - (width/2), 50, width, height); // auto place image in top center
            jframe.add(petImg);

            JLabel petInfo = new JLabel(pet.toString()); // format is odd in jlabel for some reason
            //petInfo.setBounds(jframe.getWidth()/2, 0, 300, 80); why doesn't this work?
            jframe.add(petInfo);



        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        


        jframe.setVisible(true);
    }

    //bro i 100% just copy and paste from gogle ai 
    private static BufferedImage resize(BufferedImage og, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, og.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(og, 0, 0, w, h, null);
        g.dispose();
        return resizedImage;
    }

    public static JButton createButton(String text, int xpos, int ypos, int width, int height) {
        JButton button = new JButton(text);
        button.setLocation(xpos, ypos); // Set position
        button.setSize(width, height); // Set size

        return  button;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        //default size/pos

        return  button;
    }


}