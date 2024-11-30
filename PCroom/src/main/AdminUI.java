package main;

import javax.swing.*;

import admin.window.TimeMenuManage;
import admin.window.foodManage;
import admin.window.orderManage;
import admin.window.saleStatus;
import admin.window.userInfo;
import admin.window.userManage;
import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;
import seat.vo.SeatVO;
import chatNetwork.Server;


public class AdminUI { // AdminUI 클래스를 선언
    private JFrame frame; // JFrame 객체를 선언
    private DefaultListModel<String> userListModel; // DefaultListModel<String> 객체를 선언
    private JButton[] seatButtons; // 좌석 버튼 배열
    static SeatController seatController = new SeatControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();
    static MemberController memberController = new MemberControllerImpl();
    private static final int TOTAL_SEATS = 24; // 자리 수
    //private Map<String, ClientReceiver> clients; // 클라이언트 목록을 저장할 변수
    private Server server;  // Server 객체를 클래스 변수로 선언
    private JLabel messageLabel;
    private String lastProcessedRequest = null; // 마지막으로 처리한 메시지를 저장하는 변수
 // 클래스 멤버 변수로 선언
    private JTextArea messageArea;

    

    public static void main(String[] args) { // main 메서드를 선언
        EventQueue.invokeLater(() -> { // 이벤트 큐에서 새 작업을 실행
            try {
                //AdminUI window = new AdminUI(clients); // AdminUI 객체를 생성
                //window.frame.setVisible(true); // 프레임을 보이도록 설정
            } catch (Exception e) { // 예외가 발생할 경우
                e.printStackTrace(); // 예외 스택 트레이스를 출력
            }
        });
    }

    public AdminUI(Server server) { // AdminUI 생성자
    	//System.out.println("로그인 후 잡힌 것들 : " + clients);
    	this.server = server;
        initialize(); // 초기화 메서드를 호출
        startRefreshTimer(messageArea); // 새로 고침 타이머를 시작하는 메서드를 호출
    }

    private void initialize() { // 초기화 메서드
        frame = new JFrame(); // JFrame 객체를 생성
        frame.setTitle("좌석 관리");
        frame.setBounds(100, 100, 1000, 600); // 프레임의 크기와 위치를 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 동작을 설정
        frame.setLocationRelativeTo(null);

        // 레이어드 페인 생성
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600)); // 레이어드 페인의 크기 설정

        // 바깥 패널 생성 및 배경색 설정
        JPanel outerPanel = new JPanel();
        outerPanel.setBackground(Color.DARK_GRAY); // 진한 회색으로 설정
        outerPanel.setBounds(0, 0, 1000, 600); // 바깥 패널의 크기와 위치 설정

        // 내부 패널 생성
        JPanel panel = new JPanel();
        panel.setLayout(null); // null 레이아웃 설정
        panel.setBounds(250, 20, 720, 525); // 내부 패널의 크기와 위치 설정

        userListModel = new DefaultListModel<>(); // DefaultListModel 객체를 생성
        JList<String> userList = new JList<>(userListModel); // JList 객체를 생성
        userList.setBounds(950, 0, 200, 800); // JList의 크기와 위치를 설정 (100씩 이동)
        panel.add(userList); // 패널에 추가

        seatButtons = new JButton[TOTAL_SEATS]; // 좌석 버튼 배열 초기화

     // 각 좌석의 위치를 지정하는 배열 (좌표 설정)
        int[][] seatPositions = {
            {10, 20}, {110, 20}, {210, 20}, {310, 20},
            {10, 150}, {110, 150}, {210, 150}, {310, 150},
            {10, 250}, {110, 250}, {210, 250}, {310, 250},
            {10, 380}, {110, 380}, {210, 380}, {310, 380},
            {510, 50}, {610, 50},
            {510, 150}, {610, 150},
            {510, 250}, {610, 250},
            {510, 350}, {610, 350},
            {510, 450}, {610, 450},
            {510, 550}, {610, 550}
        };

        for (int i = 0; i < TOTAL_SEATS; i++) {
            seatButtons[i] = createSeatButton(i + 1);
            seatButtons[i].setBounds(seatPositions[i][0], seatPositions[i][1], 100, 100); // 위치와 크기 설정
            panel.add(seatButtons[i]);
        }

        // 수평 구분선 추가 (40씩 이동)
        JSeparator separator2 = new JSeparator();
        separator2.setBounds(10, 240, 400, 8); // X 좌표를 40씩 이동
        panel.add(separator2);

        // 세로 구분선 추가 (40씩 이동)
        JSeparator separator3 = new JSeparator(JSeparator.VERTICAL);
        separator3.setBounds(410, 450, 8, 100); // X 좌표를 40씩 이동
        panel.add(separator3);

        JSeparator separator4 = new JSeparator(JSeparator.VERTICAL);
        separator4.setBounds(410, 20, 8, 300); // X 좌표를 40씩 이동
        panel.add(separator4);

        JSeparator separator5 = new JSeparator();
        separator5.setBounds(510, 140, 200, 8); // X 좌표를 40씩 이동
        panel.add(separator5);

        JSeparator separator6 = new JSeparator();
        separator6.setBounds(510, 240, 200, 8); // X 좌표를 40씩 이동
        panel.add(separator6);

        JSeparator separator7 = new JSeparator();
        separator7.setBounds(510, 340, 200, 8); // X 좌표를 40씩 이동
        panel.add(separator7);

        JSeparator separator8 = new JSeparator();
        separator8.setBounds(510, 440, 200, 8); // X 좌표를 40씩 이동
        panel.add(separator8);

        
        // 레이어드 페인에 패널 추가
        layeredPane.add(outerPanel, Integer.valueOf(0)); // 바깥 패널을 낮은 레이어에 추가
        layeredPane.add(panel, Integer.valueOf(1)); // 내부 패널을 높은 레이어에 추가

        frame.getContentPane().add(layeredPane); // 프레임에 레이어드 페인 추가
        refreshSeatButtons(); // 초기 상태 갱신

     // 메시지 창 패널 생성
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(null); // null 레이아웃 설정
        messagePanel.setBounds(20, 450, 200, 100); // 왼쪽 패널 하단에 위치 설정
        messagePanel.setBackground(Color.LIGHT_GRAY); // 테스트용 배경색 추가 (필요 시 제거 가능)

        // 메시지 창 설정
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(Color.WHITE);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("맑은 고딕", Font.PLAIN, 12)); // 폰트 크기 축소

        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // 테두리 추가
        messageScrollPane.setBounds(0, 0, 200, 100); // 메시지 스크롤 창 크기 설정

        // 메시지 창을 메시지 패널에 추가
        messagePanel.add(messageScrollPane);


        // 메뉴 패널 생성
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1));
        leftPanel.setBounds(20, 20, 200, 400);
        leftPanel.setBackground(new Color(240, 240, 240)); // 패널 배경색 변경
        
     // 제목 JLabel 추가
        JLabel titleLabel = new JLabel("서원 PC방", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 제목 폰트 설정
        titleLabel.setForeground(Color.BLACK); // 제목 글자 색상 설정
        leftPanel.add(titleLabel); // 제목을 패널에 추가

        // 메뉴 버튼 추가 및 스타일링
        String[] buttonLabels = {"회원 관리", "음식 메뉴 관리", "주문 관리", "요금제 관리", "매출 현황"};
        JButton[] buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBackground(new Color(255, 255, 255)); // 버튼 배경색
            buttons[i].setFont(new Font("맑은 고딕", Font.BOLD, 14)); // 폰트 스타일 변경
            buttons[i].setFocusPainted(false); // 포커스 시 테두리 제거
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // 테두리 추가
            buttons[i].setPreferredSize(new Dimension(180, 30)); // 버튼 크기 설정
            leftPanel.add(buttons[i]);
         // 각 버튼에 리스너 추가
            final int index = i; // final 변수로 람다에서 사용하기 위함
            buttons[i].addActionListener(e -> {
                switch (index) {
                    case 0:
                        // 회원 관리 버튼 클릭 시 동작
                        //System.out.println("회원 관리 버튼 클릭됨");
                    	userManage uM = new userManage();
                    	uM.showUI();
                        break;
                    case 1:
                        // 음식 메뉴 관리 버튼 클릭 시 동작
                        //System.out.println("음식 메뉴 관리 버튼 클릭됨");
                    	foodManage fM = new foodManage();
                    	fM.showUI();
                        break;
                    case 2:
                        // 주문 관리 버튼 클릭 시 동작
                        //System.out.println("주문 관리 버튼 클릭됨");
                    	orderManage oM = new orderManage();
                    	oM.showUI();
                        break;
                    case 3:
                        // 요금제 관리 버튼 클릭 시 동작
//                        System.out.println("요금제 관리 버튼 클릭됨");
                    	TimeMenuManage TMM = new TimeMenuManage();
                        TMM.setVisible(true);
                        break;
                    case 4:
                        // 매출 현황 버튼 클릭 시 동작
                        System.out.println("매출 현황 버튼 클릭됨");
                        saleStatus SS = new saleStatus();
                        SS.setVisible(true);
                        break;
                    default:
                        break;
                }
            });
            
        }

        // 패널을 레이어드 페인에 추가
        layeredPane.add(leftPanel, Integer.valueOf(2)); // 더 높은 레이어에 추가
        layeredPane.add(messagePanel, Integer.valueOf(3)); // 메뉴 패널보다 낮은 레이어에 추가
    }

    private JButton createSeatButton(int seatNumber) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(80, 60)); // 버튼 크기 설정

        // 이미지를 로드하고 JLabel을 사용하여 이미지를 버튼에 추가
        String imagePath = "C:/Users/user/Desktop/001.png"; // 파일 경로 수정
        ImageIcon icon = new ImageIcon(imagePath);
        
        // 이미지의 크기를 JButton의 크기에 맞게 조정
        Image img = icon.getImage().getScaledInstance(button.getPreferredSize().width, button.getPreferredSize().height, Image.SCALE_SMOOTH);
        
        JLabel label = new JLabel(new ImageIcon(img));
        
        // 줄바꿈을 추가한 텍스트 설정, 글씨 크기와 위치를 조정
        String labelText = "<html><div style='text-align: center; font-size: 9px;'>자리 " + seatNumber + "</div></html>";
        label.setText(labelText);  // 텍스트를 JLabel에 설정
        
        label.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트를 가운데 정렬
        label.setVerticalTextPosition(SwingConstants.CENTER);  // 텍스트를 이미지의 중앙에 위치
        label.setFont(new Font("나눔고딕", Font.PLAIN, 10));  // 폰트 크기 설정
        label.setForeground(Color.BLACK);  // 텍스트 색상 설정

        button.setLayout(new BorderLayout());
        button.add(label);

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        
     // 버튼 클릭 시 사용자 정보 화면으로 이동
        button.addActionListener(e -> {
            // 현재 창을 유지하면서 사용자 정보 창을 표시
        	//seatNumber 
        	SeatVO seat = new SeatVO();
        	//seat.setUserId(parts[1]);
        	seat.setSeatNum(seatNumber);
        	List<SeatVO> seats = seatController.pickSeat(seat);
            String id = null;
        	
        	if (!seats.isEmpty()) {
        		SeatVO firstSeat = seats.get(0); // 첫 번째 요소를 가져옴
                // 필요한 정보 사용 예시
            	id = firstSeat.getUserId();
            }
        	
            //String id = seat.getUserId();
            //System.out.println(id);
            
            // 아이디, 사용금액, 남은시간이 안나오는 것 같음
            
            // 아이디 구하고
            RemainTimeVO remainTime1 = new RemainTimeVO();
            remainTime1.setUserId(id);
            List<RemainTimeVO> reTime = remainTimeController.listRemainTime(remainTime1);
            
            // 남은 시간 얻어두고
            int remainTime2 = 0;
            // 사용 금액도 얻어두고
            int useMoney2 = 0;
            if (!reTime.isEmpty()) {
            	RemainTimeVO firstTime = reTime.get(0); // 첫 번째 요소를 가져옴
                // 필요한 정보 사용 예시
            	remainTime2 = firstTime.getRemainTime();
            	useMoney2 = firstTime.getUseMoney();
            }
            System.out.println(remainTime2);
            
            // 멤버의 정보를 얻어야함
            MemberVO Member = new MemberVO();
            Member.setId(id);
            List<MemberVO> members = memberController.listMember(Member);
            
            String Name = null;
            String Gender = null;
         // 리스트의 첫 번째 요소를 가져오기 (리스트가 비어있지 않다고 가정)
            if (!members.isEmpty()) {
                MemberVO firstMember = members.get(0); // 첫 번째 요소를 가져옴
                // 필요한 정보 사용 예시
                Name = firstMember.getName();
                Gender = firstMember.getGender();
                
            }
            
//            userInfo uInfo = new userInfo("김범진", "남", "ptcart4033", "5000원", "00:30");
            userInfo uInfo = new userInfo(seatNumber, Name, Gender, id, useMoney2, remainTime2, server);
            uInfo.setVisible(true);
        });
        
        return button;
    }
    
 // 안내창을 표시하는 메서드
    private void showSeatInfo(int seatNumber) {
        JOptionPane.showMessageDialog(frame, "자리 " + seatNumber + "의 정보입니다.", "좌석 정보", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshSeatButtons() { // 좌석 버튼 상태를 새로 고침하는 메서드
        try {
            SeatVO seat = new SeatVO();
            List<SeatVO> seatList = seatController.listSeat(seat); // 각 좌석 정보를 가져옴

            for (int i = 0; i < seatList.size(); i++) { // seatList의 크기만큼 반복
                SeatVO currentSeat = seatList.get(i); // 현재 좌석 정보를 가져옴
                String text = "<html><div style='text-align: center; font-size: 9px;'>자리 " + (i + 1);

                JLabel label = (JLabel) seatButtons[i].getComponent(0);

                if (currentSeat.getUserId() != null) {
                    seatButtons[i].setEnabled(true); // 회원이 있으면 버튼 활성화
                    text += " (" + currentSeat.getUserId() + ")</div></html>";
                    
                    // 이미지와 텍스트를 원래 상태로
                    ImageIcon icon = new ImageIcon("C:/Users/user/Desktop/창프2/002.png"); // 파일 경로 수정
                    Image img = icon.getImage().getScaledInstance(seatButtons[i].getPreferredSize().width, seatButtons[i].getPreferredSize().height, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                    label.setForeground(Color.BLACK); // 글씨 색상 원래대로
                } else {
                    seatButtons[i].setEnabled(false); // 회원이 없으면 버튼 비활성화
                    text += "</div></html>";
                    
                    // 이미지와 텍스트를 흐리게 처리
                    ImageIcon icon = new ImageIcon("C:/Users/user/Desktop/창프2/001.png"); // 파일 경로 수정
                    Image img = icon.getImage().getScaledInstance(seatButtons[i].getPreferredSize().width, seatButtons[i].getPreferredSize().height, Image.SCALE_SMOOTH);
                    Image grayImg = createGrayImage(img); // 이미지 흐리게
                    label.setIcon(new ImageIcon(grayImg));
                    label.setForeground(new Color(128, 128, 128)); // 글씨 색상을 흐리게
                }

                label.setText(text); // 텍스트 설정
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 스택 트레이스를 출력
        }
    }


    private Image createGrayImage(Image img) {
        // 이미지를 흐리게 변환하는 메서드
        ImageIcon icon = new ImageIcon(img);
        Image grayImg = new ImageIcon(
                GrayFilter.createDisabledImage(icon.getImage())
        ).getImage();
        return grayImg;
    }



    private void startRefreshTimer(JTextArea messageArea) { 
        Timer timer = new Timer(true); // Timer 객체 생성 (데몬 스레드로 실행)
        timer.scheduleAtFixedRate(new TimerTask() { // 타이머 작업을 일정 주기로 실행
            @Override
            public void run() { // 타이머 작업 내용
                SwingUtilities.invokeLater(() -> {
                    refreshSeatButtons(); // 기존 새로 고침 작업

                    // 최신 메시지 가져오기 및 파싱
                    String latestRequest = Server.getLatestRequest(); // 최신 메시지 가져오기

                    // 새 메시지인지 확인 (이전 메시지와 다를 경우만 처리)
                    if (latestRequest != null && !latestRequest.equals(lastProcessedRequest) && latestRequest.startsWith("MSG|")) {
                        String[] parts = latestRequest.split("\\|", 3); // "|" 기준으로 분리 (최대 3부분)
                        if (parts.length == 3) { // 메시지가 올바른 형식인지 확인
                            String seatNumber = parts[1]; // 좌석번호 추출
                            String messageContent = parts[2]; // 메시지 내용 추출
                            String logMessage = seatNumber + "번 손님이 메세지를 보냈습니다 : " + messageContent + "\n";
                            
                            System.out.println(logMessage.trim()); // 콘솔에 출력
                            // 메시지 라벨에 내용 추가
                            messageArea.append(logMessage); // 기존 내용에 추가
                            
                            // 스크롤 자동으로 가장 아래로 이동
                            messageArea.setCaretPosition(messageArea.getDocument().getLength());
                        } else {
                            messageArea.append("메세지 형식이 잘못되었습니다.\n");
                        }
                        lastProcessedRequest = latestRequest; // 메시지 저장
                    } else if (latestRequest == null) {
                        if (lastProcessedRequest == null) { // 마지막 메시지도 null인 경우에만 업데이트
                            //messageArea.append("메세지가 없습니다.\n");
                        }
                    } else if (!latestRequest.startsWith("MSG|")) {
                        if (!latestRequest.equals(lastProcessedRequest)) { // 이전과 다른 경우에만 업데이트
                            //smessageArea.append("알 수 없는 메시지 형식입니다.\n");
                            lastProcessedRequest = latestRequest; // 메시지 저장
                        }
                    }
                }); // 이벤트 큐에서 실행
            }
        }, 0, 3000); // 3초마다 갱신
    }

    private static void initializeUI() {
        // 나눔고딕 폰트 설정 및 Nimbus Look and Feel 적용
        try {
            // Look and Feel 설정
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // 나눔고딕 폰트 설정
            Font font = new Font("나눔고딕", Font.PLAIN, 14);
            UIManager.put("OptionPane.messageFont", font);
            UIManager.put("OptionPane.buttonFont", font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    
 // 메뉴 항목을 추가하는 메서드
    private void addMenuItem(JPanel panel, String text, String iconPath) {
        JButton menuButton = new JButton(text); // 메뉴 버튼 생성
        menuButton.setIcon(new ImageIcon(iconPath)); // 아이콘 추가
        menuButton.setHorizontalTextPosition(SwingConstants.RIGHT); // 텍스트를 오른쪽에 배치
        menuButton.setBackground(Color.WHITE); // 버튼 배경색 설정
        panel.add(menuButton); // 메뉴 패널에 버튼 추가
    }
    

}
