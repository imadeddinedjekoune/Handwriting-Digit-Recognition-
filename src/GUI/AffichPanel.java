/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class AffichPanel extends JPanel {
    private BufferedImage IAim  ;
    
    public AffichPanel(BufferedImage im)
    {
        setBackground(Color.white);
        IAim = im ;
    }
     protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (IAim != null)
        {
            g.drawImage(IAim,0,0,250,250,null);
        }else
        {
            removeAll();
            updateUI();
        }
    };
     
    public void Repaint (BufferedImage i)
    {
        IAim = i;
        this.repaint();
    }
}
