import java.util.ArrayList;

class Player {
    private ArrayList<Pet> pets;
    private ArrayList<Task> tasks;

    private String name;
    private int totalCoins;

    private int timeScale;
    private int updateHungerScale = 5;
    
    
    public Player() {
        pets = new ArrayList<Pet>();
        tasks = new ArrayList<Task>();

        name = "Player 1";
        totalCoins = 0;

        timeScale = 0;

    }

    public Player(String name) {
        pets = new ArrayList<Pet>();
        tasks = new ArrayList<Task>();

        this.name = name;
        totalCoins = 0;

        timeScale = 0;


    }

    public void AddTask(Task newTask) {
        tasks.add(newTask);
    }

    public void AddPet(Pet newPet) {
        pets.add(newPet);
    }

    public void IncrementTimeScale() {
        timeScale++;
    }

    public int GetTimeScale() {
        return timeScale;
    }

    public void updatePetHunger() {
        if (GetTimeScale() % updateHungerScale == 0) {
            for (int i = 0; i < pets.size(); i++) {
                pets.get(i).modifyHunger(-10);
                System.out.println(pets.get(i));
            }
        }
    }
}