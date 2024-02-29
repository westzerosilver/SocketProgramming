import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.net.*;

public class ServerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ImagePanel imagePanel = new ImagePanel();
	Socket socket;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public ServerUI(String fileName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane =new ImagePanel(fileName);
		
		
		setContentPane(contentPane);
	}
	
	public ServerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane =new ImagePanel();
		
		
		setContentPane(contentPane);
	}
	
	
	public void ServiceUIRepaint(String imagePath) {
		this.imagePanel = new ImagePanel(imagePath);
		setContentPane(contentPane);
	}
	
	
	
	class ImagePanel extends JPanel {
		String imagePath;
		ImageIcon icon;
		Image img;
		public ImagePanel() {
			
		}
		public ImagePanel(String imagePath) {
			this.imagePath = imagePath;
			this.icon = new ImageIcon(imagePath);
			this.img = icon.getImage();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 20, 20, this);
		}
		
		
	}
	

}
