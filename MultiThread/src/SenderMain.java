import java.net.Socket;

public class SenderMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			
			socket = new Socket("127.0.0.1", 9001); 
			//Thread thread1 = new SenderThread(socket); 

			ClientUI frame = new ClientUI(socket);
			frame.setVisible(true);
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
