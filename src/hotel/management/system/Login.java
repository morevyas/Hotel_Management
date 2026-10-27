package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JTextField username, password;
    JButton login,cancel;
    
    Login() {
        // Set the background color of the JFrame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Username label
        JLabel user = new JLabel("Username");
        user.setBounds(40, 50, 100, 30);  // Adjusted Y-position for username
        add(user);
        
        // Password label
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 100, 100, 30);  // Adjusted Y-position for password
        add(pass);
        
        // Username text field
        username = new JTextField();
        username.setBounds(150, 50, 150, 30);  // Adjusted X and Y-position for username field
        add(username);
        
        // Password text field
        password = new JPasswordField();
        password.setBounds(150, 100, 150, 30);  // Adjusted X and Y-position for password field
        add(password);
        
        // Login button
        login = new JButton("Login");
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        // Adding an image (ensure you have a valid image in the path specified)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg")); // Replace with your actual image path
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);
        
//        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/icons/second2.jpg")); // Correct path to image
//        Image img = backgroundImage.getImage();
//        Image scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); // Scale the image
//        backgroundImage = new ImageIcon(scaledImage);
        
        // Set the window size and position
        setBounds(500, 200, 600, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
           String user = username.getText();
           String pass = password.getText();
           
        try{
            conn c = new conn();
            String query = "select * from login where username='" + user +"'and password ='"+ pass + "'";
            ResultSet rs = c.s.executeQuery(query);
            if(rs.next()){
                setVisible(false);
                new Dashboard();      
          }else{
                JOptionPane.showMessageDialog(null,"Invalid username or password");
                setVisible(false);
            }
        } catch(Exception e){
            e.printStackTrace();
        }   
        }else if(ae.getSource()==cancel){
            setVisible(false);
            
        }
    }
    
    public static void main(String[] agrs) {
        new Login();
    }
}
