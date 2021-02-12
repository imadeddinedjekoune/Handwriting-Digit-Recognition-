/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Imad
 */
public class Resulat extends JPanel {
    private JLabel res[] ;

    
    private void init(double r[])
    {
        res  = new JLabel[10];
        for ( int i = 0; i < 10 ; i++ )
        {
            res[i] = new JLabel(i+" : "+String.format("%.5f", r[i]));
            res[i].setFont(new Font("Verdana", Font.PLAIN, 18));
            res[i].setPreferredSize(new Dimension(120, 50));
            res[i].setForeground(Color.BLACK);
            res[i].setBackground(Color.BLACK);
            res[i].setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), ""+i, TitledBorder.LEFT, TitledBorder.TOP));
        }
    }
    
    private void add()
    {
        for (int i = 0 ; i < 10 ; i++ )
        {
            add(res[i]);
        }
        
    }
    
    public Resulat(double r[])
    {
        init(r);
        add();
    }
    
    private int getTheMax (double r[])
    {
        int maxI = -1 ;
        double max = 0 ;
        for (int i = 0 ; i < r.length ; i++ )
        {
            if (max < r[i])
            {
                max = r[i] ;
                maxI = i ;
            }
        }
        return maxI ;
    }
    
    public  void repaint_Panel (double r[])
    {
        removeAll();
        updateUI();
        init(r);
        try {
            res[getTheMax(r)].setForeground(Color.red);
            res[getTheMax(r)].setBackground(Color.red);
        } catch (Exception e) {
        }
        
        add();
    }
    
}
