//package network;
//
//import java.io.*; // 필요한 I/O 클래스를 임포트
//import java.net.Socket;
//import java.net.SocketException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner; // 네트워크 소켓 통신을 위한 Socket 클래스를 임포트
//import java.util.UUID;
//
//import timeMenu.vo.TimeMenuVO;
//
//public class Client {
//	private Socket socket; // 서버와 통신을 위한 소켓
//    private DataOutputStream out; // 서버로 데이터를 전송하는 출력 스트림
//    private DataInputStream in; // 서버로부터 데이터를 받기 위한 입력 스트림   	
//    private PrintWriter writer;
//    private String clientId; // 클라이언트 고유 ID
//
//    String msg;
//    
//   public void start(String host, int port) { // 생성자, 서버의 호스트 주소와 포트 번호를 인자로 받음
//        try {
//        	socket = new Socket(host, port); // 소켓 초기화, 서버와 연결 시도
//            out = new DataOutputStream(socket.getOutputStream()); // 출력 스트림 초기화
//            in = new DataInputStream(socket.getInputStream()); // 입력 스트림 초기화
//            // 고유 clientId 생성 (UUID)
//            clientId = UUID.randomUUID().toString();
//            System.out.println("[서버와 연결되었습니다] Client ID: " + clientId);     
//           // System.out.println("[서버와 연결되었습니다]");
// 
//         
//        } catch (IOException e) {
//        	System.out.println("[서버 접속끊김]");
//            //e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
//        }
//    }
// 
//   public String sendCommand(String command) { // 서버로 명령어를 전송하고 응답을 받는 메소드
//	    try {
//	        System.out.println("Sending command: " + command); // 전송할 명령어 디버깅 출력
//	        out.writeUTF(command); // 서버로 명령어 전송
//	        out.flush(); // 스트림 강제 비우기
//	        StringBuilder response = new StringBuilder(); // 서버로부터 받은 응답을 저장할 StringBuilder
//	        while (true) {
//	            String line = in.readUTF(); // 서버로부터 한 줄씩 읽기
//	            System.out.println("Received line: " + line); // 받은 데이터 디버깅 출력
//	            response.append(line).append("\n"); // StringBuilder에 데이터 추가
//	            if (line.equals("END") || line.equals("LOGIN_SUCCESS") || line.equals("LOGIN_FAILURE")) { 
//	                break;
//	            }
//	            else if (line.startsWith("SEAT_SELECTED")) {
//	                break;
//	            }
//	            
//	        }  
//	        return response.toString().trim(); // 응답 문자열 반환
//	    } catch (IOException e) {
//	        e.printStackTrace(); // I/O 예외 발생 시 스택 트레이스 출력
//	        return null; // 예외 발생 시 null 반환
//	    }
//	}
//   
//   public void sendMessage(String message) throws IOException {
//	    if (out != null) {
//	        out.writeUTF("MSG|" + message);
//	        out.flush();
//	    } else {
//	        throw new IOException("서버와 연결되어 있지 않습니다.");
//	    }
//	}
//   
//
//
//   public void close() { // 클라이언트 소켓을 닫는 메소드
//	   if (socket != null && !socket.isClosed()) { 
//	   try {
//	        socket.close(); // 소켓 닫기
//	    } catch (SocketException e) {
//	        System.err.println("SocketException 발생: PC 종료 또는 네트워크 오류");
//	    } catch (EOFException e) {
//	        System.err.println("EOFException 발생: 스트림의 끝에 도달");
//	        
//	    } catch (IOException e) {
//	        System.err.println("IOException 발생: 소켓 닫는 중 오류");
//	        e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
//	    }
//	   }
//	}
//   
//   // 클라이언트 ID 반환 메서드
//   public String getClientId() {
//       return clientId;
//   }
//
//
//	
//}
//
