/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import IO.NetworkIO;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class OuterPanel extends JPanel 
{
    JLabel l1 = new JLabel("Controle");
    JLabel l2 = new JLabel("Vue Du Neural Network");
    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("Learning");
    JMenu m2 = new JMenu("About");
    JMenuItem i1 = new JMenuItem("About");
    JMenuItem i3 = new JMenuItem("Niveau");
    JMenuItem i4 = new JMenuItem("About Me");
    
    
    public OuterPanel()
    {
        
        setLayout(null);
        l1.setBounds(900,45,100,40);
        l2.setBounds(860,295,160,40);
        mb.setBounds(1000,0,110,30);
        
        mb.add(m1);
        mb.add(m2);
        
        m1.add(i1);
        
        m2.add(i3);
        m2.add(i4);
        
       
        
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "La Source Du Data c'est : MNIST\nLien : http://yann.lecun.com/exdb/mnist/", "Data", 1);
            }
        });
        
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Univ : USTHB , Bab Ezzour \nLicenece : 2éme Année\nSpe : Licence Ingénierie des Systèmes d’Information et des logiciels"
                        + "\nAnnée Scolaire : 2019-2020", "Université", 1);
            }
        });
        
        i4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Nom : Djekoune\n"
                        + "Prénom : Imad Eddine\n"
                        + "Email : imaddjekoune@gmail.com", "Informations", 1);
            }
        });
        
        add(l1);
        add(l2);
        add(mb);
        
    }
    
    
}
