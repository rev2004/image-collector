package team.nm.nnet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class FrmTrain extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * List luu duong dan den cac file;
	 */
	private JList lstListFilename;
	
	private Container container;
	
	/**
	 * Luu anh hien tai
	 */
	private JLabel lblCurImage;
	
	public FrmTrain() {
		super();
		initFrame();
		
		
	}
	
	/**
	 * Khoi tao cho frame
	 */
	private void initFrame() {
		this.setTitle("Train for faces classify");
		this.setBounds(new Rectangle(500, 600));
		//
		//Container
		//
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		//
		//lstListFilename
		//
		
		String[] str = {"Doan", "Nguyen", "Minh", "Nhat"};
		lstListFilename = new JList(str);
		lstListFilename.setBounds(new Rectangle(200, 400));
		lstListFilename.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstListFilename.setSelectedIndex(0);
		container.add(lstListFilename, BorderLayout.WEST);
		//
		//lblCurImage
		//
		lblCurImage = new JLabel("Minh NHat");
		lblCurImage.setBackground(Color.blue);
		lblCurImage.setBounds(new Rectangle(100, 200));
		container.add(lblCurImage, BorderLayout.EAST);
	}
	
	

}
