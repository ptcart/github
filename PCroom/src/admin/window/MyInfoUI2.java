package admin.window;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.PCroomClientWindow;
import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import chatNetwork.Client;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;
import seat.vo.SeatVO;

public class MyInfoUI2 extends JFrame {

	private JTextField textFieldId;
	private JPasswordField passwordField;
	private JTextField textFieldName;
	private JTextField textFieldPhoneNumber;
	private JComboBox<String> comboBoxGender;
	private int seatNum;
    static MemberController memberController = new MemberControllerImpl();
    static SeatController seatController = new SeatControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MyInfoUI2(String userId, String userName, String userPassword, String userGender, String phoneNumber) {
		initialize(userId, userName, userPassword, userGender, phoneNumber);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userId, String userPassword, String userName, String userGender, String phoneNumber) {
		setTitle("내 정보");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// ID Label and Field
		JLabel lblId = new JLabel("아이디:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblId.setBounds(12, 29, 90, 15);
		getContentPane().add(lblId);
		
		textFieldId = new JTextField(userId);
		textFieldId.setEditable(false); // ID는 수정 불가
		textFieldId.setBounds(114, 26, 150, 25); // 세로 길이를 21에서 25로 늘림
		textFieldId.setBackground(new Color(230, 230, 230)); // 흐릿하게 보이도록 배경색 설정
		textFieldId.setDisabledTextColor(new Color(150, 150, 150)); // 텍스트 색상을 흐리게 설정
		textFieldId.setEnabled(false); // 비활성화 상태로 설정
		getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		// Password Label and Field
		JLabel lblPassword = new JLabel("비밀번호:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblPassword.setBounds(12, 66, 90, 15);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField(userPassword);
		passwordField.setBounds(114, 63, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(passwordField);
		
		// Name Label and Field
		JLabel lblName = new JLabel("이름:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblName.setBounds(12, 103, 90, 15);
		getContentPane().add(lblName);
		
		textFieldName = new JTextField(userName);
		textFieldName.setBounds(114, 100, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		// Gender Label and ComboBox
		JLabel lblGender = new JLabel("성별:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblGender.setBounds(12, 140, 90, 15);
		getContentPane().add(lblGender);
		
		comboBoxGender = new JComboBox<>(new String[]{"남", "여"});
		comboBoxGender.setSelectedItem(userGender); // 현재 성별 설정
		comboBoxGender.setBounds(114, 137, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(comboBoxGender);
		
		// Phone Number Label and Field
		JLabel lblPhoneNumber = new JLabel("전화번호:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblPhoneNumber.setBounds(12, 177, 90, 15);
		getContentPane().add(lblPhoneNumber);

		// 전화번호 필드 하나로 통합
		textFieldPhoneNumber = new JTextField(phoneNumber);
		textFieldPhoneNumber.setBounds(114, 174, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(11);
		
		// Save Button
		JButton btnSave = new JButton("저장");
		btnSave.setFont(new Font("나눔고딕", Font.BOLD, 12));
		btnSave.setBackground(new Color(50, 205, 50)); // 초록색 버튼
		btnSave.setForeground(Color.WHITE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveUserInfo();
			}
		});
		btnSave.setBounds(12, 228, 97, 25); // 세로 길이를 23에서 25로 늘림
		getContentPane().add(btnSave);
		
		// Cancel Button
		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("나눔고딕", Font.BOLD, 12));
		btnCancel.setBackground(new Color(255, 128, 128)); // 빨간색 버튼
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
				userManage uM = new userManage();
                uM.showUI();
			}
		});
		btnCancel.setBounds(121, 228, 97, 25); // 세로 길이를 23에서 25로 늘림
		getContentPane().add(btnCancel);
		
		// Quit(탈퇴) Button
				JButton btnQuit = new JButton("탈퇴");
				btnQuit.setFont(new Font("나눔고딕", Font.BOLD, 12));
				btnQuit.setBackground(new Color(220, 20, 60)); // 빨간색 버튼
				btnQuit.setForeground(Color.WHITE);
				btnQuit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다.", "탈퇴 완료", JOptionPane.INFORMATION_MESSAGE);
						dispose(); // 내 정보 창 닫기						
				        
						String userId = textFieldId.getText();
						// 회원 탈퇴 명령어
						//client.sendCommand("REMOVE_MEM," + userId + "," + seatNum);

						MemberVO removeMem = new MemberVO();
		                RemainTimeVO rTime = new RemainTimeVO();
		                SeatVO _seat = new SeatVO();
		                
		                removeMem.setId(userId);
		                rTime.setUserId(userId);
		                //_seat.setUserId(null);
		                //_seat.setSeatNum(Integer.parseInt(parts[2]));
		                
		                remainTimeController.removeRemainTime(rTime);
		                //나중에 seat dao 에서 userId를 찾아서 좌석이 있으면 아이디를 null로 바꾸게끔 해야함
		                
		                //seatController.modSeat(_seat);
		                memberController.removeMember(removeMem);
		                userManage uM = new userManage();
		                uM.showUI();
						
						
				    }
				});
				btnQuit.setBounds(327, 228, 97, 25); // 세로 길이를 23에서 25로 늘림
				getContentPane().add(btnQuit);
	}
	
	/**
	 * Save user info method.
	 */
	private void saveUserInfo() {
		String userId = textFieldId.getText();
		String userPassword = new String(passwordField.getPassword());
		String userName = textFieldName.getText();
		String userGender = (String) comboBoxGender.getSelectedItem();
		String userPhone = textFieldPhoneNumber.getText();

		MemberVO modMem = new MemberVO();
        modMem.setId(userId);
        modMem.setPw(userPassword);
        modMem.setName(userName);
        modMem.setGender(userGender);
        modMem.setPhoneNum(userPhone);
        memberController.modMember(modMem);
        
        userManage uM = new userManage();
        uM.showUI();
		
		// 저장 후 창 닫기
		JOptionPane.showMessageDialog(this, "정보가 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
