import java.io.*;
import java.net.*;

public class ReceiverThread extends Thread{

	Socket socket; 
	int size;
	
	ReceiverThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		byte[] buffer = new byte[4 * 1024];
		DataInputStream dataInputStream = null;
		FileOutputStream fileOutputStream = null;
		
		try {
			ServerUI serverUI = new ServerUI();
			ServerUI frame = serverUI;
			frame.setVisible(true);
			while(true) {
				dataInputStream = new DataInputStream(socket.getInputStream());
				
				// 이름, 크기 등 받아오기
				int bytes = 0;
				String fileName = dataInputStream.readUTF();
				fileOutputStream = new FileOutputStream(fileName);	// 프로젝트 파일 내에 저장 
				long size = dataInputStream.readLong();
				
				// 받아온 파일 저장
				while (size > 0 && (bytes = dataInputStream.read(buffer, 0,
									(int)Math.min(buffer.length, size)))!= -1) {
					
					
					fileOutputStream.write(buffer, 0, bytes);
					size -= bytes;
				}
				
				serverUI = new ServerUI(fileName);
				frame = serverUI;
				frame.setVisible(true);
				
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
