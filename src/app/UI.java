package app;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.*;
// import java.util.*;
// import java.util.Scanner;

import javax.swing.JFrame;


public class UI {
        static String itemName;
        static String price_str = "0";
        static String stock_str = "0";
    
        static void Display() {
            JFrame f1 = new JFrame();
            JPanel panel1 = new JPanel(null);
            JLabel label1 = new JLabel("Adding items to inventory!");
            JLabel label2 = new JLabel("Enter name of the item: ");
            JLabel label3 = new JLabel("Enter price of the item: ");
            JLabel label4 = new JLabel("Enter Stock Quantity: ");
            JTextField text1 = new JTextField();
            JTextField text2 = new JTextField();
            JTextField text3 = new JTextField();
            // JButton btn1=new JButton("SUBMIT");
            // JButton btn2=new JButton("SUBMIT");
            JButton btn3 = new JButton("SUBMIT");
    
            label1.setBounds(200, 10, 300, 50); // x, y, width, height
    
            label2.setBounds(200, 70, 300, 50); // x, y, width, height
            text1.setBounds(200, 125, 300, 50);
            // btn1.setBounds(510, 125, 100, 50);
    
            label3.setBounds(200, 180, 300, 50);
            text2.setBounds(200, 235, 300, 50);
            // btn2.setBounds(510, 235, 100, 50);
    
            label4.setBounds(200, 290, 300, 50);
            text3.setBounds(200, 345, 300, 50);
            btn3.setBounds(510, 345, 100, 50);
    
            /*
             * btn1.addActionListener(new ActionListener(){ public void
             * actionPerformed(ActionEvent e){ //itemName = (text1.getText());
             * text1.setText(""); //System.out.println(itemName); } });
             */
    
            /*
             * btn2.addActionListener(new ActionListener(){ public void
             * actionPerformed(ActionEvent e){ //price_str = (text2.getText());
             * text2.setText(""); //System.out.println(price); } });
             */
    
            btn3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    itemName = (text1.getText());
                    price_str = (text2.getText());
                    stock_str = (text3.getText());
                    text3.setText("");
                    // System.out.println(stock);
                }
            });
    
            double price = Double.parseDouble(price_str);
            int stock = Integer.parseInt(stock_str);
            System.out.println(price + " is the price.\n" + stock + " is the stock.");
    
            panel1.add(label1);
            panel1.add(label2);
            panel1.add(label3);
            panel1.add(label4);
            panel1.add(text1);
            // panel1.add(btn1);
            panel1.add(text2);
            // panel1.add(btn2);
            panel1.add(text3);
            panel1.add(btn3);
    
            f1.add(panel1);
            f1.setPreferredSize(new Dimension(900, 600));
            f1.pack();
            f1.setVisible(true);
        }
    }

