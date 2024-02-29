import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.net.*;

public class ClientUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField filePathTextField;				// 파일 경로를 나타내는 텍스트 필드
	JButton searchFileBtn;								// 파일 다이얼로그로 이동할 버튼
	JButton sendBtn;									// 파일 전송 버튼 
	String pathName;									// 사용자가 선택한 파일의 절대 경로
	Socket socket;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ClientUI(Socket socket) {
		this.socket = socket;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		//contentPane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("보낼 파일을 선택해주세요");
		
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		filePathTextField = new JTextField();
		panel.add(filePathTextField);
		filePathTextField.setColumns(10);
		
		searchFileBtn = new JButton("찾기");
		searchFileBtn.addActionListener(new ServerUIActionListener());
		panel.add(searchFileBtn);
		
		sendBtn = new JButton("전송");
		contentPane.add(sendBtn);
	}

	public class ServerUIActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == searchFileBtn) {
				try {
					JFileChooser chooser = new JFileChooser();
					int ret = chooser.showOpenDialog(null);
					pathName = chooser.getSelectedFile().getPath();
					filePathTextField.setText(pathName);
					Thread senderThread = new SenderThread(socket ,pathName);
					senderThread.start();
				}
				catch (NullPointerException npe) {
					System.out.println(npe);
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
			else if(e.getSource() == sendBtn) {
				if(!pathName.isEmpty()) {
					Thread senderThread = new SenderThread(socket ,pathName);
					senderThread.start();
				}
			}
		}
		
	}
}
