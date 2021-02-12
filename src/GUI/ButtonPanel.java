/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.Matrix;
import Math.Vector;
import com.sun.java.accessibility.util.AWTEventMonitor;
import Options.TraitementImage.TraitementImage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Imad
 */
public class ButtonPanel extends JPanel  {
    
    private JButton b1 , b2  ;
    
    public ButtonPanel()
    {
        b1 = new JButton("Clear");
        b2 = new JButton("OK");
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Frame.p.pic = null ;
                Frame.p.repaint_all();
                Frame.ap.Repaint(null);
                Frame.res.repaint_Panel(new double[10]);
                b2.setEnabled(true);
            }
        });
        
        
            b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    BufferedImage image = Frame.p.getImage();
                    BufferedImage imageCentred = TraitementImage.CenterTheImage(image);
                    Frame.p.repaint_(imageCentred);
                    double result[] = TraitementImage.Scaled_28(Frame.p.getImage());
                    BufferedImage imageScraped;
                    imageScraped = TraitementImage.pixels_to_image_reversed(
                            TraitementImage.genratePicFromVec(result)        
                            , 28, 28); 
                    Frame.ap.Repaint(imageScraped);
                    double[] mm = Frame.net.feedforward(new Matrix(new Vector(result))).getCols()[0].getArray();
                    Frame.res.repaint_Panel(mm);
                    b2.setEnabled(false);
                } catch (Exception ee) {
                    showMessageDialog(null, "Enter En Moin Une Valeur", "NO INPUTS", ERROR_MESSAGE);
                }
            }
        });
        
        
        setLayout(null);
        
        b1.setBounds(10,10,240,55);
        b2.setBounds(10,70,240,55);
        
        add(b1);
        add(b2);
    }

    
}
