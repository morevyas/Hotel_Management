package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener {

    Choice customer;
    JTextField tfroom, tfavailable, tfstatus;
    JButton check, update, back;

    UpdateRoom() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setForeground(Color.BLUE);
        text.setBounds(30, 20, 300, 30);
        add(text);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        customer = new Choice();
        customer.setBounds(200, 80, 150, 25);
        add(customer);

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                customer.add(rs.getString("number")); // Assuming 'number' is the customer ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        tfroom.setEditable(false);
        add(tfroom);

        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(30, 180, 100, 20);
        add(lblavailable);

        tfavailable = new JTextField();
        tfavailable.setBounds(200, 180, 150, 25);
        add(tfavailable);

        JLabel lblcheckin = new JLabel("Cleaning Status");
        lblcheckin.setBounds(30, 230, 100, 20);
        add(lblcheckin);

        tfstatus = new JTextField();
        tfstatus.setBounds(200, 230, 150, 25);
        add(tfstatus);

        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/status.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 40, 550, 300);
        add(image);

        setBounds(300, 200, 980, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String id = customer.getSelectedItem();
            String query = "SELECT * FROM customer WHERE number = '" + id + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    tfroom.setText(rs.getString("room"));  // Changed from room_number to room
                }

                ResultSet rs2 = c.s.executeQuery("SELECT * FROM room WHERE roomnumber = '" + tfroom.getText() + "'");
                if (rs2.next()) {
                    tfavailable.setText(rs2.getString("availability"));
                    tfstatus.setText(rs2.getString("cleaning_status"));  // Changed from clean_status
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching data");
            }

        } else if (ae.getSource() == update) {
            String room = tfroom.getText();
            String available = tfavailable.getText();
            String status = tfstatus.getText();

            try {
                conn c = new conn();
                String updateQuery = "UPDATE room SET availability = '" + available + "', cleaning_status = '" + status + "' WHERE roomnumber = '" + room + "'";
                c.s.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(null, "Room status updated successfully");
                setVisible(false);
                new Reception();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to update room status");
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
