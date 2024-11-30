package main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import network.Client;
import chatNetwork.Client;
import seat.window.SeatSelection;
import timeMenu.window.OpenAddTimeMenu2;
import member.window.RegMemDialog;
import javax.swing.UIManager;

public class PCroomClientWindow extends JFrame {

    public JFrame frame;
    private JTextField id_tf;
    private JPasswordField pw_f;
    private Client client;
    private boolean loginSuccess;
    private String clientId;  // 클라이언트 ID를 저장할 변수 추가
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PCroomClientWindow window = new PCroomClientWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PCroomClientWindow() {
        client = new Client();
        client.start("localhost", 12345);
        initialize();
    }

    private void openSeatSelection(String UserId, Client client, String clientId) {
        SeatSelection seatSelection = new SeatSelection(UserId, client, clientId);
        seatSelection.setVisible(true);
        frame.dispose();
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public void loginSuccess(String UserId) {    
    	this.clientId = client.getClientId();  // Client에서 생성된 clientId를 가져와 저장
        openSeatSelection(UserId, client, this.clientId);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1175, 735);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // 배경 패널
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.DARK_GRAY);
        backgroundPanel.setBounds(0, 0, 1175, 735);
        backgroundPanel.setLayout(null);
        frame.getContentPane().add(backgroundPanel);

        // 로그인 패널
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(60, 63, 65));
        loginPanel.setBounds(0, 553, 1163, 145);
        loginPanel.setLayout(null);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        backgroundPanel.add(loginPanel);

        JLabel lId = new JLabel("아이디");
        lId.setForeground(Color.WHITE);
        lId.setFont(new Font("돋움", Font.PLAIN, 14));
        lId.setBounds(444, 10, 100, 30);
        loginPanel.add(lId);

        JLabel lPw = new JLabel("비밀번호");
        lPw.setForeground(Color.WHITE);
        lPw.setFont(new Font("돋움", Font.PLAIN, 14));
        lPw.setBounds(444, 50, 100, 30);
        loginPanel.add(lPw);

        id_tf = new JTextField();
        id_tf.setBounds(544, 10, 250, 30);
        id_tf.setBackground(Color.BLACK);
        id_tf.setForeground(Color.WHITE);
        id_tf.setCaretColor(Color.WHITE);
        loginPanel.add(id_tf);
        id_tf.setColumns(10);

        pw_f = new JPasswordField();
        pw_f.setBounds(544, 50, 250, 30);
        pw_f.setBackground(Color.BLACK);
        pw_f.setForeground(Color.WHITE);
        pw_f.setCaretColor(Color.WHITE);
        loginPanel.add(pw_f);

        JButton btnNewButton = new JButton("회원가입");
        btnNewButton.setBounds(544, 90, 115, 40);
        
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(100, 100, 100));
        btnNewButton.setFocusPainted(false);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegMemDialog rmd = new RegMemDialog(client);
                rmd.setVisible(true);
            }
        });
        loginPanel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("로그인");
        btnNewButton_1.setBounds(679, 90, 115, 40);
        btnNewButton_1.setBackground(new Color(0, 120, 215));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = id_tf.getText().trim();
                char[] passwordArray = pw_f.getPassword();
                String pw = new String(passwordArray).trim();

                String response = client.sendCommand("LOG_MEM," + id + "," + pw);
                
                loginSuccess = "LOGIN_SUCCESS".equals(response.trim());
                
                if(loginSuccess) {
//                    JOptionPane.showMessageDialog(null, 
//                            "로그인 성공", "성공", 
//                            JOptionPane.INFORMATION_MESSAGE);
                      loginSuccess(id);
                	
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "해당하는 아이디가 없습니다. 다시 확인하세요.", 
                            "실패", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("요금제 구매");
        btnNewButton_1_1.setBounds(834, 14, 155, 116);
        btnNewButton_1_1.setBackground(new Color(220, 20, 60));
        btnNewButton_1_1.setForeground(Color.WHITE);
        btnNewButton_1_1.setFont(new Font("돋움", Font.BOLD, 18));
        btnNewButton_1_1.setFocusPainted(false);
        btnNewButton_1_1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 아이디 입력 창 띄우기
                String userId = JOptionPane.showInputDialog(null, "아이디를 입력하세요:", "아이디 입력", JOptionPane.QUESTION_MESSAGE);
                String response = client.sendCommand("TIME_MEM," + userId);
                loginSuccess = "LOGIN_SUCCESS".equals(response.trim());
                
                if(loginSuccess) {
//                    JOptionPane.showMessageDialog(null, 
//                            "로그인 성공", "성공", 
//                            JOptionPane.INFORMATION_MESSAGE);
                	OpenAddTimeMenu2 time2 = new OpenAddTimeMenu2(userId, client);
                	time2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "해당하는 아이디가 없습니다. 다시 입력해주십시오.", 
                            "실패", 
                            JOptionPane.ERROR_MESSAGE);
                }
                if (userId != null && !userId.isEmpty()) {
                    // 입력받은 아이디로 처리하는 동작 추가
                    //JOptionPane.showMessageDialog(null, "입력된 아이디: " + userId, "아이디 확인", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하지 않았습니다.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        loginPanel.add(btnNewButton_1_1);

        // 이미지 원본 로드 및 크기 조정
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\PC방 로그인 배경화면.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(1175, 735, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // JLabel에 조정된 이미지를 설정합니다.
        JLabel PCback = new JLabel();
        PCback.setIcon(resizedIcon);
        PCback.setBounds(0, 0, 1175, 735);
        backgroundPanel.add(PCback);
    }
}
