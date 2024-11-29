//package timeMenu.window;
//
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//
//import network.Client;
//import timeMenu.vo.TimeMenuVO;
//
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OpenAddTimeMenu extends JFrame {
//
//    private int remainingSeconds;
//    private int useMoney;
//    private JTextField textFieldRemainingTime;
//    private JTextField textFieldUsageFee;
//    private Client client;
//
//    public OpenAddTimeMenu(int remainingSeconds, int useMoney, JTextField textFieldRemainingTime, JTextField textFieldUsageFee, Client client) {
//        this.remainingSeconds = remainingSeconds;
//        this.useMoney = useMoney;
//        this.textFieldRemainingTime = textFieldRemainingTime;
//        this.textFieldUsageFee = textFieldUsageFee;
//        this.client = client;
//        initialize();
//    }
//
//    private void initialize() {
//        setTitle("시간 구입");
//        setSize(300, 400);
//        setLayout(new GridLayout(4, 2, 10, 10)); // 가로 2개, 세로 4개로 레이아웃 설정
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        List<TimeMenuVO> timeMenuList = new ArrayList<TimeMenuVO>();
//
//        String response = client.sendCommand("TIME_MENU,");
//        timeMenuList = parseResponse(response);
//
//        for (TimeMenuVO timeMenu : timeMenuList) {
//            String label = "<html>" + timeMenu.getCost() + "원<br>(" + formatMinutes(timeMenu.getAddTime() / 60) + ")</html>";
//            JButton button = new JButton(label);
//            button.setBorderPainted(false); // 테두리 제거
//            button.setFont(new Font("나눔고딕", Font.PLAIN, 18));
//            button.setBackground(new Color(59, 89, 182));
//            button.setForeground(Color.WHITE);
//            button.setFocusPainted(false);
//            button.setBorder(new LineBorder(new Color(59, 89, 182)));
//            int addSeconds = timeMenu.getAddTime();
//            int cost = timeMenu.getCost();
//            button.addActionListener(e -> {
//                remainingSeconds += addSeconds;
//                useMoney += cost;
//                textFieldRemainingTime.setText(formatTime(remainingSeconds));
//                textFieldUsageFee.setText(useMoney + " 원");
//                dispose();
//            });
//            add(button);
//        }
//
//        // 창 닫힐 때 이벤트 추가
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                updateRemainingTime();
//                updateUsageFee();
//            }
//        });
//    }
//
//    private void updateRemainingTime() {
//        textFieldRemainingTime.setText(formatTime(remainingSeconds));
//    }
//
//    private void updateUsageFee() {
//        textFieldUsageFee.setText(useMoney + " 원");
//    }
//
//    /**
//     * Format minutes to HH:mm format.
//     */
//    private String formatMinutes(int totalMinutes) {
//        int hours = totalMinutes / 60;
//        int minutes = totalMinutes % 60;
//        return String.format("%02d:%02d", hours, minutes);
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
//    public int getRemainingSeconds() {
//        return remainingSeconds;
//    }
//
//    public int getUpdatedFee() {
//        return useMoney;
//    }
//
//    private List<TimeMenuVO> parseResponse(String response) {
//        List<TimeMenuVO> timeMenuList = new ArrayList<>();
//        if (response != null && !response.isEmpty()) {
//            System.out.println("Parsing response: " + response);
//            String[] lines = response.split("\n");
//            for (String line : lines) {
//                if (line.equals("END")) {
//                    break;
//                }
//                String[] data = line.split(",");
//                TimeMenuVO timeMenuVO = new TimeMenuVO();
//                timeMenuVO.setTimeNum(Integer.parseInt(data[0]));
//                timeMenuVO.setAddTime(Integer.parseInt(data[1]));
//                timeMenuVO.setPayment(data[2]);
//                timeMenuVO.setCost(Integer.parseInt(data[3]));
//                timeMenuList.add(timeMenuVO);
//            }
//        }
//        return timeMenuList;
//    }
//}
