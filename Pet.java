/* Generates animal with desired occupatation */
public class Pet {
    private String[] speciesList = {
        "Dog", "Cat", "Parrot", "Hamster", "Snake", "Lizard"
    };

    //Private instance variables - general info (will not be changed)
    private String species;
    private int variation; //different colors/appearances per species (will have 3 types but there are only 2 rn)
    private Occupation occupation;
    private String img;

    //Private instance variable - health info (can be changed)
    private String name;
    private int level; //upgrade over time -> demo will upgrade manually
    private int hunger; 
    private boolean graduated = false;
    private boolean alive = true;

    private static int petNum = 0; //keeps track of total number of ppets

    public Pet() {
        petNum++;

        name = "Pet #" + petNum;
        species = speciesList[(int)(Math.random()*speciesList.length)];
        variation = (int)(Math.random()*2) +1;
        occupation = new Occupation();
        img = "img/pet/" + species + variation + ".jpg";

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
            if (level == 5 || level == 10) {
                graduated = occupation.promotion();
                System.out.println(name + " is now a " + occupation.getJobTitle());
                if (graduated) {
                    System.out.println("Congratulations! " + name + " has graduated!");
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

}