package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class adminLogin {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adminLogin window = new adminLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public adminLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // 패널을 추가해 배경 색상을 설정
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 63, 65)); // 다크 그레이 톤
        panel.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // 제목 라벨 (더 크게, 색상 추가)
        JLabel lblAdminLogin = new JLabel("관리자 로그인");
        lblAdminLogin.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        lblAdminLogin.setForeground(new Color(255, 255, 255)); // 흰색
        lblAdminLogin.setBounds(150, 30, 200, 30);
        panel.add(lblAdminLogin);

        // 사용자명 라벨 (색상 추가)
        JLabel lblUsername = new JLabel("아이디");
        lblUsername.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblUsername.setForeground(Color.WHITE); // 흰색
        lblUsername.setBounds(80, 100, 80, 20);
        panel.add(lblUsername);

        // 사용자명 입력 필드 (모서리 둥글게, 배경 색상 변경)
        usernameField = new JTextField();
        usernameField.setBounds(170, 100, 150, 25);
        usernameField.setBackground(new Color(230, 230, 230)); // 밝은 회색
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // 파란색 테두리
        panel.add(usernameField);
        usernameField.setColumns(10);

        // 비밀번호 라벨 (색상 추가)
        JLabel lblPassword = new JLabel("비밀번호:");
        lblPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblPassword.setForeground(Color.WHITE); // 흰색
        lblPassword.setBounds(80, 140, 80, 20);
        panel.add(lblPassword);

        // 비밀번호 입력 필드 (모서리 둥글게, 배경 색상 변경)
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 140, 150, 25);
        passwordField.setBackground(new Color(230, 230, 230)); // 밝은 회색
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // 파란색 테두리
        panel.add(passwordField);

        // 로그인 버튼 (색상, 글꼴 변경)
        JButton btnLogin = new JButton("로그인");
        btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        btnLogin.setBackground(new Color(70, 130, 180)); // 파란색
        btnLogin.setForeground(Color.WHITE); // 흰색 텍스트
        btnLogin.setBounds(170, 180, 150, 30);
        panel.add(btnLogin);

        // 로그인 버튼 클릭 이벤트
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 간단한 로그인 검증 (예시)
                if (username.equals("admin") && password.equals("password123")) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    // 다음 화면으로 이동하거나 다른 작업 실행
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
