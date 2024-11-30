package admin.window;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import admin.vo.AdminVO;
import chatNetwork.Server;
import chatNetwork.Server.ClientReceiver;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;
import seat.vo.SeatVO;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userInfo {

    private JFrame frame;
    private JTextField nameField;
    private JTextField genderField;
    private JTextField idField;
    private JTextField amountField;
    private JTextField remainingTimeField;
    private int seatNum; // 좌석 번호
    private Timer timer; // 타이머 추가
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();
    static SeatController seatController = new SeatControllerImpl();
    private int remainTime2 = 0; // 클래스 필드
    private int useMoney2 = 0; // 클래스 필드\
    
    private Server server;  // Server 객체를 클래스 변수로 선언


    public userInfo(int seatNum, String name, String gender, String id, int amount, int remainingTime, Server server) {
        this.seatNum = seatNum; // 좌석 번호 저장
        this.server = server;
        initialize(seatNum, name, gender, id, amount, remainingTime);
    }

    private void initialize(int seatNum, String name, String gender, String id, int amount, int remainingTime) {
        // Create frame
        frame = new JFrame();
        frame.setTitle("PC " + seatNum + "번");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create panel
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 414, 200);
        panel.setBackground(new Color(220, 220, 220)); // Panel 배경색
        panel.setBorder(new LineBorder(Color.BLACK)); // 패널 테두리 추가
        panel.setLayout(null); // null layout으로 설정
        frame.getContentPane().add(panel);

        // 이름 라벨 및 필드
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setBounds(60, 10, 50, 25); // y 위치 조정
        nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        panel.add(nameLabel);

        nameField = new JTextField(10);
        nameField.setEditable(false); // 입력 방지
        nameField.setBackground(new Color(220, 220, 220)); // 필드 배경색
        nameField.setBorder(null); // 테두리 제거
        nameField.setBounds(110, 10, 150, 25); // y 위치 조정
        nameField.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        nameField.setText(name); // 이름 필드에 값 설정
        panel.add(nameField);

        // 성별 라벨 및 필드
        JLabel genderLabel = new JLabel("성별:");
        genderLabel.setBounds(60, 45, 50, 25); // y 위치 조정
        genderLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        panel.add(genderLabel);

        genderField = new JTextField(10);
        genderField.setEditable(false); // 입력 방지
        genderField.setBackground(new Color(220, 220, 220)); // 필드 배경색
        genderField.setBorder(null); // 테두리 제거
        genderField.setBounds(110, 45, 150, 25); // y 위치 조정
        genderField.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        genderField.setText(gender); // 성별 필드에 값 설정
        panel.add(genderField);

        // 아이디 라벨 및 필드
        JLabel idLabel = new JLabel("아이디:");
        idLabel.setBounds(48, 80, 50, 25); // y 위치 조정
        idLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        panel.add(idLabel);

        idField = new JTextField(10);
        idField.setEditable(false); // 입력 방지
        idField.setBackground(new Color(220, 220, 220)); // 필드 배경색
        idField.setBorder(null); // 테두리 제거
        idField.setBounds(110, 80, 150, 25); // y 위치 조정
        idField.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        idField.setText(id); // 아이디 필드에 값 설정
        panel.add(idField);

        // 사용 금액 라벨 및 필드
        JLabel amountLabel = new JLabel("사용 금액:");
        amountLabel.setBounds(30, 115, 80, 25); // y 위치 조정
        amountLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        panel.add(amountLabel);

        amountField = new JTextField(10);
        amountField.setEditable(false); // 입력 방지
        amountField.setBackground(new Color(220, 220, 220)); // 필드 배경색
        amountField.setBorder(null); // 테두리 제거
        amountField.setBounds(110, 115, 150, 25); // y 위치 조정
        amountField.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        amountField.setText(String.valueOf(amount)); // 사용 금액 필드에 값 설정
        panel.add(amountField);

        // 남은 시간 라벨 및 필드
        JLabel remainingTimeLabel = new JLabel("남은 시간:");
        remainingTimeLabel.setBounds(30, 150, 80, 25); // y 위치 조정
        remainingTimeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        panel.add(remainingTimeLabel);

        remainingTimeField = new JTextField(10);
        remainingTimeField.setEditable(false); // 입력 방지
        remainingTimeField.setBackground(new Color(220, 220, 220)); // 필드 배경색
        remainingTimeField.setBorder(null); // 테두리 제거
        remainingTimeField.setBounds(110, 150, 150, 25); // y 위치 조정
        remainingTimeField.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 글꼴 설정
        remainingTimeField.setText(String.valueOf(remainingTime)); // 남은 시간 필드에 값 설정
        panel.add(remainingTimeField);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null); // 레이아웃을 null로 설정하여 자유롭게 배치 가능
        buttonPanel.setBounds(10, 220, 414, 40);
        frame.getContentPane().add(buttonPanel);
        buttonPanel.setBackground(new Color(240, 240, 240)); // 버튼 패널 배경색

        // 버튼 추가 및 스타일 적용
        JButton endUsageButton = new JButton("사용 종료");
        styleButton(endUsageButton); // 버튼 스타일 적용
        endUsageButton.setBackground(Color.RED); // 사용 종료 버튼 배경색 빨간색
        endUsageButton.setBounds(12, 0, 120, 30); // 위치와 크기 설정
        buttonPanel.add(endUsageButton);
        endUsageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SeatVO seat = new SeatVO();
            	seat.setUserId(null);
            	seat.setSeatNum(seatNum);
            	seatController.modSeat(seat);
            	// 메시지 박스 출력
                JOptionPane.showMessageDialog(frame, "해당 PC가 종료되었습니다.");
            	frame.dispose();
            }
        });
        

        JButton addTimeButton = new JButton("시간 추가");
        styleButton(addTimeButton); // 버튼 스타일 적용
        addTimeButton.setBackground(new Color(70, 130, 180)); // 시간 추가 버튼 배경색 파란색
        addTimeButton.setBounds(147, 0, 120, 30); // 위치와 크기 설정
        buttonPanel.add(addTimeButton);
        addTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	AdminAddTime AAD = new AdminAddTime(id, remainingTime);
            	AAD.setVisible(true);
            	frame.dispose();
            	
            }
        });


        JButton messageButton = new JButton("메세지");
        styleButton(messageButton); // 버튼 스타일 적용
        messageButton.setBackground(Color.GRAY); // 메세지 버튼 배경색 회색
        messageButton.setBounds(282, 0, 120, 30); // 위치와 크기 설정
        buttonPanel.add(messageButton);
        
        messageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "메세지" 버튼 클릭 시, 해당 좌석 번호와 연결된 클라이언트와 메시지 화면 연결
                // 1대1 메시지 화면을 표시
                ServerMessage serverMessage = new ServerMessage(seatNum, server); // 좌석 번호 전달
                serverMessage.setVisible(true);  // ServerMessage 화면을 표시
                frame.dispose();  // 현재 창을 닫음
            }
        });

        // 타이머 설정 (5초마다 DB에서 값을 갱신)
        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDataFromController(id); // 컨트롤러를 통해 데이터를 가져와 갱신
            }
        });
    }

    // 컨트롤러를 통해 데이터를 가져와 UI를 업데이트하는 메서드
    private void updateDataFromController(String id) {
        try {
            // 컨트롤러에서 데이터를 가져옴
        	// 아이디 구하고
            RemainTimeVO remainTime1 = new RemainTimeVO();
            remainTime1.setUserId(id);
            List<RemainTimeVO> reTime = remainTimeController.listRemainTime(remainTime1);
            
            if (!reTime.isEmpty()) {
            	RemainTimeVO firstTime = reTime.get(0); // 첫 번째 요소를 가져옴
                // 필요한 정보 사용 예시
            	remainTime2 = firstTime.getRemainTime();
            	useMoney2 = firstTime.getUseMoney();
            }
            
            // UI 업데이트 (이벤트 디스패치 스레드에서 실행)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    amountField.setText(String.valueOf(useMoney2));
                    remainingTimeField.setText(formatTime(remainTime2)); // "시:분:초" 형식으로 변환 후 표시
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        if (visible) {
            timer.start(); // 창이 보일 때 타이머 시작
        } else {
            timer.stop(); // 창이 숨겨지거나 닫힐 때 타이머 중지
        }
        frame.setVisible(visible);
    }

    // 버튼 스타일 설정 메서드 추가
    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE); // 글자색
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14)); // 글꼴 설정
        button.setBorder(new LineBorder(Color.DARK_GRAY, 1)); // 테두리 설정
        button.setFocusPainted(false); // 포커스 효과 제거
        button.setOpaque(true); // 불투명하게 설정
        button.setContentAreaFilled(true); // 투명 설정을 없앰
    }
    
 // 초를 "시:분:초" 형식으로 변환하는 메서드
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs); // "시:분:초" 형식으로 반환
    }
    
}
