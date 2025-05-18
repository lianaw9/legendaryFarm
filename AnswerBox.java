// annoyingly needed
import java.text.NumberFormat;

import javax.swing.*;

public class AnswerBox {

    public AnswerBox(String question, Pet thePet, Display d) { // we take a Pet argument so we can edit it's name when the button is closed
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
            System.out.println("button clicked");
            String text = textField.getText();

            thePet.setName(text); // set the pets name when submit is clicked

            // reload pet display
            d.reloadPetDisplay();

            holder.setVisible(false);
            holder.dispose();
        });
    }

    public AnswerBox(String question, Pet thePet, Display d, int total) { // feed pet
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

            thePet.modifyHunger(Integer.parseInt(text));

            // reload pet display
            d.reloadPetDisplay();

            holder.setVisible(false);
            holder.dispose();
        });
    }

        public AnswerBox(String question, Task theTask) { // for tasks (i love copy paste hardcoding <3)
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
            System.out.println("button clicked");
            String text = textField.getText();

            //do something to create the task here
            holder.setVisible(false);
            holder.dispose();
        });
        

    }
}
