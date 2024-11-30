package admin.window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import foodMenu.controller.FoodMenuController;
import foodMenu.controller.FoodMenuControllerImpl;
import foodMenu.vo.FoodMenuVO;
import foodSort.vo.FoodSortVO;
import foodSort.controller.FoodSortController;
import foodSort.controller.FoodSortControllerImpl;
import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import seat.controller.SeatController;
import seat.controller.SeatControllerImpl;

public class foodInfoUI extends JFrame {

	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldFoodImage;
	private JComboBox<String> comboBoxSort; // 음식 종류 선택용 ComboBox
    static MemberController memberController = new MemberControllerImpl();
    static SeatController seatController = new SeatControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();
    static FoodMenuController foodMenuController = new FoodMenuControllerImpl();
    static FoodSortController foodSortController = new FoodSortControllerImpl();

	public foodInfoUI(String menuId, String sort, String name, int price, String foodImage) {
		initialize(menuId, sort, name, price, foodImage);
	}

	private void initialize(String menuId, String sort, String name, int price, String foodImage) {
		setTitle("메뉴 정보");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// ID Label and Field
		JLabel lblId = new JLabel("메뉴 번호:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblId.setBounds(12, 29, 90, 15);
		getContentPane().add(lblId);
		
		textFieldId = new JTextField(menuId);
		textFieldId.setEditable(false); // ID는 수정 불가
		textFieldId.setBounds(114, 26, 150, 25); // 세로 길이를 21에서 25로 늘림
		textFieldId.setBackground(new Color(230, 230, 230)); // 흐릿하게 보이도록 배경색 설정
		textFieldId.setDisabledTextColor(new Color(150, 150, 150)); // 텍스트 색상을 흐리게 설정
		textFieldId.setEnabled(false); // 비활성화 상태로 설정
		getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		// Password Label and Field 수정
		JLabel lblSort = new JLabel("종류:");
		lblSort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSort.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblSort.setBounds(12, 66, 90, 15);
		getContentPane().add(lblSort);

		comboBoxSort = new JComboBox<>();
		comboBoxSort.setSelectedItem(sort); // 초기값 설정
		comboBoxSort.setBounds(114, 63, 150, 25); // 세로 길이를 21에서 25로 늘림
		
		// 음식 종류 데이터를 가져와 ComboBox에 추가
        FoodSortVO fsVO = new FoodSortVO();
        
        List<FoodSortVO> foodSorts = foodSortController.listFoodSort(fsVO);
        for (FoodSortVO foodSort : foodSorts) {
        	comboBoxSort.addItem(foodSort.getName()); // 종류 이름 추가
        }
        
        comboBoxSort.setSelectedItem(sort);
		getContentPane().add(comboBoxSort);

		
		// Name Label and Field
		JLabel lblName = new JLabel("이름:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblName.setBounds(12, 103, 90, 15);
		getContentPane().add(lblName);
		
		textFieldName = new JTextField(name);
		textFieldName.setBounds(114, 100, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		// 가격 부분 텍스트필드로 변경
		JLabel lblPrice = new JLabel("가격:");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblPrice.setBounds(12, 140, 90, 15);
		getContentPane().add(lblPrice);

		textFieldPrice = new JTextField(String.valueOf(price));
		textFieldPrice.setBounds(114, 137, 150, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);

		
		// 음식 사진 파일경로
		JLabel lblFoodImage = new JLabel("음식사진:");
		lblFoodImage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFoodImage.setFont(new Font("나눔고딕", Font.BOLD, 12));
		lblFoodImage.setBounds(12, 177, 90, 15);
		getContentPane().add(lblFoodImage);

		textFieldFoodImage = new JTextField(foodImage);
		textFieldFoodImage.setBounds(114, 174, 200, 25); // 세로 길이를 21에서 25로 늘림
		getContentPane().add(textFieldFoodImage);
		textFieldFoodImage.setColumns(30);

		// 파일 선택 버튼 추가
		JButton btnSelectFile = new JButton("찾아보기");
		btnSelectFile.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnSelectFile.setBounds(320, 174, 100, 25); // 버튼 위치와 크기 설정
		btnSelectFile.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // JFileChooser 생성
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 파일만 선택 가능
		        int returnValue = fileChooser.showOpenDialog(null); // 파일 선택창 열기
		        
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            // 선택된 파일의 경로를 텍스트 필드에 설정
		            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
		            textFieldFoodImage.setText(selectedFilePath);
		        }
		    }
		});
		getContentPane().add(btnSelectFile);

		
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
		
		// 취소 버튼
		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("나눔고딕", Font.BOLD, 12));
		btnCancel.setBackground(new Color(255, 128, 128)); // 빨간색 버튼
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
				foodManage uM = new foodManage();
                uM.showUI();
			}
		});
		btnCancel.setBounds(121, 228, 97, 25); // 세로 길이를 23에서 25로 늘림
		getContentPane().add(btnCancel);
		
	}

	private void saveUserInfo() {
		String foodNum = textFieldId.getText();
		String foodSort = (String) comboBoxSort.getSelectedItem();
		String foodName = textFieldName.getText();
		int foodPrice = Integer.parseInt(textFieldPrice.getText());
		String foodImage = textFieldFoodImage.getText(); // 음식 사진 경로 가져오기
		
		FoodSortVO modSort = new FoodSortVO();
		modSort.setName(foodSort);
		List<FoodSortVO> sortList = foodSortController.listFoodSort(modSort);
		
		// sortList가 1개만 존재한다고 가정
		int sortId = -1; // 기본값 설정

		if (sortList != null && !sortList.isEmpty()) {
		    // 첫 번째 요소의 ID 값을 추출
		    sortId = sortList.get(0).getSortId(); // getId() 메서드는 int를 반환한다고 가정
		}

		FoodMenuVO modFood = new FoodMenuVO();
		modFood.setMenuId(foodNum);
		modFood.setSort(String.valueOf(sortId));
		modFood.setName(foodName);
		modFood.setPrice(foodPrice);
		modFood.setImagePath(foodImage);
        foodMenuController.modFoodMenu(modFood);
        
        foodManage uM = new foodManage();
        uM.showUI();
		
		// 저장 후 창 닫기
		JOptionPane.showMessageDialog(this, "정보가 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
