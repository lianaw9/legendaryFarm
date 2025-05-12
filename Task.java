public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isComplete;
    private int foodAmount;
    private int coinAmount;

    public Task() {
        taskName = "Test";
        taskDescription = "This is a task Description";
        isComplete = false;
        foodAmount = 10;
        coinAmount = 5;
    }

    public Task(String tN, String tD, int fA, int cA) {
        taskName = tN;
        taskDescription = tD;
        isComplete = false;
        foodAmount = fA;
        coinAmount = cA;
    }

    public String GetName() {
        return taskName;
    }

    public String GetDescription() {
        return taskDescription;
    }

    public int GetFoodAmount() {
        return foodAmount;
    }

    public int GetCoinAmount() {
        return coinAmount;
    }

    public void SetComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean GetComplete() {
        return isComplete;
    }

}
