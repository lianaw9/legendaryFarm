public class Main {
    public static void main(String[] args) {
        Pet pet1 = new Pet();
        System.out.println(pet1.getName());
        pet1.setName("Prometheus");
        System.out.println(pet1);

        Pet pet2 = new Pet();
        pet2.setName("SUPER FENT WARRIOR");
        System.out.println(pet2);
    }
}
