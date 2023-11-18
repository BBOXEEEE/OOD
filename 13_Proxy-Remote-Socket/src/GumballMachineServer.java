import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file GumballMachineServer.java
 * 껌볼기기
 * 원격 서버: 원격의 요청을 받아 유지하는 기기에 메소드를 호출하여 그 결과를
 * 다시 클라이언트(프록시)에 전달
 */
public class GumballMachineServer {
	private ServerSocket ss = null;
	private GumballMachine gumballMachine = null;
	
	private class ClientHandler extends Thread{
		private Socket clientSocket;
		
		private ClientHandler(Socket socket) {
			clientSocket = socket;
		}
		public void run() {
			System.out.println("client run");
			try(
				DataInputStream dis = new DataInputStream(
					clientSocket.getInputStream());
				DataOutputStream dout = new DataOutputStream(
					clientSocket.getOutputStream());
			){
				String methodName = (String)dis.readUTF();
				switch(methodName) {
				case "getLocation":
					dout.writeUTF(gumballMachine.getLocation());
					break;
				case "getCount":
					dout.writeUTF(gumballMachine.getCount()+"");
					break;
				case "getState":
					dout.writeUTF(gumballMachine.getState().ordinal()+"");
					break;
				default:
					dout.writeUTF("exception");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				clientSocket.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public GumballMachineServer(GumballMachine gumballMachine) throws IOException {
		this.gumballMachine = Objects.requireNonNull(gumballMachine);
		ss = new ServerSocket(6666);
	}
	
	public void start() throws IOException {
		while(true) {
			Socket clientSocket = ss.accept();
			new ClientHandler(clientSocket).start();
		}
	}
	
	public void stop() {
		try {
			ss.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		GumballMachine gMachine = new GumballMachine("제1공학관", 10);
		gMachine.dispense();
		GumballMachineServer server = new GumballMachineServer(gMachine);
		server.start();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override public void run() {
				timer.cancel();
				server.stop();
			}
		}, 1000, 5000);
	}
}
