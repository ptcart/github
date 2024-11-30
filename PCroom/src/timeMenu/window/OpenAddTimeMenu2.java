package timeMenu.window;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import network.Client;
import chatNetwork.Client;
import timeMenu.vo.TimeMenuVO;

public class OpenAddTimeMenu2 extends JFrame {

	private String userId = null;
    private String paymentMethod = "현금"; // 기본 결제수단은 현금
    private JButton cashButton, cardButton;
    //private int remainingSeconds = 0; // 남은 시간 (초)
    private int useMoney = 0; // 사용 금액
    private int remainingSeconds;

    private Client client; // 서버와의 통신 객체
    private List<TimeMenuVO> timeMenuList; // 클래스 멤버 변수로 선언
    
    public OpenAddTimeMenu2(String userId, Client client) {
    	this.userId = userId;
        this.client = client;
        
        setTitle("시간 추가");
        setSize(600, 600); // 창 크기 설정
        getContentPane().setLayout(null); // 자유 레이아웃 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 배경 색상 설정
        getContentPane().setBackground(new Color(60, 63, 65));

        // 결제수단 표시
        JLabel paymentLabel = new JLabel("결제수단: " + paymentMethod);
        paymentLabel.setBounds(20, 10, 200, 30);
        paymentLabel.setForeground(Color.WHITE);
        paymentLabel.setFont(new Font("나눔고딕", Font.BOLD, 14));
        getContentPane().add(paymentLabel);

        // 결제수단 버튼 (현금, 카드 선택)
        setupPaymentButtons(paymentLabel);

        // 시간 추가 메뉴 버튼 생성 및 추가
        setupTimeMenuButtons();

        // 오른쪽 하단 버튼 (뒤로가기, 결제)
        setupBottomButtons(userId, timeNum);
    }

 // 결제수단 버튼 설정
    private void setupPaymentButtons(JLabel paymentLabel) {
        cashButton = new JButton("현금");
        cashButton.setBounds(20, 50, 100, 40);
        cashButton.setBackground(new Color(255, 0, 0)); // 빨간색으로 초기화
        cashButton.setForeground(Color.WHITE);
        cashButton.setFont(new Font("나눔고딕", Font.BOLD, 12));
        cashButton.addActionListener(e -> {
            paymentMethod = "현금";
            paymentLabel.setText("결제수단: 현금");
            updatePaymentButtonStyle();
            setupTimeMenuButtons(); // 결제수단 변경 시 시간 메뉴 업데이트
        });
        getContentPane().add(cashButton);

        cardButton = new JButton("카드");
        cardButton.setBounds(130, 50, 100, 40);
        cardButton.setBackground(Color.WHITE); // 기본 색상은 하얀색
        cardButton.setForeground(new Color(0, 0, 128));
        cardButton.setFont(new Font("나눔고딕", Font.BOLD, 12));
        cardButton.addActionListener(e -> {
            paymentMethod = "카드";
            paymentLabel.setText("결제수단: 카드");
            updatePaymentButtonStyle();
            setupTimeMenuButtons(); // 결제수단 변경 시 시간 메뉴 업데이트
        });
        getContentPane().add(cardButton);
    }

    private int selectedAddSeconds = 0; // 선택된 추가 시간 (초)
    private int selectedCost = 0; // 선택된 금액
    private int timeNum = 0; // 시간메뉴 번호

 // 시간 메뉴 버튼 설정
    private void setupTimeMenuButtons() {
        timeMenuList = fetchTimeMenuList(paymentMethod); // 결제수단에 맞는 시간 메뉴 가져오기

        // 기존 버튼을 제거하고 새로 설정
        Arrays.stream(getContentPane().getComponents()) // 배열을 스트림으로 변환
            .filter(comp -> comp instanceof JButton && comp != cashButton && comp != cardButton) // 필터링
            .forEach(comp -> getContentPane().remove(comp)); // 컴포넌트 제거

        // 버튼 위치 정보 정의 (2x4 레이아웃)
        int[][] buttonBounds = {
            {20, 100, 140, 100}, {180, 100, 140, 100},
            {20, 210, 140, 100}, {180, 210, 140, 100},
            {20, 320, 140, 100}, {180, 320, 140, 100},
            {20, 430, 140, 100}, {180, 430, 140, 100}
        };

        for (int i = 0; i < timeMenuList.size() && i < buttonBounds.length; i++) {
            TimeMenuVO timeMenu = timeMenuList.get(i);

            String label = "<html>" + timeMenu.getCost() + "원<br>(" + formatMinutes(timeMenu.getAddTime() / 60) + ")</html>";
            JButton button = new JButton(label);
            button.setBounds(buttonBounds[i][0], buttonBounds[i][1], buttonBounds[i][2], buttonBounds[i][3]);
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(59, 89, 182));
            button.setFont(new Font("나눔고딕", Font.PLAIN, 17));
            button.setBorder(new LineBorder(Color.BLACK, 2));
            button.setFocusPainted(false);
            
            int timeNum = timeMenu.getTimeNum();
            int addSeconds = timeMenu.getAddTime();
            int cost = timeMenu.getCost();
            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();

                clickedButton.setBackground(new Color(173, 216, 230)); // 선택된 버튼 색상
                for (Component comp : getContentPane().getComponents()) {
                    if (comp instanceof JButton && comp != clickedButton && comp != cashButton && comp != cardButton) {
                        if (comp.getBounds().x >= 20 && comp.getBounds().x <= 180 &&
                            comp.getBounds().y >= 100 && comp.getBounds().y <= 430) {
                            comp.setBackground(Color.WHITE); // 다른 버튼 초기화
                        }
                    }
                }

                // 선택된 시간 및 금액 저장
                this.timeNum = timeNum;
                selectedAddSeconds = addSeconds;
                selectedCost = cost;

                System.out.println("시간메뉴 번호 : " + this.timeNum +  "선택된 시간: " + formatTime(selectedAddSeconds) + ", 금액: " + selectedCost + "원");
            });

            getContentPane().add(button);
        }

        // 뒤로가기, 결제 버튼 다시 추가
        setupBottomButtons(userId, timeNum);

        // 화면 새로 고침
        getContentPane().revalidate();
        getContentPane().repaint();
    }




    // 하단 버튼 설정 (뒤로가기, 결제)
    private void setupBottomButtons(String userId, int timeNum) {
    	//this.timeNum = timeNum;
        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(474, 490, 100, 40);
        backButton.setBackground(new Color(255, 204, 203)); // 연한 빨간색
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("나눔고딕", Font.BOLD, 12));
        backButton.addActionListener(e -> dispose());
        getContentPane().add(backButton);

        JButton payButton = new JButton("결제");
        payButton.setBounds(362, 490, 100, 40);
        payButton.setBackground(new Color(255, 69, 0));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("나눔고딕", Font.BOLD, 12));
        payButton.addActionListener(e -> {
            if (selectedAddSeconds > 0 && selectedCost > 0) {
               
                // 콘솔에 결제 완료 메시지와 함께 현재 날짜와 시간을 출력
                System.out.println(paymentMethod + " 결제 완료: " +
                    "추가 시간 " + formatTime(selectedAddSeconds) + ", 금액 " + selectedCost + "원");
                //System.out.println("결제 시각: " + formattedDateTime);

                
                
                // 안내창 표시
                JOptionPane.showMessageDialog(this, "결제가 완료되었습니다!", "결제 완료", JOptionPane.INFORMATION_MESSAGE);

                remainingSeconds += selectedAddSeconds;
                useMoney += selectedCost;
                
//                textFieldRemainingTime.setText(formatTime(remainingSeconds));
//                textFieldUsageFee.setText(useMoney + " 원");
                
                
                String response1 = client.sendCommand("MOD_TIME_SIMPLE," + userId + "," + selectedAddSeconds); // 모든 데이터를 요청
                String response2 = client.sendCommand("TIME_PAY," + userId + "," + this.timeNum); // 모든 데이터를 요청
                
                // 선택 초기화
                selectedAddSeconds = 0;
                selectedCost = 0;

                dispose(); // 창 닫기
            } else {
                System.out.println("추가할 시간을 선택해주세요.");
                JOptionPane.showMessageDialog(this, "추가할 시간을 선택해주세요.", "결제 실패", JOptionPane.WARNING_MESSAGE);
            }
        });

        getContentPane().add(payButton);
    }	

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public int getUpdatedFee() {
        return useMoney;
    }
    
    private List<TimeMenuVO> fetchTimeMenuList(String paymentMethod) { // paymentMethod 추가
        List<TimeMenuVO> timeMenuList = new ArrayList<>();
        String response = client.sendCommand("TIME_MENU,"); // 모든 데이터를 요청
        if (response != null && !response.isEmpty()) {
            String[] lines = response.split("\n");
            for (String line : lines) {
                if (line.equals("END")) break;
                String[] data = line.split(",");
                if (data[2].equals(paymentMethod)) { // paymentMethod로 필터링
                    TimeMenuVO timeMenu = new TimeMenuVO(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2],
                        Integer.parseInt(data[3])
                    );
                    timeMenuList.add(timeMenu);
                }
            }
        }
        return timeMenuList;
    }


    // 결제수단 버튼 스타일 업데이트
    private void updatePaymentButtonStyle() {
        if (paymentMethod.equals("현금")) {
            cashButton.setBackground(new Color(255, 0, 0)); // 현금 버튼은 빨간색
            cashButton.setForeground(Color.WHITE);
            cardButton.setBackground(Color.WHITE); // 카드 버튼은 하얀색
            cardButton.setForeground(new Color(0, 0, 128));
        } else {
            cashButton.setBackground(Color.WHITE);
            cashButton.setForeground(Color.BLACK);
            cardButton.setBackground(new Color(255, 0, 0));
            cardButton.setForeground(Color.WHITE);
        }
    }
    

    // 분 단위로 포맷
    private String formatMinutes(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    // 시간(초)을 HH:mm:ss로 포맷
    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}