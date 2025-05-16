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
    private static int x =0;
    private static int y =0;
    
    //store pet locations
    private static int[][] slots = {{0, 0}, {220, 0}, {440, 0}, {0, 300}, {220, 300}, {440, 300}};
    private static Pet[] petSlots = new Pet[6];

    public static void initPetDisplay() {
        jframe = new JFrame("PetInfo");
        jframe.setLayout(null);
        jframe.setSize(700, 700);
        jframe.setVisible(true);
    }

    /*PRECONDITION:  */
    public static void loadPetDisplay(Pet pet) {
        boolean canLoad = false;
        int x = 0;
        int y = 0;
        try{
            BufferedImage bimg = ImageIO.read(new File(pet.getImage()));
            bimg = resize(bimg, 200, 200);
            System.out.println("HELLO");

            //jframe.setDefaultCloseOperation(
            //    JFrame.EXIT_ON_CLOSE);
            for (int i=0; i<petSlots.length; i++) {  
                if (petSlots[i] == null) {
                    x = slots[i][0];
                    y= slots[i][1];
                    petSlots[i] = pet;
                    canLoad = true;
                    break;
                } 
            }
            System.out.println(x + " " + y);
            
            if (canLoad) {
                JLabel petImg = new JLabel();
                petImg.setIcon(new ImageIcon(bimg)); //Ok i added this so that petImg is the bimg that was set up earlier -LIANA
                petImg.setBounds(x, y, 200, 200);
                jframe.add(petImg);

                for (int i=0; i<pet.infoArray().length; i++) {
                    System.out.println(pet.infoArray()[i]);
                    JLabel petInfo = new JLabel(pet.infoArray()[i]);
                    petInfo.setBounds(x, y+100+i*10, 200, 200);
                    jframe.add(petInfo);
                }
                JButton button = new JButton("Change name");
                button.setLocation(x, y+260); // Set position
                button.setSize(200, 20); // Set size
                button.addActionListener(e -> {
                    AnswerBox box = new AnswerBox(" ");
                    pet.setName(box.getText());// need to add a way to get the text from answer box once it's submitted without halting everythin else
                });
                jframe.add(button);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        jframe.revalidate(); // tells Swing to recalculate layout
        jframe.repaint();    // tells Swing to repaint the UI

        jframe.setVisible(true);
    }

    private static void writePetInfo() {
        
    }

    public static void test() {
        try {
            BufferedImage img = ImageIO.read(new File("img/playerpets/placeholder.png"));
            JLabel petImg = new JLabel();
            petImg.setIcon(new ImageIcon(img)); //Ok i added this so that petImg is the bimg that was set up earlier -LIANA
            petImg.setBounds(220, 0, 200, 200);
            jframe.add(petImg);
            jframe.setVisible(true);
            
        } catch (Exception e) {
            System.out.println("EROR");
        }
        
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