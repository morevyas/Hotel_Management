
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRooms  extends JFrame implements ActionListener{
    
    JButton add,cancel;
    JTextField tfroom,tfprice;
    JComboBox typecombo,availablecombo, cleancombo;
    AddRooms(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD ROOMS");
        heading.setBounds(150,20,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);
        
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setBounds(60,80,120,30);
        lblroomno.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblroomno);
        
        tfroom = new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(60,130,120,30);
        lblavailable.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblavailable);
        
        String availableOptions[] = {"Available","Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200,130,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setBounds(60,180,120,30);
        lblclean.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblclean);
        
        String cleanOptions[] = {"Cleaned","Dirty"};
        cleancombo = new JComboBox(cleanOptions);
        cleancombo.setBounds(200,180,150,30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);
        
        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setBounds(60,230,120,30);
        lbltype.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lbltype);
        
        String typeOptions[] = {"Single Bed","Double Bed"};
        typecombo = new JComboBox(typeOptions);
        typecombo.setBounds(200,230,150,30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);
        
        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(60,280,120,30);
        lblprice.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200,280,150,30);
        add(tfprice);
        
        add = new JButton("Add Room");
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.setBounds(60,350,130,30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,350,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,50,500,300);
        add(image);
        
        setBounds(380,200,940,470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource ()== add ){
            String roomnumber = tfroom.getText();
            String availability =(String)availablecombo.getSelectedItem();
            String cleaning_status = (String)cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String bed_type = (String)typecombo.getSelectedItem();
            
            // Validate Name
    if (roomnumber.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "RoomNumber should not be empty");
        return;
    }
            
        try{
            conn conn = new conn();
            String str = "insert into room (roomnumber, availability,cleaning_status, price, bed_type) values('"
             + roomnumber + "','" + availability + "','" +cleaning_status + "','" + price + "','" +bed_type + "')";
            
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Room added successfully");
            
        }  catch(Exception e){
            e.printStackTrace();
        } 
        }else{
            setVisible(false);
        }
        
    }
    
    public static void main(String args[]){
        new AddRooms();
    }
    
}
