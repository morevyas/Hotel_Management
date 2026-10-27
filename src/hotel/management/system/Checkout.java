package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {

    Choice ccustomerid;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;

    Checkout() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel text = new JLabel("Check Out");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);

        ccustomerid = new Choice();
        ccustomerid.setBounds(150, 80, 150, 25);
        add(ccustomerid);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 100, 30);
        add(lblroomnumber);

        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);

        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 200, 30);
        add(lblcheckintime);

        JLabel lblcheckout = new JLabel("CheckOut Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 200, 30);
        add(lblcheckouttime);

        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.white);
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);

        // Populate customer ID choice
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                ccustomerid.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add listener to update room number and checkin time based on selected ID
        ccustomerid.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    conn c = new conn();
                    String query = "SELECT * FROM customer WHERE number = '" + ccustomerid.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        lblroomnumber.setText(rs.getString("room"));
                        lblcheckintime.setText(rs.getString("checkinTime"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Trigger the listener manually to load data for the first customer
        if (ccustomerid.getItemCount() > 0) {
            ccustomerid.select(0);
            ccustomerid.getItemListeners()[0].itemStateChanged(null);
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/out.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 50, 400, 250);
        add(image);

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkout) {
            String customerId = ccustomerid.getSelectedItem();
            String roomNumber = lblroomnumber.getText();

            String query1 = "DELETE FROM customer WHERE number = '" + customerId + "'";
            String query2 = "UPDATE room SET availability = 'Available' WHERE roomnumber = '" + roomNumber + "'";

            try {
                conn c = new conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Checkout done");
                setVisible(false);
                new Reception();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
