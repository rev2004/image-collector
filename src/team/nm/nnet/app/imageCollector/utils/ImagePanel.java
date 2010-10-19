package team.nm.nnet.app.imageCollector.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class ImagePanel extends Panel {
	 
    private static final long serialVersionUID = 10979878687678L;
    
	public Image myimg = null;

    public ImagePanel() {
        setLayout(null);
    }

    public void setImage(Image img) {
        this.myimg = img;
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(myimg, 0, 0, this);
    }
}
