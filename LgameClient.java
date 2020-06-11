import java.io.*;
import java.net.*;
import java.util.Scanner;
public class LgameClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		String clientplayer, serverplayer, serveraddress;
		System.out.print("Input server address(192.168.56.1/5000): ");   //서버 주소와 포트 입력
		serveraddress = sc.next();
		String serverinfo[] = serveraddress.split("/");                // /기준으로 나눠서 저장
		int serverport = Integer.parseInt(serverinfo[1]);
		try {
			Socket soc = new Socket(serverinfo[0], serverport);     //ip주소와 포트번호를 넣음
			DataOutputStream dous = new DataOutputStream (soc.getOutputStream());  //데이터 보내는 스트림
			DataInputStream dins = new DataInputStream(soc.getInputStream());      //데이터 읽어오는 스트림
		
			System.out.println("Connected to server!: "+serveraddress);   //연결되면 서버주소 출력
			
			System.out.print("Input yout nickname: ");             //클라이언트 닉네임 입력
			clientplayer = sc.next();
			dous.writeUTF(clientplayer);
			System.out.println("[client] Your ip: "+ soc.getLocalAddress());    //클라이언트 ip보여주기(그냥)
			System.out.println("[client] Your nickname: "+clientplayer);        //이름도 같이 보여주기
			
			
			serverplayer = dins.readUTF();                                      //서버측 닉네임 읽은 후 저장
			System.out.println("[server] Server nickname: "+serverplayer);
			
			int order= dins.readInt();            //순서 결과를 읽어드림(0또는 1)
			if(order==1) {                              //1이면 클라이언트부터 시작
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
			soc.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
