import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file GumballMachine.java
 * 껌볼기기 원격 프록시
 */
public class GumballMachineProxy implements GumballMachineRemote {
	private String serverIPaddr;
	private int port;
	private Socket s = null;
	private DataInputStream dis = null;
	private DataOutputStream dout = null;
	
	public GumballMachineProxy(String serverIPaddr, int port) {
		this.serverIPaddr = Objects.requireNonNull(serverIPaddr);
		this.port = port;
	}
	
	private void start() throws UnknownHostException, IOException {
		s = new Socket(serverIPaddr, port);
		dis = new DataInputStream(s.getInputStream());	
		dout = new DataOutputStream(s.getOutputStream());
	}
	
	@Override
	public String getLocation() throws IOException {
		start();
		dout.writeUTF("getLocation");
		dout.flush();
		String result = (String)dis.readUTF();
		stop();
		return result;
	}

	@Override
	public int getCount() throws IOException {
		start();
		dout.writeUTF("getCount");
		dout.flush();
		String result = (String)dis.readUTF();
		stop();
		return Integer.parseInt(result);
	}

	@Override
	public GumballState getState() throws IOException {
		start();
		dout.writeUTF("getState");
		dout.flush();
		String result = (String)dis.readUTF();
		stop();
		return GumballState.values()[Integer.parseInt(result)];
	}
	
	private void stop() {
		try {
			dout.close();
			dis.close();
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
