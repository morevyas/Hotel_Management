
package hotel.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDriver  extends JFrame implements ActionListener{
    
    JButton add,cancel;
    JTextField tfname,tfage,tfcompany,tfmodel,tflocation;
    JComboBox typecombo, gendercombo,availablecombo;
    AddDriver(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD DRIVER");
        heading.setBounds(150,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60,70,120,30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,70,150,30);
        add(tfname);
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60,110,120,30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,110,150,30);
        add(tfage);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60,150,120,30);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblgender);
        
        String genderOptions[] = {"Male","Female"};
        gendercombo = new JComboBox(genderOptions);
        gendercombo.setBounds(200,150,150,30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);
        
        JLabel lbltype = new JLabel("Car Model");
        lbltype.setBounds(60,230,150,30);
        lbltype.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lbltype);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200,230,150,30);
        add(tfmodel);
       
        JLabel lblcompany = new JLabel("Car Company");
        lblcompany.setBounds(60,190,120,30);
        lblcompany.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblcompany);
        
        tfcompany = new JTextField();
        tfcompany.setBounds(200,190,150,30);
        add(tfcompany);
        
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setBounds(60,270,120,30);
        lblavailable.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblavailable);
        
        String driverOptions[] = {"Available","Busy"};
        availablecombo = new JComboBox(driverOptions);
        availablecombo.setBounds(200,270,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lbllocation = new JLabel("Location");
        lbllocation.setBounds(60,310,120,30);
        lbllocation.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lbllocation);
        
        tflocation = new JTextField();
        tflocation.setBounds(200,310,150,30);
        add(tflocation);
        
        
        add = new JButton("Add Driver");
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.setBounds(60,370,130,30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,370,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,30,500,300);
        add(image);
        
        setBounds(370,200,970,470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource ()== add ){
            String name = tfname.getText();
            String age = tfage.getText();
            String gender=(String)gendercombo.getSelectedItem(); 
            String company = tfcompany.getText();
            String brand = tfmodel.getText();
            String available =(String)availablecombo.getSelectedItem();
            String location= (String)tflocation.getText();
            
            // Validate Name
    if (name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Name should not be empty");
        return;
    }

    // Validate Age
    if (age.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Age should not be empty");
        return;
    }
    try {
        int ageInt = Integer.parseInt(age);
        if (ageInt < 18 || ageInt > 100) {
            JOptionPane.showMessageDialog(null, "Please enter a valid age (between 18 and 100)");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Age should be a valid number");
        return;
    }
   
    // Validate company
    if (company.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "company should not be empty");
        return;
    }  
    // Validate brand
    if (brand.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "brand should not be empty");
        return;
    
    }  
    // Validate location
    if (location.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "location should not be empty");
        return;
    
    }     
        try{
            conn conn = new conn();
            String str = "insert into driver (name,age,gender,company,brand,available,location)values('"
             + name + "','" + age + "','" +gender + "','" +company  + "','" +brand + "','" +available + "','" +location+ "')";
            
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Driver added successfully");
            
        }  catch(Exception e){
            e.printStackTrace();
        } 
    }else{
            setVisible(false);
        }
        
    }
    
    public static void main(String args[]){
        new AddDriver();
    }
    
} 



