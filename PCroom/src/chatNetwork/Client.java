package chatNetwork;

import java.io.*; // 필요한 I/O 클래스를 임포트
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // 네트워크 소켓 통신을 위한 Socket 클래스를 임포트
import java.util.UUID;

import javax.swing.SwingUtilities;

import timeMenu.vo.TimeMenuVO;

public class Client {
	private Socket socket; // 서버와 통신을 위한 소켓
    private DataOutputStream out; // 서버로 데이터를 전송하는 출력 스트림
    private DataInputStream in; // 서버로부터 데이터를 받기 위한 입력 스트림   	
    private PrintWriter writer;
    private String clientId; // 클라이언트 고유 ID
    String msg;
    
 // 클라이언트 ID 순서를 추적할 리스트
    private static final List<String> clientIds = new ArrayList<>();

    public void start(String host, int port) {
        try {
            socket = new Socket(host, port); // 소켓 초기화
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            // 클라이언트 ID 생성 및 전송
            clientId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            out.writeUTF(clientId);
            out.flush();

            System.out.println("[서버와 연결되었습니다] Client ID: " + clientId);

            // 메시지 수신 스레드 시작
            startReceivingMessages();

        } catch (IOException e) {
            System.out.println("[서버 접속 끊김]");
        }
    }

 
   public String sendCommand(String command) { // 서버로 명령어를 전송하고 응답을 받는 메소드
	    try {
	        System.out.println("Sending command: " + command); // 전송할 명령어 디버깅 출력
	        out.writeUTF(command); // 서버로 명령어 전송
	        out.flush(); // 스트림 강제 비우기
	        
//	     // 명령어가 "MSG_REQUEST"라면 메시지를 수신할 준비를 한다
//	        if ("MSG_REQUEST".equals(command)) {
//	        	System.out.println("여기 들어왔다");
//	            //receiveMessages(); // 명령어에 맞춰 메시지를 수신하는 메서드 호출
//	            
//	        }
	        
	        StringBuilder response = new StringBuilder(); // 서버로부터 받은 응답을 저장할 StringBuilder
	        while (true) {
	            String line = in.readUTF(); // 서버로부터 한 줄씩 읽기
	            System.out.println("Received line: " + line); // 받은 데이터 디버깅 출력
	            response.append(line).append("\n"); // StringBuilder에 데이터 추가
	            if (line.equals("END") || line.equals("LOGIN_SUCCESS") || line.equals("LOGIN_FAILURE")
	            		|| line.equals("ID_DUPLICATED")|| line.equals("ID_OK")) { 
	                break;
	            }
	            else if (line.startsWith("SEAT_SELECTED")) {
	                break;
	            }
	            else if (line.startsWith("STOP")) {
	                break;
	            }
	            else if (line.startsWith("CHECK_OK")) {
	                break;
	            }
//	            else if (line.startsWith("MSG_REQUEST")){
//	            	System.out.println("여기 들어왔다");
//	            	receiveMessages();
//	            }
	            
	        }  
	        return response.toString().trim(); // 응답 문자열 반환
	    } catch (IOException e) {
	        e.printStackTrace(); // I/O 예외 발생 시 스택 트레이스 출력
	        return null; // 예외 발생 시 null 반환
	    }
	}
   
   public String receiveMessage() {
	    try {
	        while (true) {
	            if (socket != null && !socket.isClosed() && in.available() > 0) {
	                String receivedMessage = in.readUTF(); // 서버로부터 메시지 수신
	                System.out.println("받은 메시지: " + receivedMessage);
	                return receivedMessage; // 메시지를 반환하고 메서드 종료
	            } else {
	                // 수신 가능한 데이터가 없을 때 다른 작업 수행 가능
	                Thread.sleep(100); // CPU 과부하 방지
	            }
	        }
	    } catch (IOException | InterruptedException e) {
	        System.err.println("메시지 수신 실패: " + e.getMessage());
	    }
	    return null; // 예외 발생 시 null 반환
	}





   
   public void sendMessage(String targetClientId, String message, int seatNum) throws IOException {
	    if (out != null) {
	        // "MSG|<message>" 형식으로 전송하되, 대상 클라이언트에게만 보내기 위해 `targetClientId` 추가
	        out.writeUTF("MSG|"+ seatNum + "|"+message);
	        out.flush();
	    } else {
	        throw new IOException("서버와 연결되어 있지 않습니다.");
	    }
	}



   public void close() { // 클라이언트 소켓을 닫는 메소드
	   if (socket != null && !socket.isClosed()) { 
	   try {
	        socket.close(); // 소켓 닫기
	    } catch (SocketException e) {
	        System.err.println("SocketException 발생: PC 종료 또는 네트워크 오류");
	    } catch (EOFException e) {
	        System.err.println("EOFException 발생: 스트림의 끝에 도달");
	        
	    } catch (IOException e) {
	        System.err.println("IOException 발생: 소켓 닫는 중 오류");
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
	    }
	   }
	}
   
// 클라이언트 ID를 반환하는 메서드
   public String getClientId() {
       return clientId;
   }

   // 클라이언트 ID를 설정하는 메서드
   public void setClientId(String clientId) {
       this.clientId = clientId;
   }
   
	// 클라이언트 내부에 메시지 수신 스레드를 추가하는 메서드
	public void startReceivingMessages() {
	    // 메시지 수신을 처리하는 새로운 스레드 시작
	    new Thread(() -> {
	        try {
	            while (socket != null && !socket.isClosed()) { // 소켓이 열려 있는 동안 반복
	                if (in != null && in.available() > 0) { // 데이터가 들어온 경우만 처리
	                    String receivedMessage = in.readUTF(); // 서버로부터 메시지 읽기
	                    System.out.println("[받은 메시지] " + receivedMessage); // 콘솔에 출력
	
	                    // 메시지를 UI에 표시하거나 다른 작업으로 처리하려면 여기에 로직 추가
	                    handleIncomingMessage(receivedMessage);
	                }
	                Thread.sleep(100); // 과도한 CPU 사용 방지
	            }
	        } catch (IOException e) {
	            System.err.println("[메시지 수신 실패] " + e.getMessage());
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt(); // 스레드 인터럽트 시 안전 종료
	        }
	    }).start();
	}

	// 수신된 메시지를 처리하는 메서드 (UI와 연동 가능)
	private void handleIncomingMessage(String message) {
	    // 예: UI 텍스트 필드에 메시지를 추가하거나 로그에 기록
		SwingUtilities.invokeLater(() -> {
	    // UI 업데이트 코드 (예: 채팅 창에 메시지 추가)
	    System.out.println("UI 업데이트: " + message);
	    });
	}

//	// 서버에서 연결된 클라이언트에게 메시지 전송
//	public void broadcastMessage(String message) {
//	    synchronized (clientHandlers) {
//	        for (ClientHandler handler : clientHandlers) {
//	            try {
//	                handler.out.writeUTF(message); // 클라이언트로 메시지 전송
//	                handler.out.flush();
//	            } catch (IOException e) {
//	                System.err.println("[브로드캐스트 실패] " + e.getMessage());
//	            }
//	        }
//	    }
//	}

   

	
}

