package team.nm.nnet.app.imageCollector.layout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Required;

public class Capture {

	private MediaCapture mediaCapture;
	private JFrame frame;
	
	public Capture() {
		frame = new JFrame();
	}
	public void show() {

		mediaCapture.initialize();
        frame.addWindowListener(new WindowAdapter() {
 
            public void windowClosing(WindowEvent e) {
            	mediaCapture.close();
                System.exit(0);
            }
        });
 
        frame.add("Center", mediaCapture);
        frame.setSize(mediaCapture.getSize());
        frame.pack();
        frame.setVisible(true);
	}
	
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	@Required
	public void setMediaCapture(MediaCapture mediaCapture) {
		this.mediaCapture = mediaCapture;
	}
}
