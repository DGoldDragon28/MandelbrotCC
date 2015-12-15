package nf.co.golddragon.presaids.mandelbrot;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Start {
    
    static JFrame window;
    static DisplayPanel disp;
    static JTextField neq;
    
    public static void main(String[] args) {
        window = new JFrame("Mandelbrot sets");
        disp = new DisplayPanel();
        neq = new JTextField("2", 10);
        
        neq.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {disp.repaint();}});
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(3);
        
        window.add(neq, BorderLayout.NORTH);
        window.add(disp, BorderLayout.CENTER);
        
        window.pack();
        window.setVisible(true);
    }
    
}
