package team.nm.nnet.app.imageCollector.layout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Capture {

	private MediaCapture mediaCapture;
	private JFrame frame = null;
	
	public Capture(MediaCapture mediaCapture) {
		this.mediaCapture = mediaCapture;
	}
	
	public void show() {

		if(frame == null) {
			mediaCapture.initialize();		
			frame = new JFrame();
	        frame.addWindowListener(new WindowAdapter() {
	 
	            public void windowClosing(WindowEvent e) {
	            	mediaCapture.stop();
	            	super.windowClosing(e);
	            }
	            
	            @Override
	            public void windowClosed(WindowEvent e) {
	            	mediaCapture.close();
	            	super.windowClosed(e);
	            }
	        });
	 
	        frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
	        frame.add("Center", mediaCapture);
	        frame.setTitle("Camera - NM Team");
	        frame.setSize(mediaCapture.getSize());
	        frame.pack();
		} else {
			mediaCapture.play();
		}
        frame.setVisible(true);
	}
	
	public void setParent(MainFrame parent) {
		mediaCapture.setParent(parent);
	}
}
