package admin.window;

import javax.swing.*;

import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import member.window.RegMemDialog;
import chatNetwork.Client;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegMemDialog2 extends JDialog {

    private JTextField id_tf;
    private JPasswordField pw_f;
    private JTextField name_tf;
    private JTextField gender_tf;
    private JTextField phoneNum_tf;
    private JComboBox<String> gender_cb;
    static MemberController memberController = new MemberControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();

    
    public RegMemDialog2() {
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
            	
            	String idToCheck = id_tf.getText().trim();  // 클라이언트에서 보낸 ID
                
                // 중복된 ID가 있는지 확인하는 로직
                List<MemberVO> members = memberController.listMember(new MemberVO());  // 모든 회원 리스트를 조회
                
                boolean isIdAvailable = true;  // 기본값은 ID가 사용 가능하다고 가정
                for (MemberVO member11 : members) {
                    // 만약 동일한 ID가 존재하면 중복된 ID로 처리
                    if (member11.getId().equals(idToCheck)) {
                        isIdAvailable = false;
                        break;  // 중복된 ID를 찾았으면 반복을 종료
                    }
                }
                
                // 중복 여부에 따라 응답 전송
                if (isIdAvailable) {
                	showMessage("사용가능한 아이디입니다.");
                } else {
                	showMessage("중복되는 아이디입니다.");
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
                String password = new String(passwordArray);
                String pw = new String(passwordArray).trim();
                String name = name_tf.getText().trim();
                String gender = (String) gender_cb.getSelectedItem();
                String phoneNum = phoneNum_tf.getText().trim();

                MemberVO member = new MemberVO();
                member.setId(id);
                member.setPw(password);
                member.setName(name);
                member.setGender(gender);
                member.setPhoneNum(phoneNum);

                memberController.regMember(member);
                
                RemainTimeVO remainTime = new RemainTimeVO();
                int initremainTime = 10; // 회원가입했을 때 시간을 0초로 초기화
                remainTime.setUserId(id);
                remainTime.setRemainTime(initremainTime);
                remainTimeController.regRemainTime(remainTime);
                showMessage("회원을 등록했습니다.");
                dispose();
                userManage uM = new userManage();
                uM.showUI();
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
                userManage um = new userManage();
                um.showUI(); // 생성한 객체의 창을 표시
            }
        });
        getContentPane().add(btnNewButton_1);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}