/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author Imad
 */
public class DrawPanel extends JPanel implements MouseMotionListener , MouseListener
{
    ArrayList<Point> allp = new ArrayList<>();
    public BufferedImage pic  ;
    
    public DrawPanel ()
    {
        
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (pic == null)
        {
            for (int i = 0 ; i < allp.size() ; i++ )
            {
                g.fillOval(allp.get(i).getX()-10, allp.get(i).getY()-10, 50 , 50 );
            }
        }else
        {
            g.drawImage(pic, (700-pic.getWidth())/2, (700-pic.getHeight())/2, null);
        }
        
    };
    
    @Override
    public void mouseDragged(MouseEvent e) {
        addPoint(e.getX(), e.getY());
        repaint();
    }

    @Override
    
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        addPoint(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private void addPoint(int x , int y)
    {
        Point p = new Point(x,y);
        if (!allp.contains(p))
        {
            allp.add(p);
        }
    }
    
    public void repaint_all()
    {
        removeAll();
	updateUI();
        allp = new ArrayList<>();
        this.repaint();
    }
    
    public void WriteImage()
    {
        
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        print(g);
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File("./test.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage()
    {
        
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        print(g);
        g.dispose();
        return bi ;
    }

    public void repaint_ (BufferedImage image)
    {
        allp = new ArrayList<>();
        pic = image ;
        this.repaint();
        
    }
    
}
