package admin.window; // admin.window 패키지 선언

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import timeMenu.controller.TimeMenuController;
import timeMenu.controller.TimeMenuControllerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chatNetwork.Client;
import timeMenu.vo.TimeMenuVO;

public class TimeMenuManage extends JFrame {

	private String userId = null;
    private String paymentMethod = "현금"; // 기본 결제수단은 현금
    private JButton cashButton, cardButton;
    //private int remainingSeconds = 0; // 남은 시간 (초)
    private int useMoney = 0; // 사용 금액
    private int remainingSeconds;
    private JTextField textFieldRemainingTime; // 남은 시간 표시 필드
    private JTextField textFieldUsageFee; // 사용 금액 표시 필드
    private Client client; // 서버와의 통신 객체
    private List<TimeMenuVO> timeMenuList; // 클래스 멤버 변수로 선언
    
    static TimeMenuController timeMenuController = new TimeMenuControllerImpl(); // 회원 컨트롤러 인스턴스 생성
    //static FoodMenuController foodMenuController = new FoodMenuControllerImpl(); // 좌석 컨트롤러 인스턴스 생성
    
    public TimeMenuManage() {
        
        setTitle("시간 관리");
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
        setupBottomButtons();
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
        setupBottomButtons();

        // 화면 새로 고침
        getContentPane().revalidate();
        getContentPane().repaint();
    }




 // 하단 버튼 설정 (요금제 추가, 요금제 수정, 요금제 삭제)
    private void setupBottomButtons() {
        // 버튼 위치 및 크기
        int buttonWidth = 120;
        int buttonHeight = 40;
        int buttonSpacing = 10;
        int baseX = 400; // 버튼들의 공통 X 좌표 (중앙 정렬)
        int baseY = 390; // 첫 번째 버튼의 Y 좌표 시작 위치

        // 요금제 추가 버튼
        JButton addButton = new JButton("요금제 추가");
        addButton.setBounds(baseX, baseY, buttonWidth, buttonHeight);
        styleButton(addButton, new Color(46, 204, 113)); // 녹색
        addButton.addActionListener(e -> {
            // 현재 timeMenuList에서 해당 결제 방식의 메뉴 갯수 계산
            long buttonCount = timeMenuList.stream()
                .filter(menu -> menu.getPayment().equals(paymentMethod)) // 현재 결제방식 필터
                .count();

            if (buttonCount >= 8) {
                // 해당 결제방식 메뉴가 8개 이상일 경우 메시지 표시
                JOptionPane.showMessageDialog(this, 
                    "현재 결제 방식 (" + paymentMethod + ") 메뉴는 최대 8개까지 추가할 수 있습니다. (현재: " + buttonCount + "개)", 
                    "추가 제한", JOptionPane.WARNING_MESSAGE);
                System.out.println("요금제 추가 제한: " + paymentMethod + " 방식 메뉴가 최대 개수 초과");
                return;
            }

            // 버튼 추가 로직
            // JOptionPane.showMessageDialog(this, "요금제를 추가합니다.", 
                //"요금제 추가", JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("요금제 추가 버튼 클릭됨");
            RegPayTimeDialog RPTD = new RegPayTimeDialog(paymentMethod);
            dispose();
            RPTD.setVisible(true);

            // 실제 요금제 추가 로직 작성
        });


        getContentPane().add(addButton);

     // 요금제 수정 버튼
        JButton editButton = new JButton("요금제 수정");
        editButton.setBounds(baseX, baseY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
        styleButton(editButton, new Color(241, 196, 15)); // 노란색
        editButton.addActionListener(e -> {
            // 선택된 시간 메뉴가 없으면 안내창 띄우기
            if (timeNum == 0) { // 선택된 시간이 없으면
                JOptionPane.showMessageDialog(this, "수정할 시간 메뉴를 먼저 선택하세요.", "선택 오류", JOptionPane.WARNING_MESSAGE);
            } else {
                // 선택된 시간 메뉴 값 가져오기
                TimeMenuVO selectedMenu = timeMenuList.stream()
                    .filter(menu -> menu.getTimeNum() == timeNum) // 선택된 timeNum에 맞는 메뉴 찾기
                    .findFirst()
                    .orElse(null);

                if (selectedMenu != null) {
                    // 선택된 메뉴 정보 출력 (디버그용, 실제 로직에 맞게 활용)
//                    System.out.println("선택된 시간 메뉴: " + selectedMenu.getTimeNum());
//                    System.out.println("선택된 시간: " + formatTime(selectedMenu.getAddTime()));
//                    System.out.println("선택된 금액: " + selectedMenu.getCost() + "원");

                    // 요금제 수정 다이얼로그 열기
                    PayTimeinfo editDialog = new PayTimeinfo(selectedMenu.getPayment(),
                                                             selectedMenu.getTimeNum(),
                                                             selectedMenu.getAddTime(),
                                                             selectedMenu.getCost());
                    editDialog.setVisible(true);
                    dispose();
                }
            }
        });

        getContentPane().add(editButton);

        // 요금제 삭제 버튼
        JButton deleteButton = new JButton("요금제 삭제");
        deleteButton.setBounds(baseX, baseY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight);
        styleButton(deleteButton, new Color(231, 76, 60)); // 빨간색
        deleteButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "요금제를 삭제하였습니다.", "요금제 삭제", JOptionPane.INFORMATION_MESSAGE);
            TimeMenuVO tm = new TimeMenuVO();
            tm.setTimeNum(timeNum);
            timeMenuController.removeTimeMenu(tm);
            
            dispose();
            TimeMenuManage TMM = new TimeMenuManage();
            TMM.setVisible(true);
            
            
            //System.out.println("요금제 삭제 버튼 클릭됨");
            // 요금제 삭제 로직 작성
        });
        getContentPane().add(deleteButton);
        
     // 나가기 버튼
        JButton exitButton = new JButton("나가기");

        // 카드, 현금 버튼의 수평선 오른쪽 끝에 위치 설정
        exitButton.setBounds(400, 50, buttonWidth, buttonHeight); 

        exitButton.setBackground(new Color(231, 76, 60)); // 빨간색 배경
        styleButton(exitButton, new Color(231, 76, 60)); // 빨간색
        exitButton.setFocusPainted(false); // 클릭 시 테두리 비활성화
        exitButton.addActionListener(e -> {
            dispose();
        });

        getContentPane().add(exitButton);
    }


    // 버튼 스타일 설정 메서드
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("나눔고딕", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(Color.BLACK, 2));
    }


    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public int getUpdatedFee() {
        return useMoney;
    }
    
    private List<TimeMenuVO> fetchTimeMenuList(String paymentMethod) { // paymentMethod 추가
        List<TimeMenuVO> timeMenuList = new ArrayList<>();
        TimeMenuVO timeMenuVO = new TimeMenuVO(); // 조건 설정 객체
        List<TimeMenuVO> timeMenus = timeMenuController.listTimeMenu(timeMenuVO); // 컨트롤러를 통해 데이터 가져오기

        for (TimeMenuVO menu : timeMenus) {
            if (menu.getPayment().equals(paymentMethod)) { // paymentMethod로 필터링
                timeMenuList.add(menu);
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
    
    
    public static void main(String[] args) {
        // Swing의 GUI를 안전하게 실행하기 위해 SwingUtilities.invokeLater 사용
        SwingUtilities.invokeLater(() -> {
            TimeMenuManage timeMenuManage = new TimeMenuManage(); // TimeMenuManage 인스턴스 생성
            timeMenuManage.setVisible(true); // 창을 보이게 설정
        });
    }
}