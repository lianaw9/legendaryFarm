public class Occupation {
    private String[][] jobList = {
        {"President", "Cabinet Member"},
        {"Doctor", "Nurse"},
        {"Teacher", "Student Assistant"},
        {"Pilot", "Naval Student"},
        {"Demolitions Expert", "Casual Demolisher"},
        {"Historian", "Bookworm"},
        {"Feudal Lord", "Peasant"},
        {"Sergeant", "Soldier"},
        {"Master Chef", "Cook"}
    };
    private String[] job; 
    private String jobTitle;

    public Occupation() {
        job = jobList[(int)(Math.random()*jobList.length)];
        jobTitle = "Orphan";
    }
    public String getJobTitle() {return jobTitle;}
    public String getDreamJob() {return job[0];}

    //returns true if graduated to top level, returns false otherwise
    public boolean promotion() {
        if (jobTitle.equals("Orphan")) {
            jobTitle = job[1];
            return false;
        } else if (jobTitle.equals(job[1])) {
            jobTitle = job[0];
            return false;
        } 
        return true;
    }
}
