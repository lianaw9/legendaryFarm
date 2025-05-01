/* Generates animal with desired occupatation */
public class Pet {
    //Private instance variables - general info (will not be changed)
    private String species;
    private int variation; //different colors/appearances per species
    private Occupation occupation;

    //Private instance variable - health info (can be changed)
    private String name;
    private int level; //upgrade over time -> demo will upgrade manually
    private int hunger; 

    public Pet(String n, String sp, String oc) {
        name = n;
        species = sp;
        variation = (int)(Math.random()*4);
        occupation = new Occupation(oc);

        level = 0;
        hunger = 100;
    }

    public String getSpecies() {return species;}
    public int getVariation() {return variation;}
    public String getOccuptation() {return occupation.getJobTitle();}

    @Override 
    public String toString() {
        String s = "A " + species + "(Type " + variation + ") named " + name + " who wants to be a " + getOccuptation() + "\n";
        s+= "Level: " + level + "\n";
        s+= "Hunger: " + hunger + "\n";

        return s;

    }

}