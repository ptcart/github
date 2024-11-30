package remainTime.window;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import foodMenu.window.FoodMenuUI;
import foodSort.vo.FoodSortVO;
import main.PCroomClientWindow;
import member.vo.MemberVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//import network.Client;
import chatNetwork.Client;
//import timeMenu.window.OpenAddTimeMenu;
//import timeMenu.window.OpenAddTimeMenu2;
import timeMenu.window.OpenAddTimeMenu3;

import main.ClientMessage;

public class PCroomMain extends JFrame {

    private JTextField textFieldSeatNumber;
    private JTextField textFieldUsageFee;
    private JTextField textFieldUsageTime;
    private JTextField textFieldRemainingTime;
    private Timer timer;
    private int remainingSeconds;
    private int usageSeconds;
    private int useMoney;
    private String userId;
    private Client client;
    private String clientId;

    private List<MemberVO> memList = new ArrayList<>();
    
    private Point initialClick;
    private int timeCount =0;

    /**
     * Create the application.
     */
    public PCroomMain(String userId, int seatNumber, int initialRemainingTime, Client client, String clientId) {
        this.remainingSeconds = initialRemainingTime;
        this.usageSeconds = 0;
        this.useMoney = 0;
        this.userId = userId;
        this.client = client;
        this.clientId = clientId;
        initialize(userId, seatNumber); // 초기화 메서드 호출
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String userId, int seatNumber) {
        setTitle("PC방 회원 정보"); // 창 제목 설정
        setBounds(100, 20, 1400, 800); // 창 크기 및 위치 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 동작 설정

        // 배경 패널 설정
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(null); // Absolute layout으로 설정
        getContentPane().add(mainPanel);

        // 정보와 버튼을 포함할 패널
        JPanel infoButtonPanel = new JPanel();
        infoButtonPanel.setLayout(new GridBagLayout());
        infoButtonPanel.setBorder(new TitledBorder(new LineBorder(new Color(187, 187, 187)), "회원 정보",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("나눔고딕", Font.BOLD, 14), new Color(187, 187, 187))); // 패널 테두리 설정 및 폰트 크기 줄이기
        infoButtonPanel.setBackground(new Color(43, 43, 43));
        infoButtonPanel.setBounds(1035, 0, 350, 220); // 초기 위치와 크기 설정

        // 패널을 드래그해서 이동할 수 있도록 설정
        infoButtonPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        infoButtonPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // 패널의 새로운 위치 계산
                int thisX = infoButtonPanel.getLocation().x;
                int thisY = infoButtonPanel.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                infoButtonPanel.setLocation(X, Y);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 내부 여백 설정
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        // 각 정보 항목을 위한 패널 추가
        textFieldSeatNumber = new JTextField(String.valueOf(seatNumber));
        textFieldSeatNumber.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        textFieldUsageFee = new JTextField(String.valueOf(useMoney + " 원"));
        textFieldUsageFee.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        textFieldUsageTime = new JTextField(formatTime(usageSeconds));
        textFieldUsageTime.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        textFieldRemainingTime = new JTextField(formatTime(remainingSeconds));
        textFieldRemainingTime.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoButtonPanel.add(createInfoPanel("자리 번호:", textFieldSeatNumber), gbc);
        gbc.gridy = 1;
        infoButtonPanel.add(createInfoPanel("사용 요금:", textFieldUsageFee), gbc);
        gbc.gridy = 2;
        infoButtonPanel.add(createInfoPanel("사용 시간:", textFieldUsageTime), gbc);
        gbc.gridy = 3;
        infoButtonPanel.add(createInfoPanel("남은 시간:", textFieldRemainingTime), gbc);

        // 하단 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0)); // GridLayout으로 설정
        bottomPanel.setBackground(new Color(43, 43, 43));

        // 종료 버튼
        JButton btnExit = new JButton("종료");
        btnExit.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        btnExit.setBackground(new Color(220, 53, 69)); // 모던한 빨간색으로 변경
        btnExit.setForeground(Color.WHITE);
        btnExit.setPreferredSize(new Dimension(80, 30)); // 버튼 크기 조정
        btnExit.setFocusPainted(false);
        btnExit.setBorderPainted(false); // 테두리 제거
        btnExit.addActionListener(e -> {
            // 사용자의 잔여 시간 저장 및 출력
            saveRemainingTime();
            String response = client.sendCommand("QUIT_PC," + userId + "," + remainingSeconds + "," + seatNumber);
            client.close();
            timer.stop();
            JOptionPane.showMessageDialog(this, "종료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        bottomPanel.add(btnExit);
        
     // "내 정보" 버튼 추가
        JButton btnMyInfo = new JButton("내 정보");
        btnMyInfo.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        btnMyInfo.setBackground(new Color(40, 167, 69)); // 초록색 배경
        btnMyInfo.setForeground(Color.WHITE);
        btnMyInfo.setPreferredSize(new Dimension(80, 30)); // 버튼 크기 조정
        btnMyInfo.setFocusPainted(false);
        btnMyInfo.setBorderPainted(false); // 테두리 제거
        btnMyInfo.addActionListener(e -> {
        	// 유저 찾는 명령어 보내기
        	String response1 = client.sendCommand("GET_MEMS," + userId);
        	// 유저 정보 받기
        	memList = parseResponse1(response1);
        	String Id = null;
        	String Pw = null;
        	String Name = null;
        	String Gender = null;
        	String PhoneNum = null;
            
            for(MemberVO m : memList) {
            	Id = m.getId();
            	Pw = m.getPw();
            	Name = m.getName();
            	Gender = m.getGender();
            	PhoneNum = m.getPhoneNum();
            }
            
            // MyInfoUI 창 열기
            MyInfoUI myInfoWindow = new MyInfoUI(seatNumber, PCroomMain.this, client, Id, Pw, Name, Gender, PhoneNum);
            myInfoWindow.setVisible(true);
        });
        bottomPanel.add(btnMyInfo);
        
     // "메시지" 버튼 추가
        JButton btnMessage = new JButton("메시지");
        btnMessage.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 설정
        btnMessage.setBackground(new Color(0, 123, 255)); // 파란색 배경
        btnMessage.setForeground(Color.WHITE);
        btnMessage.setPreferredSize(new Dimension(80, 30)); // 버튼 크기 조정
        btnMessage.setFocusPainted(false);
        btnMessage.setBorderPainted(false); // 테두리 제거
        btnMessage.addActionListener(e -> {
        	// 유저 찾는 명령어 보내기
        	
        	ClientMessage CM = new ClientMessage(client, clientId, seatNumber);
        	CM.setVisible(true);
        });

        // 패널의 그리드 레이아웃을 1행 3열로 수정
        bottomPanel.setLayout(new GridLayout(1, 3, 10, 0));
        bottomPanel.add(btnMessage);

        
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        infoButtonPanel.add(bottomPanel, gbc);

        // 오른쪽 상단에 정보와 버튼 패널 추가
        mainPanel.add(infoButtonPanel);

        // 아이콘 크기 조정
        ImageIcon icon = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\internet_icon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);

        // 인터넷 아이콘과 텍스트를 포함할 패널 생성
        JPanel internetPanel = new JPanel();
        internetPanel.setLayout(new BorderLayout());
        internetPanel.setOpaque(false); // 패널을 투명하게 설정
        internetPanel.setBounds(50, 50, 50, 70); // 패널 위치와 크기 설정

        // 인터넷 아이콘 레이블 생성
        JLabel internetIconLabel = new JLabel();
        internetIconLabel.setIcon(icon); // 크기 조정된 아이콘 설정
        internetIconLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openWebPage("https://www.google.com");
            }
        });

        // "인터넷" 텍스트 레이블 생성
        JLabel internetTextLabel = new JLabel("인터넷");
        internetTextLabel.setFont(new Font("굴림체", Font.PLAIN, 12)); // 폰트 설정
        internetTextLabel.setForeground(new Color(230, 230, 230)); // 글꼴 색상 설정
        internetTextLabel.setHorizontalAlignment(JLabel.CENTER); // 텍스트 중앙 정렬

        // 패널에 아이콘과 텍스트 레이블 추가
        internetPanel.add(internetIconLabel, BorderLayout.CENTER);
        internetPanel.add(internetTextLabel, BorderLayout.SOUTH);

        // 메인 패널에 인터넷 패널 추가
        mainPanel.add(internetPanel);

        // 먹거리메뉴 버튼 추가
        JButton foodMenuButton = new JButton("먹거리메뉴");
        foodMenuButton.setFont(new Font("나눔고딕", Font.BOLD, 16)); // 폰트 설정
        foodMenuButton.setBackground(new Color(255, 193, 7)); // 배경색 설정
        foodMenuButton.setForeground(Color.WHITE); // 글자색 설정
        foodMenuButton.setBounds(470, 10, 150, 40); // 위치 및 크기 설정
        foodMenuButton.setFocusPainted(false);
        foodMenuButton.setBorderPainted(false);
        foodMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 먹거리메뉴 버튼 클릭 시 실행할 동작 추가
                //JOptionPane.showMessageDialog(null, "먹거리메뉴 클릭됨", "알림", JOptionPane.INFORMATION_MESSAGE);
            	FoodMenuUI fmMenu = new FoodMenuUI(client, seatNumber);
            	fmMenu.setVisible(true);
            }
        });
        mainPanel.add(foodMenuButton);
        
     // 시간 추가 버튼을 먹거리메뉴 옆에 추가
        JButton addTimeButton = new JButton("시간 추가");
        addTimeButton.setFont(new Font("나눔고딕", Font.BOLD, 16)); // 폰트 설정
        addTimeButton.setBackground(new Color(255, 193, 7)); // 배경색 설정
        addTimeButton.setForeground(Color.WHITE); // 글자색 설정
        addTimeButton.setBounds(640, 10, 150, 40); // 위치 및 크기 설정
        addTimeButton.setFocusPainted(false);
        addTimeButton.setBorderPainted(false);
        addTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddTimeMenu();
            }
        });
        mainPanel.add(addTimeButton);

        
        // 여기서 시간 조회하는 속도를 좀 줄이쟈
        // 타이머 설정
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimes(seatNumber);
            }
        });
        timer.start();
    }

    /**
     * Create a panel for each information row.
     */
    
    private List<MemberVO> parseResponse1(String response1) {
        List<MemberVO> memList = new ArrayList<>();
        if (response1 != null && !response1.isEmpty()) {
            System.out.println("Parsing response: " + response1);
            String[] lines = response1.split("\n");
            for (String line : lines) {
                if (line.equals("END")) {
                    break;
                }
                String[] data = line.split(",");
                MemberVO memVO = new MemberVO();
                memVO.setId(data[0]);
                memVO.setPw(data[1]);
                memVO.setName(data[2]);
                memVO.setGender(data[3]);
                memVO.setPhoneNum(data[4]);

                memList.add(memVO);
            }
        }
        return memList;
    }
    
    private JPanel createInfoPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(43, 43, 43));
        panel.setOpaque(false); // 패널 투명하게 설정

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        label.setForeground(new Color(187, 187, 187));
        panel.add(label, BorderLayout.WEST);

        textField.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(187, 187, 187));
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Update remaining time and usage time every second.
     */
    private void updateTimes(int seatNumber) {
        
    	String response3 = client.sendCommand("CHECK_SEAT," + seatNumber);
    	if(response3.equals("CHECK_OK")){
    	    // 안내창을 띄운 후 프로그램 종료
    	    JOptionPane.showMessageDialog(null, "PC를 강제 종료합니다", "알림", JOptionPane.INFORMATION_MESSAGE);
    	    
    	    // 클라이언트 프로그램 종료
    	    System.exit(0);
    	}


    	if (remainingSeconds > 0) {
            remainingSeconds--;
            usageSeconds++;

            textFieldRemainingTime.setText(formatTime(remainingSeconds));
            textFieldUsageTime.setText(formatTime(usageSeconds));

            textFieldRemainingTime.repaint();
            textFieldUsageTime.repaint();
            timeCount++;

            if (timeCount == 3) {
                // 사용금액 업데이트 계속 해야함
                String response = client.sendCommand("MOD_TIME," + userId + "," + remainingSeconds + "," + useMoney);

                if (response.startsWith("STOP,")) {
                    // "STOP," 제거 후 나머지 값을 정수로 변환
                	System.out.println("나왔다 시벨로마");
                    String timeString = response.substring(5); // "STOP," 이후 부분 추출
                    try {
                    	
                        int additionalTime = Integer.parseInt(timeString);
                        System.out.println("나온 값 : " + additionalTime);
                        remainingSeconds += additionalTime; // 추가 시간 더하기
                        //String response1 = client.sendCommand("MOD_TIME1," + userId + "," + remainingSeconds + "," + useMoney);

                        additionalTime = 0;
                    } catch (NumberFormatException e) {
                        System.err.println("응답 처리 중 에러 발생: " + e.getMessage());
                    }
                }

                timeCount = 0;
            }
        
    

        } else {
            timer.stop();
            JOptionPane.showMessageDialog(this, "시간이 종료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            // 여기서 명령어 보내서 자리에 회원을 null로 바꿔야함
            // 그리고 종료버튼을 누르거나 자동으로 꺼지게 되면 회원 남은시간 정보 저장되고 클라이언트 종료
            saveRemainingTime();
            String response = client.sendCommand("QUIT_PC," + userId + "," + remainingSeconds + "," + seatNumber);
            client.close();
            dispose();
        }
    }

    /**
     * Open the add time menu.
     */
    private void openAddTimeMenu() {
        OpenAddTimeMenu3 addTimeMenu = new OpenAddTimeMenu3(userId, remainingSeconds, useMoney, textFieldRemainingTime, textFieldUsageFee, client);
        addTimeMenu.setVisible(true);
        addTimeMenu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                remainingSeconds = addTimeMenu.getRemainingSeconds();
                useMoney = addTimeMenu.getUpdatedFee();
                // 사용금액 업데이트 계속 해야함
                String response = client.sendCommand("MOD_TIME1," + userId + "," + remainingSeconds + "," + useMoney);
                textFieldRemainingTime.setText(formatTime(remainingSeconds));
                textFieldUsageFee.setText(useMoney + " 원"); // 요금 업데이트
            }
        });
    }

    /**
     * Format time in HH:mm:ss.
     */
    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Save the remaining time.
     */
    private void saveRemainingTime() {
        // 초 단위로 남은 시간을 저장
        int remainingTimeInSeconds = remainingSeconds;
        System.out.println("사용자 ID: " + userId);
        System.out.println("남은 시간 (초): " + remainingTimeInSeconds);
        // 여기서 실제로 데이터를 저장하는 코드를 추가할 수 있습니다.
    }

    /**
     * Open a web page in the default browser.
     */
    private void openWebPage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    /**
     * 배경 패널 클래스
     */
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\win11.jpg").getImage(); // 배경 이미지 설정
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 배경 이미지 그리기
            }
        }
    }
    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

}
