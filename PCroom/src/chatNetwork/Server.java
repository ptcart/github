package chatNetwork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.AdminUI;
import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import remainTime.vo.RemainTimeVO;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import timeMenu.controller.TimeMenuController;
import timeMenu.controller.TimeMenuControllerImpl;
import timeMenu.vo.TimeMenuVO;
import seat.vo.SeatVO;
import foodMenu.controller.FoodMenuController;
import foodMenu.controller.FoodMenuControllerImpl;
import foodMenu.vo.FoodMenuVO;
import foodSort.controller.FoodSortController;
import foodSort.controller.FoodSortControllerImpl;
import foodSort.vo.FoodSortVO;
import order.vo.OrderVO;
import order.controller.OrderController;
import order.controller.OrderControllerImpl;
import orderDetail.vo.OrderDetailVO;
import orderDetail.controller.OrderDetailController;
import orderDetail.controller.OrderDetailControllerImpl;
import admin.vo.AdminVO;
import admin.window.ServerMessage;
import admin.window.adminLogin;
import admin.controller.AdminController;
import admin.controller.AdminControllerImpl;
import payTime.vo.PayTimeVO;
import payTime.controller.PayTimeController;
import payTime.controller.PayTimeControllerImpl;





public class Server {

    private ServerSocket serverSocket;
    static MemberController memberController = new MemberControllerImpl();
    static SeatController seatController = new SeatControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();
    static TimeMenuController timeMenuController = new TimeMenuControllerImpl();
    static FoodMenuController foodMenuController = new FoodMenuControllerImpl();
    static FoodSortController foodSortController = new FoodSortControllerImpl();
    static OrderController orderController = new OrderControllerImpl();
    static OrderDetailController orderDetailController = new OrderDetailControllerImpl();
    static AdminController adminController = new AdminControllerImpl();
    static AdminController AdminController = new AdminControllerImpl();
    static PayTimeController payTimeController = new PayTimeControllerImpl();
    
    private static Server server;  // Server 객체를 클래스 변수로 선언
    private static String latestRequest;  // 최근 요청을 저장할 변수
    private static final Map<String, ClientReceiver> clients = new HashMap<>();
    private static Map<Integer, String> seatClientMap = new HashMap<>();
    private static List<String> loggedInUsers = new ArrayList<>();
    private static int initialRemainingTime = 0; // 여전히 private
    
    private static Queue<String> requestQueue = new LinkedList<>();

    
    public static void main(String[] args) {
        // 서버 초기화
        Server ServerBackground = new Server();

        // 서버 실행을 별도 스레드에서 처리
        new Thread(() -> {
            ServerBackground.start(); // 서버 실행
        }).start();

        // UI 실행 (서버 실행 이후에도 UI가 독립적으로 실행됨)
        SwingUtilities.invokeLater(() -> {
            adminLogin adminLogin = new adminLogin(ServerBackground); // 서버 전달
            adminLogin.frame.setVisible(true);
        });
    }

    public void start() {
        int port = 12345;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(port + " 포트로 서버 열림");

            while (true) {
                System.out.println("[클라이언트 연결 대기중]");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // 새로운 클라이언트 처리를 위한 스레드 생성
                new ClientReceiver(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // 모든 클라이언트에게 메시지를 전송하는 메서드
    public static void sendMessageToAllClients1(String message) {
        synchronized (clients) {
        	System.out.println("클라이언트들 : " + clients);

            for (ClientReceiver clientReceiver : clients.values()) {
                clientReceiver.sendMessage(message);
            }
        }
    }
    
	    
    
    

    public static class ClientReceiver extends Thread {
        private Socket clientSocket;
        private DataInputStream in;
        private DataOutputStream out;
        private String clientId; // 클라이언트 ID 저장
        private ServerMessage serverMessageWindow; // ServerMessage 창 참조

        public ClientReceiver(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                // 클라이언트 UUID 수신
                clientId = in.readUTF();  // 클라이언트가 보낸 UUID 받기
                synchronized (clients) {
                    clients.put(clientId, this);  // UUID를 클라이언트 식별자로 사용하여 저장
                }

                // 클라이언트 메시지 수신 및 처리
                while (true) {
                    try {
                        if (in.available() > 0) {
                            String request = in.readUTF();
                            System.out.println("Received from " + clientId + ": " + request);

                            // 메시지와 명령어 구분
                            if (request.startsWith("MSG|")) { // 메시지 전송
                                
                            	requestQueue.offer(request);
                            	latestRequest = request;
                                String message = request.substring(4); // "MSG|" 이후의 내용
                                // 메시지를 모든 클라이언트에게 전송
                                //sendMessageToAllClients(message);
                            } else { // 명령어 처리
                                String[] parts = request.split(",");
                                String command = parts[0];
                                Clienthandler(command, parts, out);
                            }
                        } else {
                            Thread.sleep(100);
                        }
                    } catch (EOFException e) {
                        System.err.println("EOFException 발생: 스트림의 끝에 도달");
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("IOException 발생: " + e.getMessage());
                e.printStackTrace();
            } finally {
                //cleanup()
            }
        }

        // 클라이언트에게 메시지를 전송하는 메서드
        public void sendMessage(String formattedMessage) {
            try {
                out.writeUTF(formattedMessage); // 클라이언트로 메시지 전송
                out.flush();
            } catch (IOException e) {
                System.err.println("메시지 전송 실패: " + e.getMessage());
            }
        }
    }
    
 // requestQueue의 복사본을 반환하는 getter
    public static Queue<String> getRequestQueue() {
        return new LinkedList<>(requestQueue); // 큐의 복사본 반환
    }
    
    
    private void handleMessage(String senderId, String message) {
        System.out.println("[" + senderId + "] 메시지: " + message);
        //broadcastMessage(senderId, message); // 모든 클라이언트에게 메시지 브로드캐스트
    }

	  // 최신 메시지 반환 메서드
	 public static String getLatestRequest() {
	     return latestRequest;
	 }
	 
	// 큐를 비우는 메서드
	    public static void clearRequestQueue() {
	        requestQueue.clear(); // 큐 비우기
	    }
	
	 // 메시지 수신 처리
	 public static void handleClientRequest(String request) {
	     // 최신 메시지 업데이트
	     latestRequest = request;
	 }
	 
    public static List<String> getLoggedInUsers(int remainTime) {
        return new ArrayList<>(loggedInUsers);
    }

    public static void Clienthandler(String command, String[] parts, DataOutputStream out) throws IOException {
        switch (command) {
            // 회원 명령어
            // 로그인 명령어
            case "LOG_MEM":
                boolean loginSuccess = false;
                MemberVO member = new MemberVO();
                String id = parts[1];
                member.setId(parts[1]);
                member.setPw(parts[2]);
                System.out.println("Login member: " + member); // 디버깅 출력
                List<MemberVO> mems = memberController.logMember(member);

                for (MemberVO m : mems) {
                    // 아이디 비밀번호 둘다 일치할 경우
                    if (m.getId().equals(parts[1]) && m.getPw().equals(parts[2])) {
                        loginSuccess = true;
                        break; // 일치하는 사용자를 찾았으면 더 이상 반복할 필요 없음
                    }
                }
                // 로그인 성공했을 경우
                if (loginSuccess) {
                    //loggedInUsers.add(id);
                    System.out.println("로그인 성공");
                    out.writeUTF("LOGIN_SUCCESS");
                } else {
                    System.out.println("로그인 실패");
                    out.writeUTF("LOGIN_FAILURE");
                }
                out.flush();
                break;
                
                
            case "TIME_MEM":
                boolean loginSuccess1 = false;
                MemberVO member1 = new MemberVO();
                member1.setId(parts[1]);
                System.out.println("Login member: " + member1); // 디버깅 출력
                List<MemberVO> mems1 = memberController.logMember(member1);

                for (MemberVO m : mems1) {
                    // 아이디 일치할 경우
                    if (m.getId().equals(parts[1])) {
                        loginSuccess1 = true;
                        break; // 일치하는 사용자를 찾았으면 더 이상 반복할 필요 없음
                    }
                }
                // 로그인 성공했을 경우
                if (loginSuccess1) {
                    //loggedInUsers.add(id);
                    System.out.println("로그인 성공");
                    out.writeUTF("LOGIN_SUCCESS");
                } else {
                    System.out.println("로그인 실패");
                    out.writeUTF("LOGIN_FAILURE");
                }
                out.flush();
                break;
                
                
            case "CHECK_ID":
                String idToCheck = parts[1];  // 클라이언트에서 보낸 ID
                
                // 중복된 ID가 있는지 확인하는 로직
                List<MemberVO> members = memberController.listMember(new MemberVO());  // 모든 회원 리스트를 조회
                
                boolean isIdAvailable = true;  // 기본값은 ID가 사용 가능하다고 가정
                for (MemberVO member11 : members) {
                    // 만약 동일한 ID가 존재하면 중복된 ID로 처리
                    if (member11.getId().equals(idToCheck)) {
                        isIdAvailable = false;
                        break;  // 중복된 ID를 찾았으면 반복을 종료
                    }
                }
                
                // 중복 여부에 따라 응답 전송
                if (isIdAvailable) {
                    out.writeUTF("ID_OK");  // 사용 가능한 ID
                } else {
                    out.writeUTF("ID_DUPLICATED");  // 이미 존재하는 ID
                }
                out.flush();
                break;

            case "LOG_ADMIN":
                boolean adLogSuccess = false;
                AdminVO admin = new AdminVO();
                String Id = parts[1];
                admin.setId(parts[1]);
                admin.setPw(parts[2]);
                // System.out.println("Login member: " + admin); // 디버깅 출력
                List<AdminVO> admins = adminController.listAdmin(admin);

                for (AdminVO A : admins) {
                    // 아이디 비밀번호 둘다 일치할 경우
                    if (A.getId().equals(parts[1]) && A.getPw().equals(parts[2])) {
                    	adLogSuccess = true;
                        break; // 일치하는 사용자를 찾았으면 더 이상 반복할 필요 없음
                    }
                }
                // 관리자 로그인 성공했을 경우
                if (adLogSuccess) {
                    //loggedInUsers.add(id);
                    System.out.println("로그인 성공");
                    out.writeUTF("LOGIN_SUCCESS");
                } else {
                    System.out.println("로그인 실패");
                    out.writeUTF("LOGIN_FAILURE");
                }
                out.flush();
                break;

            // 회원가입 명령어
            case "ADD_MEM":
                MemberVO member11 = new MemberVO();
                member11.setId(parts[1]);
                member11.setPw(parts[2]);
                member11.setName(parts[3]);
                member11.setGender(parts[4]);
                member11.setPhoneNum(parts[5]);

                System.out.println("Registering member: " + member11); // 디버깅 출력
                memberController.regMember(member11);
                
                RemainTimeVO remainTime = new RemainTimeVO();
                int initremainTime = 10; // 회원가입했을 때 시간을 0초로 초기화
                remainTime.setUserId(parts[1]);
                remainTime.setRemainTime(initremainTime);
                remainTimeController.regRemainTime(remainTime);
                out.writeUTF("END");
                out.flush();
                break;

              // 회원 정보 찾기 명령어
              case "GET_MEMS":
                 MemberVO searchMember = new MemberVO();
                 searchMember.setId(parts[1]);

                  List<MemberVO> members1 = memberController.listMember(searchMember);
                  for (MemberVO m : members1) {
                      out.writeUTF(m.getId() + "," + m.getPw() + "," + m.getName() + "," + m.getGender() + "," + m.getPhoneNum());
                  }
                  out.writeUTF("END");
                  break;
                
              // 회원 수정 명령어
             case "MOD_MEM":
                MemberVO modMem = new MemberVO();
                modMem.setId(parts[1]);
                modMem.setPw(parts[2]);
                modMem.setName(parts[3]);
                modMem.setGender(parts[4]);
                modMem.setPhoneNum(parts[5]);
                memberController.modMember(modMem);
                out.writeUTF("END");
                break;
                
             // 회원 탈퇴 명령어
             case "REMOVE_MEM":
                MemberVO removeMem = new MemberVO();
                RemainTimeVO rTime = new RemainTimeVO();
                SeatVO _seat = new SeatVO();
                
                removeMem.setId(parts[1]);
                rTime.setUserId(parts[1]);
                _seat.setUserId(null);
                _seat.setSeatNum(Integer.parseInt(parts[2]));
                
                remainTimeController.removeRemainTime(rTime);
                seatController.modSeat(_seat);
                memberController.removeMember(removeMem);
        
                out.writeUTF("END");
                break;
                
            // 좌석 선택 명령어
            case "SEAT_MEM":
                SeatVO seat = new SeatVO();
                seat.setUserId(parts[1]);
                seat.setSeatNum(Integer.parseInt(parts[2]));
                String clientId = parts[3];      // parts[3] = clientId
                
             // 좌석 번호와 클라이언트 ID 매핑
                seatClientMap.put(seat.getSeatNum(), clientId);
                
                RemainTimeVO remainTime1 = new RemainTimeVO();
                remainTime1.setUserId(parts[1]);
                List<RemainTimeVO> reTime = remainTimeController.listRemainTime(remainTime1);

                loggedInUsers.add(parts[1]);

             // reTime 리스트에서 initialRemainingTime 값을 추출
                if (!reTime.isEmpty()) {
                	initialRemainingTime = reTime.get(0).getRemainTime();                }
                
                seatController.modSeat(seat);
                out.writeUTF("SEAT_SELECTED," + initialRemainingTime);
                //out.writeUTF("END");
                out.flush();
                break;
                
             // PC 종료 명령어
            case "QUIT_PC": 
                RemainTimeVO remainTime2 = new RemainTimeVO();
                remainTime2.setUserId(parts[1]);
                remainTime2.setRemainTime(Integer.valueOf(parts[2]));
                // 사용금액 0원 처리도 추가해야함 --> 알아서 0으로 처리되나봄
                int useMoney = 0;
                remainTime2.setUseMoney(useMoney);
          	    remainTimeController.modRemainTime(remainTime2);

          	    SeatVO quitSeat = new SeatVO();
          	    quitSeat.setSeatNum(Integer.parseInt(parts[3]));
          	    quitSeat.setUserId(null);
                
                seatController.modSeat(quitSeat);
                out.writeUTF("END");
                
                out.flush();
                break;
                
             // 시간메뉴 열어주는 명령어
            case "TIME_MENU":
                //System.out.println("시간메뉴 도착");
                TimeMenuVO timeMenuVO = new TimeMenuVO();
                List<TimeMenuVO> timeMenu = timeMenuController.listTimeMenu(timeMenuVO);

                for (TimeMenuVO T : timeMenu) {    
                    String data = T.getTimeNum() + "," + T.getAddTime() + "," + T.getPayment() + "," + T.getCost();
                    out.writeUTF(data);
                    System.out.println(data);
                }
                // writeUTF로 END 전송
                out.writeUTF("END");

                out.flush();
                break;
                
            // 사용자 남은시간 수정 명령어
            case "MOD_TIME":
            	RemainTimeVO remainTime3 = new RemainTimeVO();
            	remainTime3.setUserId(parts[1]);
            	List<RemainTimeVO> timelist = remainTimeController.listRemainTime(remainTime3);
            	int seconds = 0;

            	if (!timelist.isEmpty()) { // 리스트가 비어 있지 않은 경우
                    seconds = timelist.get(0).getRemainTime(); // 첫 번째 요소의 남은 시간 가져오기
                }
            	
            	
            	remainTime3.setRemainTime(Integer.valueOf(parts[2]));
            	int result = 0;
//            	remainTime3.setRemainTime(seconds);
            	if(Math.abs(seconds - remainTime3.getRemainTime()) < 100) {
            		remainTime3.setRemainTime(Integer.valueOf(parts[2]));
            	}
            	else {
            		result = Math.abs(seconds - remainTime3.getRemainTime());
            		remainTime3.setRemainTime(seconds);
            		System.out.println("시간 차이" + result);
            		
            		out.writeUTF("STOP," + result);
            	}
            	System.out.println("처음 가져온 값 : " + seconds);
            	System.out.println("추가하고 가져온 값 : " + remainTime3.getRemainTime());
            	
            	seconds = 0;
            	result = 0;
            	
            	remainTime3.setUseMoney(Integer.valueOf(parts[3]));
          	    remainTimeController.modRemainTime(remainTime3);
          	    
          	    PayTimeVO payTime = new PayTimeVO();
          	    
          	    out.writeUTF("END");
          	    out.flush();
          	    break;
          	    
          	// 사용자 남은시간 수정 명령어
            case "MOD_TIME1":
            	RemainTimeVO remainTime31 = new RemainTimeVO();
            	remainTime31.setUserId(parts[1]);

            	remainTime31.setRemainTime(Integer.valueOf(parts[2]));

            	remainTime31.setUseMoney(Integer.valueOf(parts[3]));
          	    remainTimeController.modRemainTime(remainTime31);
          	    
          	    
          	    out.writeUTF("END");
          	    out.flush();
          	    break;
                
          	    
          	// 사용자 남은시간 수정 명령어
            case "MOD_TIME_SIMPLE":
            	 System.out.println("출력됨~");
            	RemainTimeVO remainTime4 = new RemainTimeVO();
            	remainTime4.setUserId(parts[1]);
            	remainTime4.setRemainTime(Integer.valueOf(parts[2]));
            	
            	// 남은시간 리스트를 받아온다
                List<RemainTimeVO> remainTimeList = remainTimeController.listRemainTime(remainTime4);
                int remainTime11 = 0;
                if (!remainTimeList.isEmpty()) {
                    // 첫 번째 값에서 남은시간 가져오기
                    RemainTimeVO firstRemainTime = remainTimeList.get(0);
                    
                    remainTime11 = firstRemainTime.getRemainTime();  // 남은 시간 가져오기
                    System.out.println("출력된 시간 : "+remainTime11);
                    
                    // 이후 로직에서 remainTime을 사용
                    // remainTimeController.modRemainTime(remainTime4);
                }
                int totalTime = remainTime4.getRemainTime() + remainTime11; 
                System.out.println("더한 시간 : " +totalTime);
                remainTime4.setRemainTime(totalTime);
                
          	    remainTimeController.modRemainTime(remainTime4);
          	    
          	    
          	    out.writeUTF("END");
          	    break;

            case "FOOD_MENU":
            	FoodMenuVO FoodMenu = new FoodMenuVO();
                FoodSortVO FoodSort = new FoodSortVO();
                
                if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
                    FoodMenu.setName(parts[1]);
                    //FoodSort.setName(parts[1]);
                }
                
                List<FoodMenuVO> fm = foodMenuController.listFoodMenu(FoodMenu);
                List<FoodSortVO> fs = foodSortController.listFoodSort(FoodSort);
                
                // 음식 종류 번호를 키로 하고, 음식 종류 이름을 값으로 하는 맵을 생성
                Map<Integer, String> foodSortMap = new HashMap<>();
                for (FoodSortVO s : fs) {
                    foodSortMap.put(s.getSortId(), s.getName()); // 음식 종류 번호와 이름을 매핑
                }
                
                for (FoodMenuVO f : fm) {
                	// f.getSort()가 String이라면, Integer로 변환
                    int sortId = Integer.parseInt(f.getSort()); // String을 Integer로 변환
                    String sortName = foodSortMap.get(sortId); // 음식 종류 번호로 이름을 가져옴
                    out.writeUTF(f.getMenuId() + "," + sortName + "," + f.getName() + "," + f.getPrice() + "," + f.getImagePath());
                }

                out.writeUTF("END");
                break;

            case "FOOD_SORT":
                FoodSortVO _FoodSort = new FoodSortVO();
                List<FoodSortVO> _fs = foodSortController.listFoodSort(_FoodSort);

                for (FoodSortVO s : _fs) {
                    out.writeUTF(s.getSortId() + "," + s.getName());
                }
                out.writeUTF("END");
                break;
                
            case "FOOD_ORDER": // 결제방식 + 좌석 + 주문상태
                OrderVO order = new OrderVO();
                int nextOrderId = orderController.maxOrder();
                order.setOrderId(nextOrderId);
                order.setPayment(parts[1]);
                order.setSeatNum(Integer.parseInt(parts[2]));
                order.setState(Integer.parseInt(parts[3]));
                
             // 현재 날짜와 시간 가져오기
                LocalDateTime currentDateTime = LocalDateTime.now();
                order.setOrderTime(currentDateTime);
                
                orderController.regOrder(order);

                out.writeUTF("END");
                break;

            case "FOOD_ORDER_DETAIL":
                FoodMenuVO _FoodMenu = new FoodMenuVO();
                OrderDetailVO _Detail = new OrderDetailVO();
                // 음식 주문한 마지막 번호
                int _lastOrderId = (orderController.maxOrder())-1;
                _Detail.setOrderId(_lastOrderId); // 주문번호도 넣음

                // 음식 이름 및 갯수 설정
                _FoodMenu.setName(parts[1]); // 음식 이름
                _Detail.setCount(Integer.parseInt(parts[2])); // 음식 갯수

                // 음식 이름으로 단일 메뉴 번호를 가져옴
                String menuId = foodMenuController.findMenuIds(_FoodMenu);

                if (menuId != null) {
                    _Detail.setMenuId(menuId); // 가져온 menuId를 OrderDetailVO에 설정
                } else {
                    // 메뉴를 찾지 못한 경우 처리 로직
                    System.out.println("해당 음식 이름에 해당하는 메뉴를 찾을 수 없습니다.");
                }
                
                // 이후에 OrderDetailVO를 등록하는 로직 추가
                orderDetailController.regDetail(_Detail);
                
                out.writeUTF("END");
                break;
                
            case "TIME_PAY": // 결제방식 + 좌석 + 주문상태
                PayTimeVO payTime1 = new PayTimeVO();
                int nextTimeId = payTimeController.maxPayTime();
                payTime1.setTimeId(nextTimeId);                
                payTime1.setUserId((parts[1]));
                payTime1.setTimeNum(Integer.parseInt(parts[2]));
                
             // 현재 날짜와 시간 가져오기
                LocalDateTime currentDateTime31 = LocalDateTime.now();                
                payTime1.setPayTime(currentDateTime31);
                
                payTimeController.regPayTime(payTime1);

                out.writeUTF("END");
                break;
                
            case "CHECK_SEAT": // 결제방식 + 좌석 + 주문상태
            	SeatVO seat1 = new SeatVO();
                //seat1.setUserId(null);
                seat1.setSeatNum(Integer.parseInt(parts[1]));
                List<SeatVO> seatlist = seatController.pickSeat(seat1);
                String checkSeat = seatlist.get(0).getUserId();
                
                if (checkSeat == null) {
                    out.writeUTF("CHECK_OK");
                }
                
                
                out.writeUTF("END");
                break;

            	
        }
    }



	



	

	
}
    
// // reTime 리스트를 처리하는 메서드
//    private static void handleRemainTimes(List<RemainTimeVO> reTime) {
//
//    	for (RemainTimeVO time : reTime) {
//            // 예: 사용자 ID 및 남은 시간 출력
//            System.out.println("User ID: " + time.getUserId());
//            System.out.println("Remaining Time: " + time.getRemainTime());
//        }
//    }


//            
//            System.out.println("Registering member: " + member1); // 디버깅 출력
//            memberController.regMember(member1);
            
//            out.println("END");
//            break;
    		
    	// car 명령어
//        case "ADD_CAR":
            //CarVO car = new CarVO();
            //car.setCarNumber(parts[1]);
           // car.setCarName(parts[2]);
            //car.setCarColor(parts[3]);
            //car.setCarSize(Integer.parseInt(parts[4]));
            //car.setCarMaker(parts[5]);
            //new CarControllerImpl().regCarInfo(car);
            // out.println("END");
            // //out.println("Car added");
            // break;
//        case "GET_CARS":
            //CarVO searchCar = new CarVO();
            /* 
            if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
                searchCar.setCarNumber(parts[1]);
            }
            System.out.println("Searching for car with number: " + searchCar.getCarNumber());
            List<CarVO> cars = carController.listCarInfo(searchCar);
            for (CarVO c : cars) {
                out.println(c.getCarNumber() + "," + c.getCarName() + "," + c.getCarColor() + "," + c.getCarSize() + "," + c.getCarMaker());
            }
                */
            // out.println("END");
            // System.out.println("Processed GET_CARS command, sent " + cars.size() + " cars to client.");
            // break;
//        case "MOD_CAR":
            //CarVO modCar = new CarVO();
            //modCar.setCarNumber(parts[1]);
            //modCar.setCarName(parts[2]);
            //modCar.setCarColor(parts[3]);
           // modCar.setCarSize(Integer.parseInt(parts[4]));
           // modCar.setCarMaker(parts[5]);
            //carController.modCarInfo(modCar);
            // out.println("END");
            // //out.println("Car modified");
            // break;
//        case "REMOVE_CAR":
            //CarVO remCar = new CarVO();
            //remCar.setCarNumber(parts[1]);
           // carController.removeCarInfo(remCar);
           // out.println("END");
            //out.println("Car removed");
            // break;                    
            
        // member 명령어
        //case "ADD_MEM":
            //MemberVO member = new MemberVO();
           // member.setMemId(parts[1]);
           // member.setMemPassword(parts[2]);
           // member.setMemName(parts[3]);
          //  member.setMemAddress(parts[4]);
          //  member.setMemPhoneNum(parts[5]);
            
            //System.out.println("Registering member: " + member); // 디버깅 출력
            //memberController.regMember(member);
            
            // out.println("END");
            // break;
            
//        case "GET_MEMS":
           // MemberVO searchMember = new MemberVO();
            // if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
            //     searchMember.setMemName(parts[1]);
            // }
            // System.out.println("Searching for member with Name: " + searchMember.getMemName());
            // List<MemberVO> members = memberController.listMember(searchMember);
            // for (MemberVO m : members) {
            //     out.println(m.getMemId() + "," + m.getMemPassword() + "," + m.getMemName() + "," + m.getMemAddress() + "," + m.getMemPhoneNum());
            // }
            // out.println("END");
            // System.out.println("Processed GET_MEMS command, sent " + members.size() + " members to client.");
            // break;
//            
//        case "MOD_MEM":
            // MemberVO modMem = new MemberVO();
            // modMem.setMemId(parts[1]);
            // modMem.setMemPassword(parts[2]);
            // modMem.setMemName(parts[3]);
            // modMem.setMemAddress(parts[4]);
            // modMem.setMemPhoneNum(parts[5]);
            // memberController.modMember(modMem);
            // out.println("END");
            //out.println("Car modified");
            // break;
            
//        case "REMOVE_MEM":
            // MemberVO remMem = new MemberVO();
            // remMem.setMemId(parts[1]);
            // memberController.removeMember(remMem);
            // out.println("END");
            // //out.println("Car removed");
            // break;
            
//        default:
            // out.println("Invalid command");
            // break;
            
        // 예약 명령어
//        case "ADD_RES":
        	// System.out.println("잘나옴");
            // ResVO res = new ResVO();
            // res.setResNumber(parts[1]);
            // res.setResCarNumber(parts[2]);
            // res.setResDate(parts[3]);
            // res.setUseBeginDate(parts[4]);
            // res.setReturnDate(parts[5]);
            // res.setResUserId(parts[6]);
            // res.setResCarMoney(parts[7]);
            // res.setResInsure(parts[8]);
            
            // resController.regResInfo(res);
            
            // PayVO pay = new PayVO();
            // pay.setPayNumber(parts[1]);
            // pay.setPayResNumber(parts[1]);
            // pay.setPayCarNumber(parts[2]);
            // pay.setPayUserId(parts[6]);
            // pay.setPayMoney(Integer.parseInt(parts[7]));
            // pay.setPayCheck(Integer.parseInt("0")); // 0 : 결제 대기 하기위해서

            // payController.regPayInfo(pay);
            // out.println("END");
            // //out.println("Res added");
            //break;
            
//        case "GET_RES":
            // ResVO searchRes = new ResVO();
            // if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
            // 	searchRes.setResNumber(parts[1]);
            // }
            // System.out.println("Searching for member with Name: " + searchRes.getResNumber());
            // List<ResVO> res1 = resController.listResInfo(searchRes);
            // for (ResVO r : res1) {
            //     out.println(r.getResNumber() + "," + r.getResCarNumber() + "," + r.getResDate() + "," + r.getUseBeginDate() + "," + r.getReturnDate()+ "," + r.getResUserId()+ "," + r.getResCarMoney() + "," + r.getResInsure());
            // }
            // out.println("END");
            // System.out.println("Processed GET_RES command, sent " + res1.size() + " res to client.");
            // break;
            
//        case "MOD_RES":
        	// ResVO modRes = new ResVO();
            // modRes.setResNumber(parts[1]);
            // modRes.setResCarNumber(parts[2]);
            // modRes.setResDate(parts[3]);
            // modRes.setUseBeginDate(parts[4]);
            // modRes.setReturnDate(parts[5]);
            // modRes.setResUserId(parts[6]);
            // modRes.setResCarMoney(parts[7]);
            // modRes.setResInsure(parts[8]);
            // resController.modResInfo(modRes);
            // out.println("END");
            // //out.println("Car modified");
            // break;
            
//        case "MOD_RES_INSURE":
        	// ResVO modInsure = new ResVO();
        	// modInsure.setResNumber(parts[1]);
        	// modInsure.setResInsure(parts[2]);
            // resController.modInsureInfo(modInsure);
            // out.println("END");
            // //out.println("Car modified");
            // break;
            
//        case "REMOVE_RES":
        	// PayVO remPay = new PayVO();
        	// remPay.setPayNumber(parts[1]);
        	// payController.cancelPayInfo(remPay);
        	
        	// ResVO remRes = new ResVO();
            // remRes.setResNumber(parts[1]);
            // resController.cancelResInfo(remRes);
            // out.println("END");
            // break;
            
//        case "GET_PAY":
        	// PayVO searchPay = new PayVO();
            // if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
            // 	searchPay.setPayNumber(parts[1]);
            // }
            // System.out.println("Searching for pay with Number: " + searchPay.getPayNumber());
            // List<PayVO> pay1 = payController.listPayInfo(searchPay);
            // for (PayVO p : pay1) {
            //     out.println(p.getPayNumber() + "," + p.getPayResNumber() + "," + p.getPayCarNumber() + "," + p.getPayUserId() + "," + p.getPayMoney()+ "," + p.getPayCheck());
            // }	
            // out.println("END");
            // System.out.println("Processed GET_PAY command, sent " + pay1.size() + " pay to client.");
            // break;
            
//        case "CONTROLL_PAY":
        	// System.out.println("확인됬어~");
        	// PayVO ControllPay = new PayVO();
        	// ControllPay.setPayNumber(parts[1]);
        	// //CompPay.setPayResNumber(parts[2]);
        	// ControllPay.setPayCarNumber(parts[2]);
        	// ControllPay.setPayUserId(parts[3]);
        	// ControllPay.setPayMoney(Integer.parseInt(parts[4]));
        	// ControllPay.setPayCheck(Integer.parseInt(parts[5]));
        	
        	// payController.modPayInfo(ControllPay);
        	// out.println("END");
        	// break;

//        case "GET_COMPPAY":
        	// PayVO compPay = new PayVO();
            // if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
            // 	compPay.setPayNumber(parts[1]);
            // }
            // System.out.println("Searching for pay with Number: " + compPay.getPayNumber());
            // List<PayVO> pay2 = payController.listCompPayInfo(compPay);
            // for (PayVO p : pay2) {
            //     out.println(p.getPayNumber() + "," + p.getPayResNumber() + "," + p.getPayCarNumber() + "," + p.getPayUserId() + "," + p.getPayMoney()+ "," + p.getPayCheck());
            // }	
            // out.println("END");
            // System.out.println("Processed GET_COMPPAY command, sent " + pay2.size() + " pay to client.");
            // break;
            
//        case "GET_CANCLEPAY":
        	// PayVO canclePay = new PayVO();
            // if (parts.length > 1 && parts[1] != null && !parts[1].isEmpty()) {
            // 	canclePay.setPayNumber(parts[1]);
            // }
            // System.out.println("Searching for pay with Number: " + canclePay.getPayNumber());
            // List<PayVO> pay3 = payController.listCanclePayInfo(canclePay);
            // for (PayVO p : pay3) {
            //     out.println(p.getPayNumber() + "," + p.getPayResNumber() + "," + p.getPayCarNumber() + "," + p.getPayUserId() + "," + p.getPayMoney()+ "," + p.getPayCheck());
            // }	
            // out.println("END");
            // System.out.println("Processed GET_COMPPAY command, sent " + pay3.size() + " pay to client.");
            // break;
        	



    
