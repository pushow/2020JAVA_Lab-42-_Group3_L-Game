import java.beans.DesignMode;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;
public class LgameServer {
	static int DesideOrder() {
		return (int)(new Random().nextInt())%2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serversocket = null;
		Scanner sc= new Scanner(System.in);
		System.out.print("Input your nickname: ");
		String serverplayer=sc.next();         //서버측 사용자 닉네임 입력                        
		String clientplayer;                      //클라이언트 측 사용자 닉네임 저장 
		 try {
			 //서버 소켓 셋팅
			 serversocket = new ServerSocket();

	         InetAddress inetAddress = InetAddress.getLocalHost();
	         String localhost = inetAddress.getHostAddress();
	 
	         serversocket.bind(new InetSocketAddress(localhost, 5000));
	 
	         System.out.println("[server] Binding " + localhost);
	         System.out.println("[server] Port: 5000");
	         System.out.println("[server] Your nickname: "+serverplayer);
	         System.out.println("Server is working...");
	         System.out.println("=====================================");
	         
	         
	         //클라이언트 접속 대기
	         Socket socket = serversocket.accept();
	 
	         OutputStream os = socket.getOutputStream();
	         DataOutputStream dous = new DataOutputStream(os);   //데이터 보내는 스트림
	         
	         InputStream ins = socket.getInputStream();
	         DataInputStream dins = new DataInputStream(ins);    //데이터 읽어오는 스트림
	         clientplayer= dins.readUTF();                    // 클라이언트 닉네임 읽어오기
	         System.out.println("Client connected!");
	         System.out.println("Client ip: "+socket.getInetAddress());    //클라이언트의 ip주소   
	         System.out.println("Client nickname: "+clientplayer);
	         dous.writeUTF(serverplayer);   //서버 닉네임 클라이언트에 보내기
	         int order = DesideOrder();      //순서를 정하기 0,1 중 랜덤으로 결정됨
	         dous.writeInt(order);           //순서 결과를 클라이언트에 보냄(0또는 1)
	         
	         if(order==0) {                                  //순서결과가 0애면 서버 먼저 시작
	        	 System.out.println("You first!");
	         }
	         else {
	        	 System.out.println("You second");
	         }
	         
	         
	         /*
		  * 여기에 GUI와 게임 실행
		  */
	         
	         
	         System.out.println("The end(일단 여기까지)");  //일단 여기까지
	         
	         dins.close();
	         dous.close();
	         socket.close();
	         serversocket.close();
	         
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 
		 
		 
		
		 
		 
	}

}

