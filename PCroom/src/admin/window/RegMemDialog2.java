package admin.window;

import javax.swing.*;

import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import chatNetwork.Client;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegMemDialog2 extends JDialog {

    private JTextField id_tf;
    private JPasswordField pw_f;
    private JTextField name_tf;
    private JTextField gender_tf;
    private JTextField phoneNum_tf;
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

        id_tf = new JTextField();
        id_tf.setBounds(105, 42, 278, 40);
        getContentPane().add(id_tf);
        id_tf.setColumns(10);

        pw_f = new JPasswordField();
        pw_f.setBounds(105, 104, 278, 40);
        getContentPane().add(pw_f);

        JLabel lId = new JLabel("아이디");
        lId.setBounds(43, 54, 50, 15);
        getContentPane().add(lId);

        JLabel lPw = new JLabel("비밀번호");
        lPw.setBounds(43, 116, 50, 15);
        getContentPane().add(lPw);

        JButton btnNewButton = new JButton("가입");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = id_tf.getText().trim();
                char[] passwordArray = pw_f.getPassword();
                String password = new String(passwordArray);
                String pw = new String(passwordArray).trim();
                String name = name_tf.getText().trim();
                String gender = gender_tf.getText().trim();
                String phoneNum = phoneNum_tf.getText().trim();

                MemberVO member = new MemberVO();
                member.setId(id);
                member.setPw(password);
                member.setName(name);
                member.setGender(gender);
                member.setPhoneNum(phoneNum);

                //System.out.println("Registering member: " + member1); // 디버깅 출력
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
        btnNewButton.setBounds(121, 467, 91, 23);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("취소");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                userManage uM = new userManage();
                uM.showUI();
                
            }
        });
        btnNewButton_1.setBounds(235, 467, 91, 23);
        getContentPane().add(btnNewButton_1);

        name_tf = new JTextField();
        name_tf.setColumns(10);
        name_tf.setBounds(105, 170, 278, 40);
        getContentPane().add(name_tf);

        JLabel lName = new JLabel("이름");
        lName.setBounds(43, 182, 50, 15);
        getContentPane().add(lName);

        JLabel lGender = new JLabel("성별");
        lGender.setBounds(43, 244, 50, 15);
        getContentPane().add(lGender);

        JLabel lPhoneNum = new JLabel("전화번호");
        lPhoneNum.setBounds(43, 304, 50, 15);
        getContentPane().add(lPhoneNum);

        gender_tf = new JTextField();
        gender_tf.setColumns(10);
        gender_tf.setBounds(105, 232, 278, 40);
        getContentPane().add(gender_tf);

        phoneNum_tf = new JTextField();
        phoneNum_tf.setColumns(10);
        phoneNum_tf.setBounds(105, 290, 278, 40);
        getContentPane().add(phoneNum_tf);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
