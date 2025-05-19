import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

//timer stuff
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display implements ActionListener{
    private Player player;
    private Timer timer;

    public Display(Player p) {
        player = p; 
        timer = new Timer(1000, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.IncrementTimeScale();
        reloadPetDisplay();
        //System.out.println("Seconds passed: " + player.GetTimeScale());
        int random = (int)(Math.random()*10);
        //System.out.println("RANDOM: " + random);
        if (random == 1 && player.getTasks().size() > 0) {
            System.out.println("CURRENT TIME: " + player.GetTimeScale());
            System.out.println("NEW TASK OBTAINED!");
            giveTask();
        }
    }
    public void startTimer() {
        timer.start();
    }
    public void stopTimer() {
        timer.stop();
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

    //SORRY I was messing around with this method since i added some sprites -LIANA
    private static JFrame jframe;
    
    //store pet locations
    private static int[][] slots = {{0, 0}, {220, 0}, {440, 0}, {0, 300}, {220, 300}, {440, 300}};
    private static Pet[] petSlots = new Pet[6];

    public void initPetDisplay() {
        jframe = new JFrame("PetInfo");
        jframe.setLayout(null);
        jframe.setSize(1000, 700);

        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 700);
        jframe.setContentPane(layeredPane);

        loadMainDisplay();
        for (Pet p : player.getPets()) {
            loadPetDisplay(p);
        }
        timer.start();

         // Stop the timer when the window is closed - I copy adn pasted this chatgpt
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    System.out.println("Timer stopped because window closed");
                }
            }
        });

        JLabel bg = new JLabel(new ImageIcon("img/background.png"));
        bg.setBounds(0, 0, 1000, 700);
        layeredPane.add(bg, Integer.valueOf(0));  // layer 0 = bottom

        jframe.setVisible(true);
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
                JButton nameButton = createButton("Rename", x, y+260, 100, 20);
                nameButton.addActionListener(e -> { // set to new name
                    AnswerBox box = new AnswerBox(" ", pet, this); 
                });
                layeredPane.add(nameButton, Integer.valueOf(3));

                JButton lvlUpButton = createButton("Level Up (30 coins)", x, y+280, 200, 20);
                lvlUpButton.addActionListener(e -> { 
                    if (player.getCoins() >= 30) {
                        pet.levelUp();
                        player.modifyCoins(-30);
                        if (pet.getLevel() == 11) {
                            player.removeGraduate(pet);
                        }
                        reloadPetDisplay();
                    } else {
                        System.out.println("Sorry, you need 30 coins to level up " + pet.getName() + "!");
                    }
                });
                layeredPane.add(lvlUpButton, Integer.valueOf(3));

                JButton feedButton = createButton("FEED", x+100, y+260, 100, 20);
                feedButton.addActionListener(e -> { 
                    AnswerBox box = new AnswerBox("", pet, this, player);
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

    public void loadMainDisplay() {
        // Assume we have access to the layered pane
        JLayeredPane layeredPane = (JLayeredPane) jframe.getContentPane();

        JLabel greet = new JLabel("HELLO, " + player.getName()); 
        greet.setBounds(700, 20, 100, 20);
        layeredPane.add(greet, 3);
        JLabel coins = new JLabel("COINS: " + player.getCoins());
        coins.setBounds(700, 50, 100, 20);
        layeredPane.add(coins, 3);
        JLabel food = new JLabel("AVAILABLE FOOD: " + player.getFood());
        food.setBounds(700, 70, 300, 20);
        layeredPane.add(food, 3);

        JButton buyPetButton = new JButton("Buy new pet (100 coins)");
        buyPetButton.setBounds(700, 120, 200, 50);
        buyPetButton.addActionListener(e -> {
            if (player.getCoins() >= 100) {
                player.AddPet(new Pet(player.getName()));
                player.modifyCoins(-100);
            }
            
            reloadPetDisplay();
        }); 
        layeredPane.add(buyPetButton, 3);

        JButton buyFoodButton = new JButton("Buy Food (10 food for 5 coins)");
        buyFoodButton.setBounds(700, 170, 200, 50);
        buyFoodButton.addActionListener(e -> {
            if (player.getCoins() >= 5) {
                player.modifyCoins(-5);
                player.modifyFood(10);
            }
            
            //reloadPetDisplay();
        }); 
        layeredPane.add(buyFoodButton, 3);

        JButton createTaskButton = new JButton("CREATE TASK");
        createTaskButton.setBounds(700, 240, 200, 50);
        createTaskButton.addActionListener(e -> {
            AnswerBox box = new AnswerBox("Task name?", new Task(), this, player);
        }); 
        layeredPane.add(createTaskButton, 3);

        JButton getTask = new JButton("GET RANDOM TASK");
        getTask.setBounds(700, 300, 200, 50);
        getTask.addActionListener(e -> {
            if (player.getTasks().size() > 0) {
                giveTask();
            } else {
                System.out.println("You have already finished all of your tasks!");
            }
            
        });
        layeredPane.add(getTask);

        JButton displayTasks = new JButton("SHOW REMAINING TASKS");
        displayTasks.setBounds(700, 360, 200, 50);
        displayTasks.addActionListener(e -> {
            System.out.println("---REMAINING TASKS---");
            for (Task t: player.getTasks()) {
                System.out.println(t);
                System.out.println();
            }
            System.out.println("-----END-----");
        });
        layeredPane.add(displayTasks);

        JButton listGrads = new JButton("LIST GRADUATES");
        listGrads.setBounds(700, 450, 200, 20);
        listGrads.addActionListener(e -> {
            System.out.println("****GRADUATES****");
            for (Pet g: player.getGraduates()) {
                System.out.println(g);
                System.out.println();
            }
            System.out.println("*****END*****");
        });
        layeredPane.add(listGrads);

        JButton listDeaths = new JButton("CEMETERY");
        listDeaths.setBounds(700, 480, 200, 20);
        listDeaths.addActionListener(e -> {
            System.out.println("****RIP****");
            for (Pet g: player.getDeaths()) {
                System.out.println(g);
                System.out.println();
            }
            System.out.println("*****END*****");
        });
        layeredPane.add(listDeaths);
    }

    public void reloadPetDisplay() {
        // Pet[] holder = petSlots;
        petSlots = new Pet[6];
        
        jframe.getContentPane().removeAll();
        
        //update so if any pets die they get deleetd
        for (int i=player.getPets().size()-1; i>=0; i--) {
            Pet current = player.getPets().get(i);
            if (!current.isAlive()) {
                player.removeDead(current);
            }
        }
        loadMainDisplay();
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

    public void printInfo() {
        System.out.println("***");
        System.out.println("TIME: " + player.GetTimeScale());
        int slot = 0;
        for (Pet p : petSlots) {
            if (p != null) {
                System.out.println("Slot #" + slot);
                System.out.println(p);
                slot++;
            }
        }
    }

    public void giveTask() {
        stopTimer();

        Task t = player.PickTask();

        JFrame task = new JFrame("NEW TASK");
        task.setLayout(null);
        task.setSize(500, 500);

        JLabel taskName = new JLabel("TASK NAME: " + t.GetName());
        taskName.setBounds(50, 50, 400, 20);
        task.add(taskName);

        JLabel taskDesc = new JLabel("DESCRIPTION: " + t.GetDescription());
        taskDesc.setBounds(50, 80, 400, 20);
        task.add(taskDesc);

        JLabel taskPayment = new JLabel("COINS TO EARN: " + t.GetCoinAmount());
        taskPayment.setBounds(50, 110, 400, 20);
        task.add(taskPayment);

        JLabel taskFeed = new JLabel("FOOD TO EARN: " + t.GetFoodAmount());
        taskFeed.setBounds(50, 140, 400, 20);
        task.add(taskFeed);

        JButton complete = new JButton("COMPLETED");
        complete.addActionListener(e -> {
            startTimer();
            player.modifyCoins(t.GetCoinAmount());
            player.modifyFood(t.GetFoodAmount());

            player.SetCompletedTasks(t);
            reloadPetDisplay();

            task.setVisible(false);
            task.dispose();
        }); 
        complete.setBounds(100, 300, 150, 100);
        task.add(complete);

        JButton skip = new JButton("SKIP");
        skip.addActionListener(e -> {
            startTimer();
            task.setVisible(false);
            task.dispose();
        }); 
        skip.setBounds(300, 300, 150, 100);
        task.add(skip);

        task.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        task.setVisible(true);
    }
}