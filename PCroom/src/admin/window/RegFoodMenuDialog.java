package admin.window;

import javax.swing.*;
import java.util.List;


import foodMenu.controller.FoodMenuController;
import foodMenu.controller.FoodMenuControllerImpl;
import foodSort.controller.FoodSortController;
import foodSort.controller.FoodSortControllerImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import member.controller.MemberController;
import member.controller.MemberControllerImpl;
import member.vo.MemberVO;
import remainTime.controller.RemainTimeController;
import remainTime.controller.RemainTimeControllerImpl;
import remainTime.vo.RemainTimeVO;

import foodSort.vo.FoodSortVO; // MemberVO 클래스 임포트
import foodSort.controller.FoodSortController; // RemainTimeController 인터페이스 임포트
import foodSort.controller.FoodSortControllerImpl; // RemainTissmeControllerImpl 클래스 임포트

import foodMenu.controller.FoodMenuController; // SeatController 인터페이스 임포트
import foodMenu.controller.FoodMenuControllerImpl; // SeatControllerImpl 클래스 임포트
import foodMenu.vo.FoodMenuVO; // SeatVO 클래스 임포트



public class RegFoodMenuDialog extends JDialog {

    private JTextField menuIdField;
    private JTextField menuNameField;
    private JTextField menuTypeField;
    private JTextField menuPriceField;
    private JPasswordField pwField;
    
    static MemberController memberController = new MemberControllerImpl();
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl();
    static FoodSortController foodSortController = new FoodSortControllerImpl(); // 회원 컨트롤러 인스턴스 생성
    static FoodMenuController foodMenuController = new FoodMenuControllerImpl();
    
    private JTextField photoPathField; // 사진 경로 필드
    private JComboBox<String> foodTypeComboBox; // 음식 종류 선택용 ComboBox



    public RegFoodMenuDialog() {
        initialize();
    }

    private void initialize() {
    	setTitle("메뉴 등록창");
        setBounds(100, 100, 460, 550);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // 한글 폰트 설정
        Font labelFont = new Font("맑은 고딕", Font.PLAIN, 14);

        // 다음 메뉴 ID 생성
        int nextMenuId = getNextMenuId();
        
        // 메뉴 번호 라벨
        JLabel lMenuId = new JLabel("메뉴번호:");
        lMenuId.setBounds(43, 50, 80, 30);
        lMenuId.setFont(labelFont);
        getContentPane().add(lMenuId);

        // 메뉴 번호 텍스트 필드 (초기값 설정 및 수정 불가능하게 설정)
        menuIdField = new JTextField(String.valueOf(nextMenuId));  // 초기값으로 nextMenuId 설정
        menuIdField.setBounds(130, 50, 250, 30);
        menuIdField.setEditable(false);  // 읽기 전용으로 설정
        getContentPane().add(menuIdField);

//        // Menu Type
//        JLabel lMenuType = new JLabel("종류:");
//        lMenuType.setBounds(43, 100, 80, 30);
//        lMenuType.setFont(labelFont);
//        getContentPane().add(lMenuType);
//        
//        menuTypeField = new JTextField();
//        menuTypeField.setBounds(130, 100, 250, 30);
//        getContentPane().add(menuTypeField);
        
     // 음식 종류 라벨
        JLabel lFoodType = new JLabel("종류:");
        lFoodType.setBounds(43, 100, 80, 30);
        lFoodType.setFont(labelFont);
        getContentPane().add(lFoodType);

        // 음식 종류 ComboBox
        foodTypeComboBox = new JComboBox<>();
        foodTypeComboBox.setBounds(130, 100, 250, 30);
        getContentPane().add(foodTypeComboBox);

        // 음식 종류 데이터를 가져와 ComboBox에 추가
        FoodSortVO fsVO = new FoodSortVO();
        
        List<FoodSortVO> foodSorts = foodSortController.listFoodSort(fsVO);
        for (FoodSortVO foodSort : foodSorts) {
            foodTypeComboBox.addItem(foodSort.getName()); // 종류 이름 추가
        }


        // Menu Name
        JLabel lMenuName = new JLabel("이름:");
        lMenuName.setBounds(43, 150, 80, 30);
        lMenuName.setFont(labelFont);
        getContentPane().add(lMenuName);
        
        menuNameField = new JTextField();
        menuNameField.setBounds(130, 150, 250, 30);
        getContentPane().add(menuNameField);
       

        // Menu Price
        JLabel lMenuPrice = new JLabel("가격:");
        lMenuPrice.setBounds(43, 200, 80, 30);
        lMenuPrice.setFont(labelFont);
        getContentPane().add(lMenuPrice);
        
        menuPriceField = new JTextField();
        menuPriceField.setBounds(130, 200, 250, 30);
        getContentPane().add(menuPriceField);
        
     // 사진 경로 라벨
        JLabel lPhotoPath = new JLabel("사진 경로:");
        lPhotoPath.setBounds(43, 250, 80, 30);
        lPhotoPath.setFont(labelFont);
        getContentPane().add(lPhotoPath);

        // 사진 경로 텍스트 필드
        photoPathField = new JTextField();
        photoPathField.setBounds(130, 250, 250, 30);
        photoPathField.setEditable(false); // 경로는 선택만 가능하게 설정
        getContentPane().add(photoPathField);

        // 사진 선택 버튼
        JButton selectPhotoButton = new JButton("사진 선택");
        selectPhotoButton.setBounds(130, 300, 120, 30);
        selectPhotoButton.setBackground(new Color(70, 130, 180));
        selectPhotoButton.setForeground(Color.WHITE);
        selectPhotoButton.setFont(labelFont);
        selectPhotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("사진 선택");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // 파일 선택 필터 (이미지 파일만)
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(java.io.File file) {
                        String filename = file.getName().toLowerCase();
                        return file.isDirectory() || filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg");
                    }

                    public String getDescription() {
                        return "이미지 파일 (*.jpg, *.jpeg, *.png)";
                    }
                });

                int result = fileChooser.showOpenDialog(RegFoodMenuDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    photoPathField.setText(selectedFile.getAbsolutePath()); // 경로 표시
                }
            }
        });
        getContentPane().add(selectPhotoButton);
        

        // Register Button
        JButton registerButton = new JButton("등록");
        registerButton.setBounds(120, 450, 100, 30);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(labelFont);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = menuIdField.getText().trim();
                //String type = menuTypeField.getText().trim();
                // ComboBox에서 선택된 음식 종류 가져오기
                String selectedType = (String) foodTypeComboBox.getSelectedItem();
                String name = menuNameField.getText().trim();
                String price = menuPriceField.getText().trim();
                String photoPath = photoPathField.getText().trim();

                if (photoPath.isEmpty()) { // 사진 경로 유효성 검사
                    showMessage("사진을 선택하세요.");
                    return;
                }
                
                FoodSortVO foodsort = new FoodSortVO();
                foodsort.setName(selectedType);
                // 음식 종류 리스트 가져오기 (결과는 리스트로 반환)
                List<FoodSortVO> foodSortList = foodSortController.listFoodSort(foodsort);

                // 첫 번째 결과에서 SortId 가져오기
                int sortId = -1; // 기본값 (-1: 에러나 비정상 상태를 나타냄)
                if (!foodSortList.isEmpty()) {
                    sortId = foodSortList.get(0).getSortId(); // 첫 번째 결과의 SortId 추출
                } else {
                    JOptionPane.showMessageDialog(null, "선택한 음식 종류를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return; // 종류가 없으면 추가 작업 중단
                }
                
                FoodMenuVO foodmenu = new FoodMenuVO();
                foodmenu.setMenuId(id);
                foodmenu.setName(name);
                foodmenu.setSort(String.valueOf(sortId));
                foodmenu.setPrice(Integer.parseInt(price)); // 가격을 숫자로 변환
                foodmenu.setImagePath(photoPath);
                
                foodMenuController.regFoodMenu(foodmenu);
                
                showMessage("메뉴를 등록했습니다.");
                dispose();
                
                foodManage uM = new foodManage();
                uM.showUI();
            }
        });
        getContentPane().add(registerButton);

        // Cancel Button
        JButton cancelButton = new JButton("취소");
        cancelButton.setBounds(230, 450, 100, 30);
        cancelButton.setBackground(new Color(255, 69, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(labelFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                foodManage uM = new foodManage();
                uM.showUI();
            }
        });
        getContentPane().add(cancelButton);
    }
    
    // 다음 메뉴 ID를 가져오는 헬퍼 메서드
    private int getNextMenuId() {
        int lastMenuId = 0; // 기존 메뉴 ID가 없을 경우 기본값으로 0 설정

        // foodMenu 리스트나 마지막 메뉴 ID를 가져오는 메서드가 있다고 가정
        FoodMenuVO foodmenuVO = new FoodMenuVO();
        
        List<FoodMenuVO> foodMenus = foodMenuController.listFoodMenu(foodmenuVO);  // 이 메서드가 존재한다고 가정
        if (!foodMenus.isEmpty()) {
            lastMenuId = Integer.parseInt(foodMenus.get(foodMenus.size() - 1).getMenuId());  // 마지막 메뉴 ID를 int로 변환하여 가져오기
        }

        return lastMenuId + 1;  // 1을 더해서 다음 메뉴 ID 반환
    }
    

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
