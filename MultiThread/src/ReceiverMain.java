import java.io.*;
import java.net.*;

public class ReceiverMain {	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		Socket socket = null;
		
		try {
			ss = new ServerSocket(9001);
			socket = ss.accept();
			

			Thread thread2 = new ReceiverThread(socket); 
			thread2.start();
			 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
