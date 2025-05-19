// annoyingly needed
import javax.swing.*;

public class AnswerBox{

    public AnswerBox(String question, Pet thePet, Display d) { // we take a Pet argument so we can edit it's name when the button is closed
        d.stopTimer();

        JTextField textField = new JTextField(question);
        JFrame holder = new JFrame("Text Holder");
        JButton button = Display.createButton("Submit", 0, 0, 100, 70);

        holder.setLayout(null);
        holder.setSize(300, 200);
        textField.setLocation(0, 100); // Set position
        textField.setSize(150, 60); // Set size
        holder.add(textField);
        holder.add(button);
        holder.setVisible(true);

        button.addActionListener(e -> {
            String text = textField.getText();

            thePet.setName(text); // set the pets name when submit is clicked

            // reload pet display
            d.reloadPetDisplay();

            d.startTimer();

            holder.setVisible(false);
            holder.dispose();
        });
    }

    public AnswerBox(String question, Pet thePet, Display d, Player player) { // feed pet
        d.stopTimer();
        JTextField numberField = new JTextField(question);
        JFrame holder = new JFrame("Text Holder");
        JButton button = Display.createButton("Submit", 0, 0, 100, 70);

        holder.setLayout(null);
        holder.setSize(300, 200);
        numberField.setLocation(0, 100); // Set position
        numberField.setSize(150, 60); // Set size
        holder.add(numberField);
        holder.add(button);
        holder.setVisible(true);

        button.addActionListener(e -> {
            String text = numberField.getText();

            try {
                int number = Integer.parseInt(text);
                if (number <= player.getFood()) {
                    thePet.modifyHunger(number);
                    player.modifyFood(-number);
                } else {
                    System.out.println("You do not have enough food");
                }
            } catch (Exception x) {
                System.out.println("Please enter a number.");
            }
            
            // reload pet display
            d.reloadPetDisplay();

            d.startTimer();

            holder.setVisible(false);
            holder.dispose();
        });
    }

        public AnswerBox(String question, Task theTask, Display d, Player p) { // for tasks (i love copy paste hardcoding <3)
        d.stopTimer();
        JTextField textField = new JTextField(question);
        JTextField descriptionField = new JTextField("Enter description");
        JFrame holder = new JFrame("Text Holder");
        JButton button = Display.createButton("Submit", 0, 0, 100, 70);

        holder.setLayout(null);
        holder.setSize(300, 200);
        textField.setLocation(0, 100); // Set position
        textField.setSize(150, 60); // Set size
        descriptionField.setLocation(150, 100); // Set position
        descriptionField.setSize(150, 60); // Set size
        holder.add(textField);
        holder.add(button);
        holder.add(descriptionField);
        holder.setVisible(true);

        button.addActionListener(e -> {
            System.out.println("button clicked");
            String text = textField.getText();
            String description = descriptionField.getText();

            //do something to create the task here
            Task newTask = new Task(text, description, (int)(Math.random() * 50) + 1, (int)(Math.random() * 200) + 1);
            p.AddTask(newTask);

            d.startTimer();
            holder.setVisible(false);
            holder.dispose();
        });
        

    }
}
