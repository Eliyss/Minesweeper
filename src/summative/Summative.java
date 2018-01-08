//Me learning Java Swing
//Pls Ignore
//Go to Main
package summative;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;



public class Summative {
    
    private JFrame f;
    private JPanel p;

    private JButton d;
    private JLabel l;
    
    public Summative() {
        
        initComponents();
                
    }
    
    
    public void initComponents() {
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        
        
        JPanel p = new JPanel();
        JPanel c = new JPanel();
        //p.setSize(200, 400);
        //c.setSize(200, 400);
        
        p.setBounds(0, 300, 200, 200);
        c.setBounds(500, 500, 200, 400);
        
        
        
        
        JButton v = new JButton("Defend");
        v.setSize(10, 10);
        
        v.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //b6ActionPerformed(evt);
                v.setText("Hello");
            }
        });
        
        JButton b6 = new JButton("Attack");
        b6.setSize(10, 10);
        
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //b6ActionPerformed(evt);
                b6.setText("Hello");
            }
        });

        p.add(b6);
        c.add(v);
        f.add(c);
        f.add(p);
        
        
                
             
        

        /*b = new javax.swing.JButton();
        d = new javax.swing.JButton();
        
        
        
        f = new JFrame("Main");
        f.setVisible(true);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel();
        p.setBackground(Color.yellow);  
        
        
        b.setText("Attack");
        p.setLayout(null);
        b.setBounds(300, 200, 100, 100);
        b.addActionListener()
        
        b.addActionListener(new ActionListener(){
            
            public void actionPreformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Action");
            }
            
        });
        

        d.setText("jButton2");
        d.setBounds(0, 0, 100, 100);
        
        p.add(b);
        p.add(d);
        
        f.add(p);
        */
    }
    

    
    public static void main(String[] args) {
        
        new Summative();
        
        
    }
    
}
