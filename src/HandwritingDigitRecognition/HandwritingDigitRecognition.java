/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HandwritingDigitRecognition;

import GUI.Frame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Imad
 */
public class HandwritingDigitRecognition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame();
            }
        });
    }
    
}
