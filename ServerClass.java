import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
	public static void main(String args[]){
		try{
			ServerSocket s = new ServerSocket(3000);
			for(;;){
				Socket incoming = s.accept();
				new ThreadedObject(incoming).start();
			}
			
		}catch(Exception e){
			System.out.println(e);			
		}
	}
}


