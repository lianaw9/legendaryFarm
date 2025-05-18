import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Display {
    private Player player;
    public Display(Player p) {
        player = p; 
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
            AnswerBox box = new AnswerBox("Task name?", new Task());
        });

        frame.setVisible(true);

    }

    //SORRY I was messing around with this method since i added some sprites -LIANA
    private static JFrame jframe;
    
    //store pet locations
    private static int[][] slots = {{0, 0}, {220, 0}, {440, 0}, {0, 300}, {220, 300}, {440, 300}};
    private static Pet[] petSlots = new Pet[6];

    public void initPetDisplay() {
        jframe = new JFrame("PetInfo");
        jframe.setLayout(null);
        jframe.setSize(1000, 700);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 700);
        jframe.setContentPane(layeredPane);
    }

    /*PRECONDITION:  */
    public void loadPetDisplay(Pet pet) {
        boolean canLoad = false; //checks if there is empty space in petslots
        int x = 0;
        int y = 0;

        // Assume we have access to the layered pane
        JLayeredPane layeredPane = (JLayeredPane) jframe.getContentPane();

        JLabel bg = new JLabel(new ImageIcon("img/background.png"));
        bg.setBounds(0, 0, 1000, 700);
        layeredPane.add(bg, Integer.valueOf(0));  // layer 0 = bottom

        try{
            BufferedImage bimg = ImageIO.read(new File(pet.getImage()));
            bimg = resize(bimg, 200, 200);

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
            //System.out.println(x + " " + y);
            
            if (canLoad) {
                JLabel petImg = new JLabel();
                petImg.setIcon(new ImageIcon(bimg)); //Ok i added this so that petImg is the bimg that was set up earlier -LIANA
                petImg.setBounds(x, y, 200, 200);
                layeredPane.add(petImg, Integer.valueOf(1));

                for (int i=0; i<pet.infoArray().length; i++) {
                    //System.out.println(pet.infoArray()[i]);
                    JLabel petInfo = new JLabel(pet.infoArray()[i]);
                    petInfo.setBounds(x, y+100+i*10, 200, 200);
                    layeredPane.add(petInfo, Integer.valueOf(2)); // higher layer
                }
                JButton nameButton = createButton("Change Name", x, y+260, 100, 20);
                nameButton.addActionListener(e -> { // set to new name
                    AnswerBox box = new AnswerBox(" ", pet, this); 
                });
                layeredPane.add(nameButton, Integer.valueOf(3));

                JButton lvlUpButton = createButton("Level Up", x+100, y+260, 100, 20);
                lvlUpButton.addActionListener(e -> { 
                    if (player.getCoins() >= 30) {
                        pet.levelUp();
                        player.modifyCoins(-30);
                        if (pet.getLevel() == 10) {
                            player.removeGraduate(pet);
                        }
                        reloadPetDisplay();
                    } else {
                        System.out.println("Sorry, you need 30 coins to level up " + pet.getName() + "!");
                    }
                });
                layeredPane.add(lvlUpButton, Integer.valueOf(3));

                JButton feedButton = createButton("FEED", x, y+280, 200, 20);
                feedButton.addActionListener(e -> { 
                    AnswerBox box = new AnswerBox("", pet, this, player.getFood());
                });
                layeredPane.add(feedButton, Integer.valueOf(3));
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        jframe.revalidate(); // tells Swing to recalculate layout
        jframe.repaint();    // tells Swing to repaint the UI

        jframe.setVisible(true);
        x = 0;
        y = 0;
    }

    public void reloadPetDisplay() {
        // Pet[] holder = petSlots;
        petSlots = new Pet[6];
        
        jframe.getContentPane().removeAll();
        //int counter = 0;
        for (Pet p : player.getPets()) {
            if (p != null) {
                loadPetDisplay(p);
            }
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

    public static Pet[] getPets() { // probably a better way to do this but whateverrr, used to reload all the pets from AnswerBox
        return petSlots;
    }


}