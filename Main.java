public class Main {
    public static void main(String[] args) {
        Pet pet1 = new Pet();
        System.out.println(pet1.getName());
        pet1.setName("Prometheus");
        System.out.println(pet1);

        Pet pet2 = new Pet();
        pet2.setName("SUPER FENT WARRIOR");
        System.out.println(pet2);

        for (int i=1; i<=10; i++) {
            pet2.levelUp();
            pet2.modifyHunger(-10);
            System.out.println("LOOP #" + i);
            System.out.println(pet2);
        }
    }
}
