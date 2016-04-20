import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/*
Kevin Cramsey
3 July 2014
CIS 163 AA
Module #13 Programming Challenge
ImageViewer.java
*/

public class ImageViewer 
{
	private JFrame frame;
	private final int FRAME_WIDTH = 320;
	private final int FRAME_HEIGHT = 240;
	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JButton btnOpen;
	private ButtonListener buttonListener;
	private JLabel lblImage;
	private JFileChooser imgChooser;
	private ImageIcon image;
	private File imgFile;
	
	public ImageViewer()
	{
		frame = new JFrame();
		imagePanel = new JPanel();
		buttonPanel = new JPanel();
		btnOpen = new JButton("Open");
		buttonListener = new ButtonListener();
		lblImage = new JLabel();
		imgChooser = new JFileChooser();
		image = null;
		
		createAndShowGUI();
	}
	
	private void createAndShowGUI()
	{
		btnOpen.setPreferredSize(new Dimension(100, 30));
		btnOpen.setMnemonic('o');
		btnOpen.setToolTipText("Open a dialog to choose an image file. Must be .gif, .jpg, or .png file type");
		btnOpen.addActionListener(buttonListener);
		
		imagePanel.add(lblImage);
		buttonPanel.add(btnOpen);
		
		frame.setTitle("Java Image Viewer");
		frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) 
		{	
			lblImage.setIcon(loadImage());
			
			if(lblImage.getIcon() != null)
			{
				frame.pack();
			}
		}
	}
	
	public ImageIcon loadImage()
	{
		if(imgChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			imgFile = imgChooser.getSelectedFile();
			image = new ImageIcon(imgFile.getPath());
		}
		
		return image;
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ImageViewer();
			}
		});
	}
}