package member.window;

import javax.swing.*;

import admin.window.userManage;
import chatNetwork.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegMemDialog extends JDialog {

    private JTextField id_tf;
    private JPasswordField pw_f;
    private JTextField name_tf;
    private JComboBox<String> gender_cb;
    private JTextField phoneNum_tf;
    private Client client;

    public RegMemDialog(Client client) {
        this.client = client;
        initialize();
    }

    private void initialize() {
        setTitle("회원 등록창");
        setBounds(100, 100, 460, 594);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        // 배경색을 진한 회색으로 설정
        getContentPane().setBackground(new Color(60, 60, 60));

        // 폰트 설정
        Font font = new Font("맑은 고딕", Font.PLAIN, 14);

        // ID 입력 필드
        JLabel lId = new JLabel("아이디");
        lId.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lId.setBounds(43, 54, 50, 15);
        lId.setForeground(Color.WHITE);
        getContentPane().add(lId);

        id_tf = new JTextField();
        id_tf.setBounds(105, 42, 170, 40);
        id_tf.setFont(font);
        id_tf.setBackground(new Color(240, 240, 240));
        id_tf.setForeground(Color.BLACK);
        getContentPane().add(id_tf);

        // ID 중복 확인 버튼
        JButton checkIdBtn = new JButton("ID 중복 확인");
        checkIdBtn.setFont(font);
        checkIdBtn.setBounds(285, 42, 130, 40); // ID 입력란 오른쪽에 배치
        checkIdBtn.setBackground(new Color(100, 149, 237)); // 버튼 배경색 변경
        checkIdBtn.setForeground(Color.WHITE); // 버튼 텍스트 색 변경
        checkIdBtn.setBorderPainted(false); // 버튼 경계선 제거
        checkIdBtn.setFocusPainted(false); // 버튼 선택 시 테두리 제거
        checkIdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = id_tf.getText().trim();
                if (!id.isEmpty()) {
                    String response = client.sendCommand("CHECK_ID," + id);
                    System.out.println("결과 : " + response);
                    if (response.equals("ID_OK")) {
                        JOptionPane.showMessageDialog(RegMemDialog.this, "사용 가능한 ID입니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
                    } else if (response.equals("ID_DUPLICATED")) {
                        JOptionPane.showMessageDialog(RegMemDialog.this, "이미 존재하는 ID입니다.", "경고", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(RegMemDialog.this, "ID를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        getContentPane().add(checkIdBtn);

        // 비밀번호 입력 필드
        JLabel lPw = new JLabel("비밀번호");
        lPw.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lPw.setBounds(43, 116, 74, 15);
        lPw.setForeground(Color.WHITE);
        getContentPane().add(lPw);

        pw_f = new JPasswordField();
        pw_f.setBounds(105, 104, 278, 40);
        pw_f.setFont(font);
        pw_f.setBackground(new Color(240, 240, 240));
        pw_f.setForeground(Color.BLACK);
        getContentPane().add(pw_f);

        // 이름 입력 필드
        JLabel lName = new JLabel("이름");
        lName.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lName.setBounds(43, 182, 50, 15);
        lName.setForeground(Color.WHITE);
        getContentPane().add(lName);

        name_tf = new JTextField();
        name_tf.setColumns(10);
        name_tf.setBounds(105, 170, 278, 40);
        name_tf.setFont(font);
        name_tf.setBackground(new Color(240, 240, 240));
        name_tf.setForeground(Color.BLACK);
        getContentPane().add(name_tf);

        // 성별 선택 콤보박스
        JLabel lGender = new JLabel("성별");
        lGender.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lGender.setBounds(43, 244, 50, 15);
        lGender.setForeground(Color.WHITE);
        getContentPane().add(lGender);

        gender_cb = new JComboBox<>(new String[]{"남", "여"});
        gender_cb.setBounds(105, 232, 278, 40);
        gender_cb.setFont(font);
        gender_cb.setBackground(new Color(240, 240, 240));
        gender_cb.setForeground(Color.BLACK);
        getContentPane().add(gender_cb);

        // 전화번호 입력 필드
        JLabel lPhoneNum = new JLabel("전화번호");
        lPhoneNum.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        lPhoneNum.setBounds(43, 304, 74, 15);
        lPhoneNum.setForeground(Color.WHITE);
        getContentPane().add(lPhoneNum);

        phoneNum_tf = new JTextField();
        phoneNum_tf.setColumns(10);
        phoneNum_tf.setBounds(105, 290, 278, 40);
        phoneNum_tf.setFont(font);
        phoneNum_tf.setBackground(new Color(240, 240, 240));
        phoneNum_tf.setForeground(Color.BLACK);
        getContentPane().add(phoneNum_tf);

        // 가입 버튼
        JButton btnNewButton = new JButton("가입");
        btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        btnNewButton.setBounds(121, 467, 91, 23);
        btnNewButton.setBackground(new Color(34, 139, 34)); // 버튼 배경색
        btnNewButton.setForeground(Color.WHITE); // 버튼 텍스트 색
        btnNewButton.setBorderPainted(false); // 버튼 경계선 제거
        btnNewButton.setFocusPainted(false); // 버튼 선택 시 테두리 제거
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = id_tf.getText().trim();
                char[] passwordArray = pw_f.getPassword();
                String pw = new String(passwordArray).trim();
                String name = name_tf.getText().trim();
                String gender = (String) gender_cb.getSelectedItem();
                String phoneNum = phoneNum_tf.getText().trim();

                if (id.isEmpty() || pw.isEmpty() || name.isEmpty() || gender.isEmpty() || phoneNum.isEmpty()) {
                    showMessage("모든 정보를 입력해 주세요.");
                    return;
                }

                System.out.println("서버로 명령어 전송: ADD_MEM," + id + "," + pw + "," + name + "," + gender + "," + phoneNum);
                String response = client.sendCommand("ADD_MEM," + id + "," + pw + "," + name + "," + gender + "," + phoneNum);

                showMessage("회원을 등록했습니다.");
                dispose();
            }
        });
        getContentPane().add(btnNewButton);

        // 취소 버튼
        JButton btnNewButton_1 = new JButton("취소");
        btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        btnNewButton_1.setBounds(235, 467, 91, 23);
        btnNewButton_1.setBackground(new Color(220, 20, 60)); // 취소 버튼 배경색
        btnNewButton_1.setForeground(Color.WHITE); // 버튼 텍스트 색
        btnNewButton_1.setBorderPainted(false); // 버튼 경계선 제거
        btnNewButton_1.setFocusPainted(false); // 버튼 선택 시 테두리 제거
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();   
            }
        });
        getContentPane().add(btnNewButton_1);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
