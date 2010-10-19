package team.nm.nnet.app.imageCollector.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.media.Buffer;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import org.springframework.beans.factory.annotation.Required;

import team.nm.nnet.app.imageCollector.utils.ImageFilter;
import team.nm.nnet.app.imageCollector.utils.ImagePanel;
import team.nm.nnet.util.ImageUtils;

public class MediaCapture extends Panel implements ActionListener {

	private static final long serialVersionUID = 8927815914445440376L;
	
	private static Player player;
	private MediaLocator mediaLocator;
	private JButton saveBtn;
	private Buffer buffer;
	private Image img;
	private BufferToImage buffToImg;
	private ImagePanel imgPanel;
	private String locatorString;

	public void initialize() {
		setLayout(new BorderLayout());

		imgPanel = new ImagePanel();
		saveBtn = new JButton("Lưu ảnh");
		saveBtn.addActionListener(this);

		mediaLocator = new MediaLocator(locatorString);

		try {
			player = Manager.createRealizedPlayer(mediaLocator);
			player.start();
			Component comp;
			if ((comp = player.getVisualComponent()) != null) {
				setSize(comp.getSize());
				imgPanel.setSize(comp.getSize());
				add(comp, BorderLayout.NORTH);
			}
			add(saveBtn, BorderLayout.CENTER);
			add(imgPanel, BorderLayout.SOUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		player.close();
		player.deallocate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComponent) {
			JComponent c = (JComponent) e.getSource();
			if (c == saveBtn) {
				final JFileChooser chooser = new JFileChooser(".");
				ImageFilter imageFilter = new ImageFilter();
				chooser.addChoosableFileFilter(imageFilter);
				int returnVal = chooser.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					if (imageFilter.accept(selectedFile)) {
						action(selectedFile);
					}
				}
			}
		}
	}

	protected void action(File file) {
		// Grab a frame
		FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
		buffer = fgc.grabFrame();

		// Convert it to an image
		buffToImg = new BufferToImage((VideoFormat) buffer.getFormat());
		img = buffToImg.createImage(buffer);

		// show the image
		imgPanel.setImage(img);

		// save image
		BufferedImage bufferedImage = ImageUtils.toBufferedImage(img);
		if (bufferedImage != null) {
			if(ImageFilter.getExtension(file).compareToIgnoreCase(ImageFilter.PNG) == 0) {
				ImageUtils.saveToPng(bufferedImage, file);
			} else {
				ImageUtils.drawImageToJpgByteStream(bufferedImage, file);
			}
		}

	}

	@Required
	public void setLocatorString(String locatorString) {
		this.locatorString = locatorString;
	}
}
