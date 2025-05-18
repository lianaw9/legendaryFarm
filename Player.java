import java.util.ArrayList;
import java.util.Scanner;

class Player {
    private ArrayList<Pet> pets;
    private ArrayList<Task> tasks;

    private String name;
    private int totalCoins;
    private int max = 6; //maximum number of pets

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
    public String getName() {return name;}
    public void setName(String newName) {name = newName;}
    public int getCoins() {return totalCoins;}
    public void modifyCoins(int m) {totalCoins += m;}
    public ArrayList<Pet> getPets() {return pets;}

    public void AddTask(Task newTask) {
        tasks.add(newTask);
    }

    public void SetCompletedTasks(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                tasks.get(i).SetComplete(true); // remove if not important
                tasks.remove(i);
            }
        }
    }

    public Task PickTask() {
        int randomTaskIndex = (int) (Math.random() * tasks.size());
        Task selectedTask = tasks.get(randomTaskIndex);

        return selectedTask;
    }

    public void AddPet(Pet newPet) {
        if (pets.size() < max) {
            pets.add(newPet);
        }
    }

    public void IncrementTimeScale() {
        timeScale++;
    }

    public int GetTimeScale() {
        return timeScale;
    }

    public void UpdatePetHunger(int foodDelta) {
        if (GetTimeScale() % updateHungerScale == 0) {
            for (int i = 0; i < pets.size(); i++) {
                pets.get(i).modifyHunger(foodDelta);
                System.out.println(pets.get(i));
            }
        }
    }

    public void UpdateSpecificPetHunger(String chosenPet, int foodDelta) {
        boolean petExists = false;
        for(int i = 0; i < pets.size(); i++) {
            if(pets.get(i).getName().equals(chosenPet)) {
                pets.get(i).modifyHunger(foodDelta);
                System.out.println(pets.get(i));
                petExists = true;
            }
        }

        if(!petExists) {
            System.out.println("That pet doesn't exist");
        }
    }

    public void UpdateTask() {
        if (GetTimeScale() % (updateHungerScale + (int) (Math.random() * 3)) == 0 && (int)(Math.random() * 2) == 1) {
            Task sTask = PickTask();

            System.out.println("Remember, " + sTask.GetName() + "! ");
            System.out.println("If you forgot," + sTask.GetName() + " is about: " + sTask.GetDescription());
            System.out.println("Did you complete the task(y/n)?");

            boolean isFinished = false;

            Scanner scan = new Scanner(System.in);

            String unfilteredText = scan.nextLine();

            if (unfilteredText.equals("y")) {
                isFinished = true;
            }

            if (isFinished) {
                System.out.println("Congrats, you earned " + sTask.GetCoinAmount() + "! you now have " + totalCoins + " coins.");
                this.totalCoins += sTask.GetCoinAmount();
                SetCompletedTasks(sTask);

                System.out.println("What pet do you want to feed? ");
                String petName = scan.nextLine();

                UpdateSpecificPetHunger(petName, sTask.GetFoodAmount());

            }

            System.out.println("Do you want to: (1) buy a pet, (2) create a new task, or (3) quit?");
            int choice = scan.nextInt();

            if (choice == 1 && totalCoins > 100) {
                if (pets.size() < max) {
                    totalCoins -= 100;
                    Pet newPet = new Pet(name);
                    System.out.println("What do you want to name your pet? ");
                    String petName = scan.nextLine();

                    newPet.setName(petName);
                    this.AddPet(newPet);
                    System.out.println("Congrats, you now have " + petName + " joining the team!");
                    System.out.println(newPet);
                } else {
                    System.out.println("Not enough space for a new pet");
                }
                
            } else if (choice == 2) {
                System.out.println("What is the new Task you want to add? ");
                String taskName = scan.nextLine();

                System.out.println("What is a brief description of " + taskName + "? ");
                String taskDescription = scan.nextLine();

                System.out.println("How much will it feed your pets? ");
                int taskFoodDelta = scan.nextInt();

                System.out.println("How much will you earn upon completion? ");
                int taskMoneyEarned = scan.nextInt();

                Task newTask = new Task(taskName, taskDescription, taskFoodDelta, taskMoneyEarned);
                this.AddTask(newTask);

            }
        }
    }
}