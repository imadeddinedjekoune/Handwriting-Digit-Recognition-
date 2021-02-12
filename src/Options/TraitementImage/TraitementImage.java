package Options.TraitementImage;


import GUI.Frame;
import Math.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Imad
 */
public class TraitementImage {
    
   public static  int[] resizePixels(int[] pixels,int w1,int h1,int w2,int h2) {
        int[] temp = new int[w2*h2] ;
    // EDIT: added +1 to account for an early rounding problem
    int x_ratio = (int)((w1<<16)/w2) +1;
    int y_ratio = (int)((h1<<16)/h2) +1;
    //int x_ratio = (int)((w1<<16)/w2) ;
    //int y_ratio = (int)((h1<<16)/h2) ;
    int x2, y2 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x2 = ((j*x_ratio)>>16) ;
            y2 = ((i*y_ratio)>>16) ;
            temp[(i*w2)+j] = pixels[(y2*w1)+x2] ;
        }                
    }                
    return temp ;
    }
   
    public static void debug_Image (BufferedImage image)
    {
        System.out.println("Begin");
        int i , j ;
        for ( i = 0 ; i < image.getWidth() ; i++ )
        {
            for ( j = 0 ; j < image.getHeight() ; j++ )
            {
                int color = image.getRGB(i, j);

                int blue = color & 0xff;
                int green = (color & 0xff00) >> 8;
                int red = (color & 0xff0000) >> 16;
                
                System.out.println(i+" "+j+" "+red+" "+green+" "+blue);
                
            }
        }
    }
    
    public static int [] getPixels_Reversed (BufferedImage image)
    {
        int i , j , cpt = 0;
        int ret[] = new int[image.getWidth()*image.getHeight()];
        for ( i = 0 ; i < image.getWidth() ; i++ )
        {
            for ( j = 0 ; j < image.getHeight() ; j++ )
            {
                int color = image.getRGB(j, i);
                ret[cpt++] = color ;
            }
        }
        return ret ;
    }
    
    
    public static int [] getPixels (BufferedImage image)
    {
        int i , j , cpt = 0;
        int ret[] = new int[image.getWidth()*image.getHeight()];
        for ( i = 0 ; i < image.getWidth() ; i++ )
        {
            for ( j = 0 ; j < image.getHeight() ; j++ )
            {
                int color = image.getRGB(i, j);
                ret[cpt++] = color ;
            }
        }
        return ret ;
    }
    
    public static void write_image (BufferedImage im,String name)
    {
        try {
            ImageIO.write(im, "png", new File("./"+name+".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static BufferedImage pixels_to_image (int pixels[] , int w , int h)
    {
        int  i , j;
        BufferedImage im = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        for (int cpt = 0 ; cpt < w*h ; cpt++ )
        {
            j = cpt % h ;
            i = cpt / h ;
            im.setRGB(i, j, pixels[cpt]);
        }
        
        return im ;
        
    }
    
    public static BufferedImage pixels_to_image_reversed (int pixels[] , int w , int h)
    {
        int  i , j;
        BufferedImage im = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
        for (int cpt = 0 ; cpt < w*h ; cpt++ )
        {
            j = cpt % h ;
            i = cpt / h ;
            im.setRGB(j, i, pixels[cpt]);
        }
        
        return im ;
        
    }
    
    public static BufferedImage scale(BufferedImage image , float cap)
    {
        int x1 = image.getWidth() , y1 = image.getHeight() ;
        int p1 [] = TraitementImage.getPixels(image);
        int p[] = TraitementImage.resizePixels(p1, x1, y1, (int)(x1*cap), (int)(y1*cap));
        return TraitementImage.pixels_to_image(p,(int)(x1*cap), (int)(y1*cap));
    }
    
    private static int getWhitespaceInImage(BufferedImage image, int i1, int i2, boolean horizontal ) {
        int cut = i1;
        for (int i = i1; i >= 0 && i < image.getWidth(); i += i2) 
        {
            boolean white = true;
            for (int j = 0; j < image.getWidth(); j++) 
            {
                if ((horizontal && new Color(image.getRGB(j, i)).getRed() != 255)
                || (!horizontal && new Color(image.getRGB(i, j)).getRed() != 255)) 
                {
                    white = false;
                    break;
                }
            }

            if (white || i2 < 0) {
                cut = i + 1;
            }
            if (!white) {
                break;
            }
        }
        return cut;
    }
    
    public static BufferedImage CenterTheImage(BufferedImage image)
    {
        int nCut = TraitementImage.getWhitespaceInImage(image, 0, 1, true);
        int wCut = TraitementImage.getWhitespaceInImage(image, 0, 1, false);
        int eCut = TraitementImage.getWhitespaceInImage(image, image.getWidth()- 1, -1, false);
        int sCut = TraitementImage.getWhitespaceInImage(image, image.getWidth() - 1, -1, true);

        
        int width = eCut - wCut;
        int height = sCut - nCut;
        
        BufferedImage cutImage = new BufferedImage(eCut - wCut, sCut - nCut, BufferedImage.TYPE_INT_ARGB_PRE);
        ((Graphics2D) cutImage.getGraphics()).drawImage(image.getSubimage(wCut, nCut, width, height), 0, 0, null);
        return cutImage;
    }
    
    public static void Test (BufferedImage im)
    {
        int width = im.getWidth();
        int height = im.getHeight();
        int maxLen = height ;
        
        if (width > height)
        {
            maxLen = width ;
        }
        
        int offset = (int) (0.14285714285714285714 * maxLen );
        Graphics2D g = (Graphics2D) im.getGraphics();
        if (width == height)
        {
            g.drawImage(im, offset, offset, maxLen - offset, maxLen - offset, 0, 0, width, height, null);
        } else if (width > height)
        {
            double scaledWidth = (maxLen - (2.0 * offset)) / width;
            int space = (int) ((width - height) * scaledWidth / 2.0);
            g.drawImage(im, offset, offset + space, maxLen - offset, maxLen - offset - space, 0, 0, width, height, null);
        } else if (width < height) {
            double scaledHeight = (maxLen - (2.0 * offset)) / height;
            int space = (int) ((height - width) * scaledHeight / 2.0);
            g.drawImage(im, offset + space, offset, maxLen - offset - space, maxLen - offset, 0, 0, width, height, null);
        }
        
        Graphics G = g ;
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        G = bi.createGraphics();
        
        try {
            ImageIO.write(bi, "jpg", new File("foo.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static int[] resizeBilinearGray(int[] pixels, int w, int h, int w2, int h2) {
    int[] temp = new int[w2*h2] ;
    int A, B, C, D, x, y, index, gray ;
    float x_ratio = ((float)(w-1))/w2 ;
    float y_ratio = ((float)(h-1))/h2 ;
    float x_diff, y_diff, ya, yb ;
    int offset = 0 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x = (int)(x_ratio * j) ;
            y = (int)(y_ratio * i) ;
            x_diff = (x_ratio * j) - x ;
            y_diff = (y_ratio * i) - y ;
            index = y*w+x ;

            // range is 0 to 255 thus bitwise AND with 0xff
            A = pixels[index] & 0xff ;
            B = pixels[index+1] & 0xff ;
            C = pixels[index+w] & 0xff ;
            D = pixels[index+w+1] & 0xff ;
            
            // Y = A(1-w)(1-h) + B(w)(1-h) + C(h)(1-w) + Dwh
            gray = (int)(
                    A*(1-x_diff)*(1-y_diff) +  B*(x_diff)*(1-y_diff) +
                    C*(y_diff)*(1-x_diff)   +  D*(x_diff*y_diff)
                    ) ;

            temp[offset++] = gray ;                                   
        }
    }
    return temp ;
}
    
    public static int[] resizeBilinear(int[] pixels, int w, int h, int w2, int h2) {
    int[] temp = new int[w2*h2] ;
    int a, b, c, d, x, y, index ;
    float x_ratio = ((float)(w-1))/w2 ;
    float y_ratio = ((float)(h-1))/h2 ;
    float x_diff, y_diff, blue, red, green ;
    int offset = 0 ;
    for (int i=0;i<h2;i++) {
        for (int j=0;j<w2;j++) {
            x = (int)(x_ratio * j) ;
            y = (int)(y_ratio * i) ;
            x_diff = (x_ratio * j) - x ;
            y_diff = (y_ratio * i) - y ;
            index = (y*w+x) ;                
            a = pixels[index] ;
            b = pixels[index+1] ;
            c = pixels[index+w] ;
            d = pixels[index+w+1] ;

            // blue element
            // Yb = Ab(1-w)(1-h) + Bb(w)(1-h) + Cb(h)(1-w) + Db(wh)
            blue = (a&0xff)*(1-x_diff)*(1-y_diff) + (b&0xff)*(x_diff)*(1-y_diff) +
                   (c&0xff)*(y_diff)*(1-x_diff)   + (d&0xff)*(x_diff*y_diff);

            // green element
            // Yg = Ag(1-w)(1-h) + Bg(w)(1-h) + Cg(h)(1-w) + Dg(wh)
            green = ((a>>8)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>8)&0xff)*(x_diff)*(1-y_diff) +
                    ((c>>8)&0xff)*(y_diff)*(1-x_diff)   + ((d>>8)&0xff)*(x_diff*y_diff);

            // red element
            // Yr = Ar(1-w)(1-h) + Br(w)(1-h) + Cr(h)(1-w) + Dr(wh)
            red = ((a>>16)&0xff)*(1-x_diff)*(1-y_diff) + ((b>>16)&0xff)*(x_diff)*(1-y_diff) +
                  ((c>>16)&0xff)*(y_diff)*(1-x_diff)   + ((d>>16)&0xff)*(x_diff*y_diff);

            temp[offset++] = 
                    0xff000000 | // hardcode alpha
                    ((((int)red)<<16)&0xff0000) |
                    ((((int)green)<<8)&0xff00) |
                    ((int)blue) ;
        }
    }
    return temp ;
}
    
    public static double[] Scaled_28 (BufferedImage image)
    {
        double[] vec = new double[784];
        for (int i = 0; i < 28; i++)
        {
            for (int j = 0; j < 28; j++) 
            {
                int sum = 0;
                int fscale = 5; 
                for (int k = 0; k < 25; k++)
                {
                    for (int l = 0; l < 25; l++)
                    {
                        sum += new Color(image.getRGB(j * 25 + k, i * 25 + l)).getRed();
                    }
                }
                vec[i * 28 + j] = 1 - ((sum / 625.0) / 255.0);
                
            }
        }
        
        return vec;
    }
    
    public static int[] genratePicFromVec (double vec[])
    {
        int []  pixels = new int[784];
        for (int i = 0 ; i < 784 ;i++)
        {
            int cC = (int)(255 - 255*vec[i]);
            pixels[i] = new Color(cC,cC,cC).getRGB();
        }
        return pixels;
    }
    
    public static BufferedImage Fill (BufferedImage image)
    {
        BufferedImage im_Totale = new BufferedImage(700,700, BufferedImage.TYPE_INT_RGB);
        int nbWidth = (700 - image.getWidth()) / 2 ;
        int nbHeight =  (700 - image.getWidth()) / 2 ;
        
        for (int i = 0 ; i < 700 ; i++ )
        {
            for (int j = 0 ; j < 700 ; j++ )
            {
                if (i >= nbWidth && j >= nbHeight && i < nbWidth+image.getWidth() && j < nbHeight+image.getHeight() )
                {
                    im_Totale.setRGB(i, j,image.getRGB(i-nbWidth, j-nbHeight));
                }else
                {
                    im_Totale.setRGB(i, j, new Color(255, 255, 255).getRGB());
                }
            }
        }
        return im_Totale;
    }
    
}























