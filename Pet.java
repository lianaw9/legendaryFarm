/* Generates animal with desired occupatation */
public class Pet {
    private String[] speciesList = {
        "Dog", "Cat", "Parrot", "Hamster", "Snake", "Lizard"
    };

    //Private instance variables - general info (will not be changed)
    private String species;
    private int variation; //different colors/appearances per species
    private Occupation occupation;
    private String img;

    //Private instance variable - health info (can be changed)
    private String name;
    private int level; //upgrade over time -> demo will upgrade manually
    private int hunger; 

    public Pet(String n) {
        name = n;
        species = speciesList[(int)(Math.random()*speciesList.length)];
        variation = (int)(Math.random()*2) +1;
        occupation = new Occupation();
        img = "img/pet/" + species + variation + ".jpg";

        level = 0;
        hunger = 100;
    }

    public String getSpecies() {return species;}
    public int getVariation() {return variation;}
    public String getOccuptation() {return occupation.getJobTitle();}
    public String getImage() {return img;}

    @Override 
    public String toString() {
        String s = "A " + species + "(Type " + variation + ") named " + name + " who wants to be a " + getOccuptation() + "\n";
        s+= "Level: " + level + "\n";
        s+= "Hunger: " + hunger + "\n";

        return s;

    }

}