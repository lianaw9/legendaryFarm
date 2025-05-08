public class Occupation {
    private String[][] jobList = {
        {"President", "Cabinet Member"},
        {"Doctor", "Nurse"},
        {"Teacher", "Student Assistant"},
        {"Pilot", "Naval Student"},
        {"Demolitions Expert", "Casual Demolisher"},
        {"Historian", "Bookworm"},
        {"Feudal Lord", "Peasant"},
        {"Sargeant", "Soldier"},
        {"Master Chef", "Cook"}
    };
    private String[] job; 
    private String jobTitle;

    public Occupation() {
        job = jobList[(int)(Math.random()*jobList.length)];
        jobTitle = job[0];
    }
    public String getJobTitle() {return jobTitle;}
}
