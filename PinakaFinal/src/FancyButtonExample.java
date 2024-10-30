import javax.swing.*;
import java.awt.*;

public class FancyButtonExample extends JFrame {
    public FancyButtonExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fancy Button Example");
        setSize(600, 600);
        getContentPane().setLayout(null); // Using null layout for demonstration purposes

        JButton btnNewButton_1 = new JButton("Print");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_1.setBounds(200, 200, 100, 40); // Adjust position and size as needed
        btnNewButton_1.setFocusable(false);

        // Setting custom background color
        btnNewButton_1.setBackground(new Color(100, 200, 255)); // You can adjust RGB values for your desired color

        // Adding rounded border
        btnNewButton_1.setBorder(BorderFactory.createCompoundBorder()); // Adjust RGB values for border color

        getContentPane().add(btnNewButton_1);
    }
}