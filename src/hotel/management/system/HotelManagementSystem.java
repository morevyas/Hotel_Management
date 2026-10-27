package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;

    public HotelManagementSystem() {

        // Set the frame size, layout and location
        setSize(1366, 430);
        setLayout(null);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the program exits when the window is closed

        // Initialize JLabel and JButton
        l1 = new JLabel();
        b1 = new JButton("Sign In");

        // Set button properties
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setBounds(1170, 325, 150, 50);
        b1.addActionListener(this);  // Attach the action listener to the "Next" button

        // Set background image for the JLabel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/homepage.jpg"));
        Image i3 = i1.getImage().getScaledInstance(1366, 430, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);
        
        // Create and set properties for the hotel name label
        JLabel lid = new JLabel("HOTEL GRAND VIEW");
        lid.setBounds(30, 300, 1500, 100);
        lid.setFont(new Font("serif", Font.PLAIN, 50));
        lid.setForeground(Color.white);
        l1.add(lid);  // Add the hotel name label to the main label
        
        // Add the "Next" button to the main label
        l1.add(b1);

        // Set the bounds for the main label and add it to the frame
        l1.setBounds(0, 0, 1366, 390);
        add(l1);

        // Make the window visible
        setVisible(true);

        // Create a timer to handle the blinking effect for the hotel name
        Timer timer = new Timer(500, new ActionListener() {
            private boolean isVisible = true;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lid.setVisible(isVisible);  // Toggle visibility
                isVisible = !isVisible;  // Change the visibility flag
            }
        });
        timer.start();  // Start the blinking effect
    }

    // ActionListener method for the "Next" button
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);  // Hide the current frame
        new Login();  // Open the login screen
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new HotelManagementSystem();  // Create and display the main window
    }
}
