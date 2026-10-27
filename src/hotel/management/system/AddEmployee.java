
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener{
    
    JTextField tfname,tfphone,tfage,tfsalary,tfemail,tfaadhar;
    JRadioButton rbmale,rbfemale;
    JButton submit;
    JComboBox cbjob;
    
    AddEmployee(){
        setLayout(null);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60,30,120,30);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,30,150,30);
        add(tfname);
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60,80,120,30);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,80,150,30);
        add(tfage);
        
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(60,130,120,30);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblgender);
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200,130,70,30);
        rbmale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
         
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280,130,70,30);
        rbfemale.setFont(new Font("Tahoma",Font.PLAIN,14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(60,180,120,30);
        lbljob.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lbljob);
        
        String str[] = {"Front Clerks","Porters","HouseKeeping","Kitchen Staff","Room service","Chefs","Waiter/Waitress","Manager","Accountant"}; 
        cbjob = new JComboBox(str);
        cbjob.setBounds(200,180,150,30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
        
        JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60,230,120,30);
        lblsalary.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblsalary);
        
        tfsalary = new JTextField();
        tfsalary.setBounds(200,230,150,30);
        add(tfsalary);
        
        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60,280,120,30);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200,280,150,30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60,330,120,30);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200,330,150,30);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("AADHAR");
        lblaadhar.setBounds(60,380,120,30);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(200,380,150,30);
        add(tfaadhar);
        
        submit = new JButton("SUBMIT");
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200,430,150,30);
        submit.addActionListener(this);
        add(submit);
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/fifth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380,60,450,370);
        add(image);
        
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(430,200,850,540);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
    String name = tfname.getText();
    String age = tfage.getText();
    String salary = tfsalary.getText();
    String phone = tfphone.getText();
    String email = tfemail.getText();
    String aadhar = tfaadhar.getText();
    
    String gender = null;
    
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
    
    // Validate Gender
    if (!rbmale.isSelected() && !rbfemale.isSelected()) {
        JOptionPane.showMessageDialog(null, "Please select a gender");
        return;
    }
    
    if (rbmale.isSelected()) {
        gender = "Male";
    } else if (rbfemale.isSelected()) {
        gender = "Female";
    }

    // Validate Job
    String job = (String) cbjob.getSelectedItem();
    if (job == null || job.equals("")) {
        JOptionPane.showMessageDialog(null, "Please select a job");
        return;
    }

    // Validate Salary
    if (salary.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Salary should not be empty");
        return;
    }
    try {
        double salaryDouble = Double.parseDouble(salary);
        if (salaryDouble <= 0) {
            JOptionPane.showMessageDialog(null, "Salary should be a positive number");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Salary should be a valid number");
        return;
    }

    // Validate Phone
    if (phone.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Phone number should not be empty");
        return;
    }
    if (!phone.matches("[0-9]{10}")) {
        JOptionPane.showMessageDialog(null, "Phone number should be a 10-digit number");
        return;
    }

    // Validate Email
    if (email.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Email should not be empty");
        return;
    }
    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email address");
        return;
    }

    // Validate Aadhar
    if (aadhar.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Aadhar number should not be empty");
        return;
    }
    if (!aadhar.matches("[0-9]{12}")) {
        JOptionPane.showMessageDialog(null, "Aadhar number should be a 12-digit number");
        return;
    }

    // Insert Data into Database
    try {
        conn conn = new conn();
        String query = "insert into employee (name, age, gender, job, salary, phone, email, aadhar) values('"
                + name + "','" + age + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + email + "','" + aadhar + "')";
        
        conn.s.executeUpdate(query);
        
        JOptionPane.showMessageDialog(null, "Employee added successfully");
        
        setVisible(false);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 public static void main(String[] args){
        new AddEmployee();
    }
} 