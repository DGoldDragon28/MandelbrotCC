package nf.co.golddragon.presaids.mandelbrot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
    
    private static final long serialVersionUID = -2150699916458254933L;
    private static final int SCALE = 150;
    private static final double SCALE_D = (double) SCALE;
    private static final int MAX_ITERATION = 256;

    public DisplayPanel() {
        this.setSize(4 * SCALE, 4 * SCALE);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(4 * SCALE, 4 * SCALE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage canvas = new BufferedImage(4 * SCALE, 4 * SCALE, BufferedImage.TYPE_INT_ARGB);
        double n;
        try {n = Double.parseDouble(Start.neq.getText());} catch (NumberFormatException e) {n = 2.0D;}
        for(int y = 0; y < (4 * SCALE); y++) {
            for(int x = 0; x < (4 * SCALE); x++) {
                double re = (x - (2.0D * SCALE_D)) / SCALE_D;
                double im = (y - (2.0D * SCALE_D)) / SCALE_D;
                int escape = isInSet(re, im, n);
                canvas.setRGB(x, y, (new Color(escape, Math.max(escape - 100, 0), 0)).getRGB());
            }
        }
        ((Graphics2D) g).drawImage(canvas, 0, 0, 4 * SCALE, 4 * SCALE, null);
    }
    
    /**
     * 
     * @param c_re real part
     * @param c_im imaginary part
     * @param n exponent
     * @return 0 if part of the set, otherwise, the iterations left when it escaped.
     */
    static int isInSet(double c_re, double c_im, double n) {
        double re = c_re;
        double im = c_im;
        for(int i = 0; i < MAX_ITERATION; i++) {
            double r = Math.sqrt(re * re + im * im);
            if(r > 4) return MAX_ITERATION - i;
            double arg = Math.atan2(im, re);
            r = Math.pow(r, n);
            arg = arg * n;
            re = r * Math.cos(arg) + c_re;
            im = r * Math.sin(arg) + c_im;
        }
        return (((re * re + im * im) > 4) ? 1 : 0);
    }
}
