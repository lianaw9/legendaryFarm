
public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.setName("John");

        Pet pet1 = new Pet(player.getName());
        System.out.println(pet1.getName());
        pet1.setName("Prometheus");
        System.out.println(pet1);

        Pet pet2 = new Pet(player.getName());
        pet2.setName("SUPER FENT WARRIOR");
        System.out.println(pet2);

        Pet pet3 = new Pet(player.getName());
        pet3.setName("Painter");
        System.out.println(pet3);

        Display.loadPetDisplay(pet3);

        Display.initMainDisplay();

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
