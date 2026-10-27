
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;



public class Department  extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    Department(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
               
       JLabel l1 = new JLabel("Department");
       l1.setBounds(180,10,100,30);
       add(l1);
       
       JLabel l2 = new JLabel("Budget");
       l2.setBounds(420,10,100,30);
       add(l2);
       
       table = new JTable();
       table.setBounds(10,50,700,350);
       add(table);
   
       
       try{
           conn c = new conn();
           ResultSet rs = c.s.executeQuery("select*from department");
           table.setModel(DbUtils.resultSetToTableModel(rs));
       }catch (Exception e){
           e.printStackTrace();
       }
       
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200,500,120,30);
        back.addActionListener(this);
        add(back);
       
       
        
       
       setBounds(450,200,800,600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    
    public static void main(String[] args){
        new Department();
    }
}

