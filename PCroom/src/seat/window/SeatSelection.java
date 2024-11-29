package seat.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
//import network.Client;
import chatNetwork.Client;
import remainTime.window.PCroomMain;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;
import seat.vo.SeatVO;

public class SeatSelection {

    private JFrame frame;
    private static final int TOTAL_SEATS = 24; // 자리 수
    private String UserId; // 사용자 ID를 저장할 변수
    private Client client; // Client 객체 선언
    private JButton[] seatButtons = new JButton[TOTAL_SEATS]; // 좌석 버튼 배열
    private SeatController seatController = new SeatControllerImpl(); // SeatController 객체
    private String imagePath = "C:/Users/user/Desktop/창프2/002.png"; // 이미지 경로
    private String clientId;  // 클라이언트 ID를 저장할 변수 추가

    public SeatSelection(String UserId, Client client, String clientId) {
        this.UserId = UserId;
        this.client = client;
        this.clientId = clientId;
        initialize();
        refreshSeatButtons(); // 초기 좌석 상태 갱신
        startRefreshTimer(); // 새로 고침 타이머 시작
    }

    private void initialize() {
        initializeUI(); // UI 초기화 메서드 호출
        frame = new JFrame();
        frame.setTitle("자리 선택");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); // null 레이아웃 설정

        // 각 좌석의 위치를 지정하는 배열 (좌표 설정)
        int[][] seatPositions = {
            {50, 20}, {150, 20}, {250, 20}, {350, 20},
            {50, 150}, {150, 150}, {250, 150}, {350, 150},
            {50, 250}, {150, 250}, {250, 250}, {350, 250},
            {50, 380}, {150, 380}, {250, 380}, {350, 380},
            {550, 50}, {650, 50},
            {550, 150}, {650, 150},
            {550, 250}, {650, 250},
            {550, 350}, {650, 350},
            {550, 450}, {650, 450},
            {550, 550}, {650, 550}
        };

        for (int i = 0; i < TOTAL_SEATS; i++) {
            seatButtons[i] = createSeatButton(i + 1);
            seatButtons[i].setBounds(seatPositions[i][0], seatPositions[i][1], 100, 100); // 위치와 크기 설정
            panel.add(seatButtons[i]);
        }

        // 수평 구분선 추가
        JSeparator separator2 = new JSeparator();
        separator2.setBounds(50, 240, 400, 4);
        panel.add(separator2);

        // 세로 구분선 추가
        JSeparator separator3 = new JSeparator(JSeparator.VERTICAL);
        separator3.setBounds(530, 450, 4, 100);
        panel.add(separator3);
        
        JSeparator separator4 = new JSeparator(JSeparator.VERTICAL);
        separator4.setBounds(530, 50, 4, 300);
        panel.add(separator4);

        JSeparator separator5 = new JSeparator();
        separator5.setBounds(550, 140, 200, 4);
        panel.add(separator5);

        JSeparator separator6 = new JSeparator();
        separator6.setBounds(550, 240, 200, 4);
        panel.add(separator6);
        
        JSeparator separator7 = new JSeparator();
        separator7.setBounds(550, 340, 200, 4);
        panel.add(separator7);
        
        JSeparator separator8 = new JSeparator();
        separator8.setBounds(550, 440, 200, 4);
        panel.add(separator8);

        frame.getContentPane().add(panel);
    }

    private JButton createSeatButton(int seatNumber) {
        // 동일한 아이디가 다른 좌석에 등록되어 있는지 확인하고 해제
        releasePreviousSeat(UserId);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(80, 70)); // 버튼 크기 설정

        // 이미지를 로드하고 JLabel을 사용하여 이미지를 버튼에 추가
        ImageIcon icon = new ImageIcon(imagePath);  // 이미지 경로를 사용하여 ImageIcon 객체 생성
        Image img = icon.getImage().getScaledInstance(button.getPreferredSize().width, button.getPreferredSize().height, Image.SCALE_SMOOTH);  // 이미지 크기 조정
        JLabel label = new JLabel(new ImageIcon(img));  // 이미지를 JLabel에 설정

        // 줄바꿈을 추가한 텍스트 설정, 글씨 크기와 위치를 조정
        String labelText = "<html><div style='text-align: center; font-size: 9px;'>자리 " + seatNumber + "<br>Additional Text</div></html>";
        label.setText(labelText);  // 텍스트를 JLabel에 설정
        
        label.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트를 가운데 정렬
        label.setVerticalTextPosition(SwingConstants.CENTER);  // 텍스트를 이미지의 중앙에 위치
        label.setFont(new Font("나눔고딕", Font.PLAIN, 10));  // 폰트 크기 설정
        label.setForeground(Color.BLACK);  // 텍스트 색상 설정

     
        
        button.setLayout(new BorderLayout());  // 버튼에 BorderLayout 설정
        button.add(label);  // 버튼에 JLabel 추가

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);

        // 버튼 클릭 이벤트 처리
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("자리 선택 완료: " + seatNumber + "번 자리");
                JOptionPane.showMessageDialog(frame, 
                        seatNumber + "번 자리를 선택하셨습니다.", 
                        "자리 선택 완료", 
                        JOptionPane.INFORMATION_MESSAGE);     

                String response = client.sendCommand("SEAT_MEM," + UserId + "," + seatNumber + "," + clientId);
                refreshSeatButtons(); // 좌석 상태 갱신

                String[] responseParts = response.trim().split(",");
                if (responseParts[0].equals("SEAT_SELECTED")) {
                    int initialRemainingTime = Integer.parseInt(responseParts[1]);
                    // PCroomMain 화면으로 전환
                    EventQueue.invokeLater(() -> {
                        try {
                            PCroomMain mainWindow = new PCroomMain(UserId, seatNumber, initialRemainingTime, client, clientId);
                            mainWindow.setVisible(true);
                            frame.dispose(); // 현재 창 닫기
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                } else {
                    System.out.println("Unexpected response: " + response);
                }
            }
        });

        return button;
    }




    private void refreshSeatButtons() {
        try {
            SeatVO seat = new SeatVO();
            List<SeatVO> seatList = seatController.listSeat(seat); // 각 좌석 정보를 가져옴

            for (int i = 0; i < seatList.size(); i++) { // seatList의 크기만큼 반복
                SeatVO currentSeat = seatList.get(i); // 현재 좌석 정보를 가져옴
                String text = "<html><div style='text-align: center; font-size: 9px; '>자리 " + (i + 1);

                JLabel label = (JLabel) seatButtons[i].getComponent(0);

                if (currentSeat.getUserId() != null) {
                    seatButtons[i].setEnabled(false); // 회원이 있으면 버튼 비활성화
                    text += "<br>(사용중)</div></html>";

                    // 이미지와 텍스트를 흐리게 처리
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage().getScaledInstance(seatButtons[i].getPreferredSize().width, seatButtons[i].getPreferredSize().height, Image.SCALE_SMOOTH);
                    Image grayImg = createGrayImage(img); // 이미지 흐리게
                    label.setIcon(new ImageIcon(grayImg));
                    label.setForeground(new Color(128, 128, 128)); // 글씨 색상을 흐리게
                } else {
                    seatButtons[i].setEnabled(true); // 회원이 없으면 버튼 활성화
                    text += "</div></html>";

                    // 이미지와 텍스트를 원래대로
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage().getScaledInstance(seatButtons[i].getPreferredSize().width, seatButtons[i].getPreferredSize().height, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                    label.setForeground(Color.BLACK); // 글씨 색상을 원래대로
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


    private void releasePreviousSeat(String userId) {
        try {
            SeatVO seat = new SeatVO();
            List<SeatVO> seatList = seatController.listSeat(seat);

            for (SeatVO currentSeat : seatList) {
                if (userId.equals(currentSeat.getUserId())) {
                    currentSeat.setUserId(null);
                    seatController.modSeat(currentSeat); // 좌석 정보 업데이트
                    break; // 동일한 아이디가 발견되면 더 이상 반복할 필요 없음
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startRefreshTimer() { // 새로 고침 타이머를 시작하는 메서드
        Timer timer = new Timer(true); // Timer 객체를 생성 (데몬 스레드로 실행)
        timer.scheduleAtFixedRate(new TimerTask() { // 타이머 작업을 일정 주기로 실행
            @Override
            public void run() { // 타이머 작업 내용
                SwingUtilities.invokeLater(() -> refreshSeatButtons()); // 새로 고침 작업을 이벤트 큐에서 실행
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
}
