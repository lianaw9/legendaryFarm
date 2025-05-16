
public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.setName("John");
        player.AddPet(new Pet(player.getName()));
        player.AddPet(new Pet(player.getName()));
        player.AddPet(new Pet(player.getName()));
        player.AddPet(new Pet(player.getName()));
        player.AddPet(new Pet(player.getName()));
        player.AddPet(new Pet(player.getName()));

        Display.initPetDisplay();
        //Display.test();
        System.out.println(player.getPets().size());
        for (Pet p : player.getPets()) {
            Display.loadPetDisplay(p);
        }

        //Display.initMainDisplay();

        // player.AddPet(pet1);
        // player.AddPet(pet2);

        // Task task1 = new Task("I believe I can fly", "Jump off a building", 30, 50);
        // Task task2 = new Task("Task 2", "number 2", 2, 100);
        // Task task3 = new Task("Destablize a country", "Take over France", 1000, 1000000);

        // player.AddTask(task3);
        // player.AddTask(task2);
        // player.AddTask(task1);

        // for (int i=1; i<=100; i++) {
        //     player.IncrementTimeScale();
        //     System.out.println(player.GetTimeScale());
        //     player.UpdatePetHunger(-10);
        //     player.UpdateTask();
        // }
    }
}
