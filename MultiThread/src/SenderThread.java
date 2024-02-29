import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class SenderThread extends Thread {
	
	String pathName;		// 보낼 파일의 경로 저장
	Socket socket;			
	
	// 파일의 경로, 소켓을 받아오는 생성자 함수
	public SenderThread(Socket socket, String pathName) {
		this.socket = socket;
		this.pathName = pathName; 
	}
	
	
	public void run() {
		File file = null;
		DataOutputStream dataOutputStream = null;
		FileInputStream fileInputStream = null;
		
		try {
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			int bytes = 0;
			
			file = new File(pathName);
			 fileInputStream= new FileInputStream(file);
			
			dataOutputStream.writeUTF(file.getName());
			System.out.println(file.getName());
			dataOutputStream.writeLong(file.length());
			
			byte[] buffer = new byte[4 * 1024];
			while ((bytes = fileInputStream.read(buffer))
				!= -1) {
			// Send the file to Server Socket 
			dataOutputStream.write(buffer, 0, bytes);
				dataOutputStream.flush();
			}
			
		}
		catch (FileNotFoundException fnfe) {
			System.out.println(pathName + " 파일이 존재하지 않습니다.");
		}
		catch (IOException ioe) {
			System.out.println(pathName + " 파일을 읽을 수 없습니다.");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				fileInputStream.close();
			}
			catch (Exception e) {
			}
		}
	}
}
