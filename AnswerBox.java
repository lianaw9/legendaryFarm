// annoyingly needed
import javax.swing.*;

public class AnswerBox {

    private String finalText;

    public AnswerBox(String question) {
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

            finalText = text;
            holder.setVisible(false);
            holder.dispose();
        });
        
    }

    public String getText() {return finalText;}

}
