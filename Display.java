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
    private static JFrame jframe;
    private static int x;
    private static int y;

    public static void initPetDisplay() {
        jframe = new JFrame("PetInfo");
        x = 0;
        y = 0;
        jframe.setLayout(new BorderLayout());
        jframe.setSize(700, 700);
        jframe.setVisible(true);
    }

    private static int column = 1;
    public static void loadPetDisplay(Pet pet) {
        int width = 200;
        int height = 200;
        try{
            BufferedImage bimg = ImageIO.read(new File(pet.getImage()));
            bimg = resize(bimg, 200, 200);

            //jframe.setDefaultCloseOperation(
            //    JFrame.EXIT_ON_CLOSE);
            

            JLabel petImg = new JLabel();
            petImg.setIcon(new ImageIcon(bimg)); //Ok i added this so that petImg is the bimg that was set up earlier -LIANA
            petImg.setBounds(x, y, width, height);
            System.out.println(); // ID ONT KNOW WHY IT ONLY WORKS WITH THIS PRNT STTEMNT HERE
            jframe.add(petImg);
            if (column == 3) {
                y += height + 100;
                x = 0;
                column = 1;
                System.out.println("CONDITION 1");
            } else {
                x += width + 20;
                column++;
                System.out.println("CONDITION 2");
            }

            for (int i=0; i<pet.infoArray().length; i++) {
                System.out.println(pet.infoArray()[i]);
                JLabel petInfo = new JLabel(pet.infoArray()[i]);
                petInfo.setBounds(x, y+200+i*10, 200, 30);
                jframe.add(petInfo);
            }

            
            //JLabel petInfo = new JLabel(pet.toString()); // format is odd in jlabel for some reason
            //petInfo.setBounds(jframe.getWidth()/2, 0, 300, 80); why doesn't this work?
            //jframe.add(petInfo);
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