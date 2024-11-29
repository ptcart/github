//package seat.window;
//
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.border.TitledBorder;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.net.URI;
//import network.Client;
//import timeMenu.window.OpenAddTimeMenu;
//
//public class SeatSelection1 extends JFrame {
//
//    private JTextField textFieldSeatNumber;
//    private JTextField textFieldUsageFee;
//    private JTextField textFieldUsageTime;
//    private JTextField textFieldRemainingTime;
//    private Timer timer;
//    private int remainingSeconds;
//    private int usageSeconds;
//    private String userId;
//    private Client client;
//
//    private Point initialClick;
//
//    /**
//     * Create the application.
//     */
//    public SeatSelection1(String userId, int seatNumber, int initialRemainingTime, Client client) {
//        this.remainingSeconds = initialRemainingTime;
//        this.usageSeconds = 0;
//        this.userId = userId;
//        this.client = client;
//        initialize(userId, seatNumber); // 초기화 메서드 호출
//    }
//
//    /**
//     * Initialize the contents of the frame.
//     */
//    private void initialize(String userId, int seatNumber) {
//        setTitle("PC방 회원 정보"); // 창 제목 설정
//        setBounds(100, 100, 1400, 800); // 창 크기 및 위치 설정
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 동작 설정
//
//        // 배경 패널 설정
//        BackgroundPanel mainPanel = new BackgroundPanel();
//        mainPanel.setLayout(null); // Absolute layout으로 설정
//        getContentPane().add(mainPanel);
//
//        // 정보와 버튼을 포함할 패널
//        JPanel infoButtonPanel = new JPanel();
//        infoButtonPanel.setLayout(new GridBagLayout());
//        infoButtonPanel.setBorder(new TitledBorder(new LineBorder(new Color(187, 187, 187)), "회원 정보",
//                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
//                new Font("나눔고딕", Font.BOLD, 14), new Color(187, 187, 187))); // 패널 테두리 설정 및 폰트 크기 줄이기
//        infoButtonPanel.setBackground(new Color(43, 43, 43));
//        infoButtonPanel.setBounds(1035, 0, 350, 200); // 초기 위치와 크기 설정
//
//        // 패널을 드래그해서 이동할 수 있도록 설정
//        infoButtonPanel.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                initialClick = e.getPoint();
//                getComponentAt(initialClick);
//            }
//        });
//        infoButtonPanel.addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                // 패널의 새로운 위치 계산
//                int thisX = infoButtonPanel.getLocation().x;
//                int thisY = infoButtonPanel.getLocation().y;
//
//                int xMoved = e.getX() - initialClick.x;
//                int yMoved = e.getY() - initialClick.y;
//
//                int X = thisX + xMoved;
//                int Y = thisY + yMoved;
//                infoButtonPanel.setLocation(X, Y);
//            }
//        });
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5); // 내부 여백 설정
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0.0;
//
//        int useMoney = 0;
//
//        // 각 정보 항목을 위한 패널 추가
//        textFieldSeatNumber = new JTextField(String.valueOf(seatNumber));
//        textFieldSeatNumber.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        textFieldUsageFee = new JTextField(String.valueOf(useMoney + " 원"));
//        textFieldUsageFee.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        textFieldUsageTime = new JTextField(formatTime(usageSeconds));
//        textFieldUsageTime.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        textFieldRemainingTime = new JTextField(formatTime(remainingSeconds));
//        textFieldRemainingTime.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        infoButtonPanel.add(createInfoPanel("자리 번호:", textFieldSeatNumber), gbc);
//        gbc.gridy = 1;
//        infoButtonPanel.add(createInfoPanel("사용 요금:", textFieldUsageFee), gbc);
//        gbc.gridy = 2;
//        infoButtonPanel.add(createInfoPanel("사용 시간:", textFieldUsageTime), gbc);
//        gbc.gridy = 3;
//        infoButtonPanel.add(createInfoPanel("남은 시간:", textFieldRemainingTime), gbc);
//
//        // 하단 패널
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new GridLayout(1, 2, 10, 0)); // GridLayout으로 설정
//        bottomPanel.setBackground(new Color(43, 43, 43));
//
//        // 종료 버튼
//        JButton btnExit = new JButton("종료");
//        btnExit.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        btnExit.setBackground(new Color(59, 89, 182));
//        btnExit.setForeground(Color.WHITE);
//        btnExit.setPreferredSize(new Dimension(80, 20)); // 버튼 크기 조정 (세로 늘림)
//        btnExit.setMinimumSize(new Dimension(80, 20)); // 최소 크기 설정
//        btnExit.setMaximumSize(new Dimension(80, 20)); // 최대 크기 설정
//        btnExit.setFocusPainted(false);
//        btnExit.setBorder(new LineBorder(new Color(59, 89, 182)));
//        btnExit.addActionListener(e -> {
//            // 사용자의 잔여 시간 저장 및 출력
//            saveRemainingTime();
//            String response = client.sendCommand("QUIT_PC," + userId + "," + remainingSeconds + "," + seatNumber);
//            client.close();
//            timer.stop();
//            JOptionPane.showMessageDialog(this, "종료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
//            dispose();
//        });
//        bottomPanel.add(btnExit);
//
//        // 시간추가 버튼
//        JButton btnAddTime = new JButton("시간추가");
//        btnAddTime.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        btnAddTime.setBackground(new Color(59, 89, 182));
//        btnAddTime.setForeground(Color.WHITE);
//        btnAddTime.setPreferredSize(new Dimension(80, 20)); // 버튼 크기 조정 (세로 늘림)
//        btnAddTime.setMinimumSize(new Dimension(80, 20)); // 최소 크기 설정
//        btnAddTime.setMaximumSize(new Dimension(80, 20)); // 최대 크기 설정
//        btnAddTime.setFocusPainted(false);
//        btnAddTime.setBorder(new LineBorder(new Color(59, 89, 182)));
//        btnAddTime.addActionListener(e -> openAddTimeMenu());
//        bottomPanel.add(btnAddTime);
//
//        gbc.gridy = 4;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.CENTER;
//        infoButtonPanel.add(bottomPanel, gbc);
//
//        // 오른쪽 상단에 정보와 버튼 패널 추가
//        mainPanel.add(infoButtonPanel);
//
//        // 인터넷 아이콘 추가
//        JTextPane internetIconPane = new JTextPane();
//        internetIconPane.setContentType("text/html");
//        internetIconPane.setText("<html><body style='margin: 0; text-align: center;'><a href='https://www.google.com' style='text-decoration: none; color: black; border: none;'><img src='file:///C:/Users/user/Desktop/internet_icon.png' width='50' height='50' style='border: none;'><br><span style='font-size: 12px;'>인터넷</span></a></body></html>");
//        internetIconPane.setEditable(false);
//        internetIconPane.setOpaque(false);
//        internetIconPane.setBounds(50, 50, 90, 90); // 아이콘 위치와 크기 설정
//
//        internetIconPane.addHyperlinkListener(e -> {
//            if (e.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
//                openWebPage(e.getURL().toString());
//            }
//        });
//        mainPanel.add(internetIconPane);
//
//        // 타이머 설정
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateTimes(seatNumber);
//            }
//        });
//        timer.start();
//    }
//
//    /**
//     * Create a panel for each information row.
//     */
//    private JPanel createInfoPanel(String labelText, JTextField textField) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout(10, 10));
//        panel.setBackground(new Color(43, 43, 43));
//        panel.setOpaque(false); // 패널 투명하게 설정
//
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        label.setForeground(new Color(187, 187, 187));
//        panel.add(label, BorderLayout.WEST);
//
//        textField.setFont(new Font("나눔고딕", Font.PLAIN, 12)); // 폰트 크기 줄이기
//        textField.setForeground(Color.BLACK);
//        textField.setBackground(new Color(187, 187, 187));
//        textField.setEditable(false);
//        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        panel.add(textField, BorderLayout.CENTER);
//
//        return panel;
//    }
//
//    /**
//     * Update remaining time and usage time every second.
//     */
//    private void updateTimes(int seatNumber) {
//        if (remainingSeconds > 0) {
//            remainingSeconds--;
//            usageSeconds++;
//            textFieldRemainingTime.setText(formatTime(remainingSeconds));
//            textFieldUsageTime.setText(formatTime(usageSeconds));
//        } else {
//            timer.stop();
//            JOptionPane.showMessageDialog(this, "시간이 종료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
//            // 여기서 명령어 보내서 자리에 회원을 null로 바꿔야함
//            // 그리고 종료버튼을 누르거나 자동으로 꺼지게 되면 회원 남은시간 정보 저장되고 클라이언트 종료
//            saveRemainingTime();
//            String response = client.sendCommand("QUIT_PC," + userId + "," + remainingSeconds + "," + seatNumber);
//            client.close();
//            dispose();
//        }
//    }
//
//    /**
//     * Open the add time menu.
//     */
//    private void openAddTimeMenu() {
//        OpenAddTimeMenu addTimeMenu = new OpenAddTimeMenu(remainingSeconds, textFieldRemainingTime, client);
//        addTimeMenu.setVisible(true);
//        addTimeMenu.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                remainingSeconds = addTimeMenu.getRemainingSeconds();
//                textFieldRemainingTime.setText(formatTime(remainingSeconds));
//            }
//        });
//    }
//
//    /**
//     * Format time in HH:mm:ss.
//     */
//    private String formatTime(int totalSeconds) {
//        int hours = totalSeconds / 3600;
//        int minutes = (totalSeconds % 3600) / 60;
//        int seconds = totalSeconds % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
//    }
//
//    /**
//     * Save the remaining time.
//     */
//    private void saveRemainingTime() {
//        // 초 단위로 남은 시간을 저장
//        int remainingTimeInSeconds = remainingSeconds;
//        System.out.println("사용자 ID: " + userId);
//        System.out.println("남은 시간 (초): " + remainingTimeInSeconds);
//        // 여기서 실제로 데이터를 저장하는 코드를 추가할 수 있습니다.
//    }
//
//    /**
//     * Open a web page in the default browser.
//     */
//    private void openWebPage(String urlString) {
//        try {
//            Desktop.getDesktop().browse(new URI(urlString));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setVisible(boolean visible) {
//        super.setVisible(visible);
//    }
//
//    /**
//     * 배경 패널 클래스	
//     */
//    class BackgroundPanel extends JPanel {
//        private Image backgroundImage;
//
//        public BackgroundPanel() {
//            try {
//                backgroundImage = new ImageIcon("C:\\Users\\user\\Desktop\\win11.jpg").getImage(); // 배경 이미지 설정
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            if (backgroundImage != null) {
//                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 배경 이미지 그리기
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            SeatSelection1 frame = new SeatSelection1("testUser", 5, 3600, new Client()); // 예시로 클라이언트 생성 및 프레임 설정
//            frame.setVisible(true); // 프레임 보이기
//        });
//    }
//}
