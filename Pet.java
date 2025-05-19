/* Generates animal with desired occupatation */

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class Pet {
    private String[] speciesList = {
        "Dog", "Cat", "Parrot", "Hamster", "Snake", "Lizard"
    };

    //Private instance variables - general info (will not be changed)
    private String species;
    private int variation; //different colors/appearances per species (3 types)
    private Occupation occupation;
    private String img;
    private String owner;

    //Private instance variable - health info (can be changed)
    private String name;
    private int level; //upgrade over time -> demo will upgrade manually
    private int hunger; 
    private boolean alive = true;

    private static int petNum = 0; //keeps track of total number of ppets

    public Pet(String ownerName) {
        petNum++;

        name = "Pet #" + petNum;
        species = speciesList[(int)(Math.random()*speciesList.length)];
        variation = (int)(Math.random()*3) +1;
        occupation = new Occupation();
        owner = ownerName;
        //creates an image combining species and occupation and puts it in playerpets under the player's name
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("img/pet/" + species + variation + ".png"));
            BufferedImage overlayImage = ImageIO.read(new File("img/occupation/" + occupation.getDreamJob() + ".png"));
            Graphics2D g2d = backgroundImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(overlayImage, 0, 0, null);
            g2d.dispose();

            ImageIO.write(backgroundImage, "png", new File("img/playerpets/" + owner + "_" + name + ".png"));
            img = "img/playerpets/" + owner + "_" + name + ".png";
        } catch (Exception e) {
            System.out.println("Error occured.");
            img = "img/pet/" + species + variation + ".png";
        }

        level = 0;
        hunger = 100;
    }

    //getters for non-modifiable data
    public String getSpecies() {return species;}
    public int getVariation() {return variation;}
    public String getFinalOccuptation() {return occupation.getDreamJob();} //what bro wants to be (ex. teacher)
    public String getCurrentOccupation() {return occupation.getJobTitle();} //what bro is rn (ex. student)
    public String getImage() {return img;}
    
    //modifiable data
    public String getName() {return name;}
    public boolean isAlive() {return alive;}
    public void setName(String n) {name = n;}
    public int getLevel() {return level;}
    

    public void modifyHunger(int n) {
        if (alive) {
            if (hunger+n >= 100) {
                hunger = 100;
                System.out.println(name + " is full!");
            } else if (hunger+n <= 0) {
                hunger = 0;
                System.out.println(name + " has died.");
                alive = false;
            } else {
                hunger += n;
                if (hunger <= 15) {
                    System.out.println(name + " is getting very hungry...");
                } 
            }
        }
    }

    public void levelUp() {
        if (alive) {
            level++;
            if (level == 5 || level >= 10) {
                occupation.promotion();
                System.out.println(name + " is now a " + occupation.getJobTitle() + ". ");
                if (level > 10) {
                    System.out.println("Congratulations! " + name + " has graduated!");
                } else if (level == 10) {
                    System.out.println("Level up one more time to graduate!");
                }
            }
        }
    }

    @Override 
    public String toString() { //prolly wont end up using this in the final beta but it is useful to test info
        String s = "----- \n";
        s+= "A " + species + "(Type " + variation + ") named " + name + " who wants to be a " + getFinalOccuptation() + "\n";
        s+= "Current Occupation: " + occupation.getJobTitle() + "\n";
        s+= "Level: " + level + "\n";
        s+= "Hunger: " + hunger + "\n";
        s+= "Alive: " + alive + "\n";
        s+= "-----\n";

        return s;

    }

    public String[] infoArray() {
        String[] info = new String[4];
        info[0]= "NAME: " + name;
        info[1] = "CURRENTLY: " + occupation.getJobTitle();
        info[2] = "LEVEL: " + level;
        info[3] = "HUNGER: " + hunger;
        return info;
    }

}