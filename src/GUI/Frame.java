/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import IO.NetworkIO;
import Options.NeuralNetwork.NeuralNetwork;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Imad
 */
public class Frame extends JFrame {
    public static int width = (Toolkit.getDefaultToolkit().getScreenSize().width) ;
    public static int height = (Toolkit.getDefaultToolkit().getScreenSize().height);
    public static DrawPanel p ;
    public static ButtonPanel b ;
    public static AffichPanel ap ;
    public static Resulat res ;
    static NeuralNetwork net ;
    private OuterPanel op ;
    
    public Frame()
    {
        
        net = NetworkIO.loadNetwork("./data/network.dat");
        
        p = new DrawPanel();
        b = new ButtonPanel();
        ap = new AffichPanel(null);
        res = new Resulat(new double[10]);
        op = new OuterPanel();
        
        setTitle("HandWriting Digits Recongnition");
	setResizable(false);
        setSize(width,height);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
		
        setLayout(null);
        
        op.setBounds(0,0,width,height);
        p.setBounds(30,15,700,700);
        b.setBounds(800,90,260,135);
        b.setBorder(BorderFactory.createEtchedBorder());
        ap.setBounds(800,350,250,250);
        ap.setBorder(BorderFactory.createEtchedBorder());
        res.setBounds(1100,50,200,610);
        res.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Resultat : ", TitledBorder.LEFT, TitledBorder.TOP));
        
        add(p);
        add(b);
        add(ap);
        add(res);
        add(op);
        System.out.println(width+" "+height);
    }
    
    
}
