package admin.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import remainTime.vo.RemainTimeVO;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;

public class AdminAddTime {

    private JFrame frame;
    private JTextField hoursField;
    private JTextField minutesField;
    
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();

    // 초 단위 초기값
    private int initialSeconds;
    private String userId;


    public AdminAddTime(String userId, int initialSeconds) {
    	this.userId = userId;
    	this.initialSeconds = initialSeconds;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.getContentPane().setLayout(null);
        frame.setBounds(100, 100, 300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblHours = new JLabel("시간 :");
        lblHours.setForeground(Color.WHITE);
        lblHours.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lblHours.setBounds(50, 50, 50, 25);
        frame.getContentPane().add(lblHours);

        hoursField = new JTextField();
        hoursField.setBounds(100, 50, 100, 25);
        frame.getContentPane().add(hoursField);
        hoursField.setColumns(10);

        JLabel lblMinutes = new JLabel("분 :");
        lblMinutes.setForeground(Color.WHITE);
        lblMinutes.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lblMinutes.setBounds(50, 100, 50, 25);
        frame.getContentPane().add(lblMinutes);

        minutesField = new JTextField();
        minutesField.setBounds(100, 100, 100, 25);
        frame.getContentPane().add(minutesField);
        minutesField.setColumns(10);

        JButton btnAddTime = new JButton("추가");
        btnAddTime.setBounds(100, 150, 100, 30);
        btnAddTime.setFont(new Font("맑은 고딕", Font.BOLD, 14)); // 글꼴 설정
        btnAddTime.setBackground(new Color(70, 130, 180)); // 파란색 배경
        btnAddTime.setForeground(Color.WHITE); // 흰색 글자
        btnAddTime.setFocusPainted(false); // 클릭 시 테두리 제거
        btnAddTime.setBorderPainted(false); // 테두리 제거

        frame.getContentPane().add(btnAddTime);

        btnAddTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int hours = Integer.parseInt(hoursField.getText());
                    int minutes = Integer.parseInt(minutesField.getText());

                    // 입력값을 초로 변환하여 합산
                    int addedSeconds = (hours * 3600) + (minutes * 60);
                    int totalSeconds = initialSeconds + addedSeconds;

                    // RemainTimeVO 객체 생성 및 설정
                    RemainTimeVO RTVO = new RemainTimeVO();
                    RTVO.setUserId(userId);
                    RTVO.setRemainTime(totalSeconds);

                    
                    // 남은 시간 수정
                    remainTimeController.modRemainTime(RTVO);

                    // 안내창 표시
                    JOptionPane.showMessageDialog(frame, "추가가 완료되었습니다.", "안내", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    // 창 닫기
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "시간과 분에 올바른 숫자를 입력하세요.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
