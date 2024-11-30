package admin.window;

import javax.swing.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import foodMenu.controller.FoodMenuController;
import foodMenu.controller.FoodMenuControllerImpl;
import foodSort.controller.FoodSortController;
import foodSort.controller.FoodSortControllerImpl;
import foodMenu.vo.FoodMenuVO;
import foodSort.vo.FoodSortVO;
import timeMenu.vo.TimeMenuVO;
import timeMenu.controller.TimeMenuController;
import timeMenu.controller.TimeMenuControllerImpl;

public class RegPayTimeDialog extends JDialog {

    private JTextField timeNumField;
    private JTextField menuNameField;
    private JTextField menuPriceField;
    private JTextField menuHourField;
    private JTextField menuMinuteField;
    private String payment;
    private JComboBox<String> paymentComboBox;

    static FoodMenuController foodMenuController = new FoodMenuControllerImpl();
    static FoodSortController foodSortController = new FoodSortControllerImpl();
    static TimeMenuController timeMenuController = new TimeMenuControllerImpl();

    public RegPayTimeDialog(String payment) {
    	this.payment = payment;
        initialize();
    }

    private void initialize() {
        setTitle("요금제 등록");
        setBounds(100, 100, 320, 400); // 창 크기를 줄임
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // 한글 폰트 설정
        Font labelFont = new Font("맑은 고딕", Font.PLAIN, 12);

        // 다음 메뉴 ID 생성
        int nextTimeNum = getNextTimeNum();

        // 메뉴 번호
        JLabel lMenuId = new JLabel("번호:");
        lMenuId.setBounds(20, 20, 60, 25); // 위치와 크기 축소
        lMenuId.setFont(labelFont);
        getContentPane().add(lMenuId);

        timeNumField = new JTextField(String.valueOf(nextTimeNum));
        timeNumField.setBounds(90, 20, 200, 25);
        timeNumField.setEditable(false);
        getContentPane().add(timeNumField);

        // 결제 방식
        JLabel lpaymentType = new JLabel("결제:");
        lpaymentType.setBounds(20, 60, 60, 25);
        lpaymentType.setFont(labelFont);
        getContentPane().add(lpaymentType);

        paymentComboBox = new JComboBox<>();
        paymentComboBox.setBounds(90, 60, 200, 25);
        getContentPane().add(paymentComboBox);

        paymentComboBox.addItem(payment);
     // 콤보박스 비활성화
        paymentComboBox.setEnabled(false);


     // 시간
        JLabel lTime = new JLabel("시간:");
        lTime.setBounds(20, 100, 60, 25);
        lTime.setFont(labelFont);
        getContentPane().add(lTime);

        // 시간 입력 필드
        menuHourField = new JTextField();  // 시간 입력 필드 추가
        menuHourField.setBounds(90, 100, 45, 25);
        getContentPane().add(menuHourField);

        // 분 입력 필드
        menuMinuteField = new JTextField();  // 분 입력 필드 추가
        menuMinuteField.setBounds(175, 100, 45, 25);
        getContentPane().add(menuMinuteField);

        // 분, 시간 입력 필드 옆에 단위 표시 라벨
        JLabel hourLabel = new JLabel("시간");
        hourLabel.setBounds(140, 100, 30, 25);
        hourLabel.setFont(labelFont);
        getContentPane().add(hourLabel);

        JLabel minuteLabel = new JLabel("분");
        minuteLabel.setBounds(230, 100, 30, 25);
        minuteLabel.setFont(labelFont);
        getContentPane().add(minuteLabel);

     // 가격 레이블
        JLabel lMenuPrice = new JLabel("가격:");
        lMenuPrice.setBounds(20, 140, 60, 25);  // 레이블 크기
        lMenuPrice.setFont(labelFont);
        getContentPane().add(lMenuPrice);

        // 가격 입력 필드
        menuPriceField = new JTextField();
        menuPriceField.setBounds(90, 140, 180, 25);  // 텍스트 필드 크기 줄이기
        getContentPane().add(menuPriceField);

        // "원" 단위 레이블
        JLabel priceUnitLabel = new JLabel("원");
        priceUnitLabel.setBounds(275, 140, 30, 25);  // "원" 라벨 위치
        priceUnitLabel.setFont(labelFont);
        getContentPane().add(priceUnitLabel);


        // 등록 버튼
        JButton registerButton = new JButton("등록");
        registerButton.setBounds(60, 300, 80, 30);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(labelFont);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String timeNum = timeNumField.getText().trim();
                String payment = (String) paymentComboBox.getSelectedItem();
                
                String hour = menuHourField.getText().trim();
                String minute = menuMinuteField.getText().trim();
                
                String cost = menuPriceField.getText().trim();
                
                int seconds =  Integer.parseInt(hour) * 3600 + Integer.parseInt(minute)*60;
                
                TimeMenuVO timeMenu = new TimeMenuVO();
                timeMenu.setTimeNum(Integer.parseInt(timeNum));
                timeMenu.setPayment(payment);
                timeMenu.setAddTime(seconds);
                timeMenu.setCost(Integer.parseInt(cost));

                timeMenuController.regTimeMenu(timeMenu);
                showMessage("요금제를 등록했습니다.");
                dispose();
                TimeMenuManage TMM = new TimeMenuManage();
                TMM.setVisible(true);
            }
        });
        getContentPane().add(registerButton);

        // 취소 버튼
        JButton cancelButton = new JButton("취소");
        cancelButton.setBounds(160, 300, 80, 30);
        cancelButton.setBackground(new Color(255, 69, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(labelFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                TimeMenuManage TMM = new TimeMenuManage();
                TMM.setVisible(true);
            	}
            });
        getContentPane().add(cancelButton);
    }

    private int getNextTimeNum() {
        int lastTimeNum = 0;
        TimeMenuVO timeMenuVO = new TimeMenuVO();
        timeMenuVO.setPayment(payment);

        // 현재 시간 번호 리스트 가져오기
        List<TimeMenuVO> nowTimeNum = timeMenuController.listTimeMenu(timeMenuVO);

        // nowTimeNum 값을 출력 (디버깅용)
        System.out.println("현재 timeMenu 리스트:");
        for (TimeMenuVO vo : nowTimeNum) {
            System.out.println(vo); // VO의 toString() 메서드를 활용하여 출력
        }

        // 사용되지 않은 첫 번째 숫자 찾기
        Set<Integer> usedTimeNums = new HashSet<>();
        for (TimeMenuVO vo : nowTimeNum) {
            usedTimeNums.add(vo.getTimeNum());
        }

        // 결제 방식에 따른 시작 번호 설정
        int startNum = (payment.equals("카드")) ? 1 : 9;  // 현금이면 1부터, 카드면 8부터 시작

        // 비어있는 숫자를 찾기
        for (int i = startNum; i <= nowTimeNum.size() + startNum; i++) {
            if (!usedTimeNums.contains(i)) {
                return i;  // 첫 번째로 비어있는 숫자를 반환
            }
        }

        return lastTimeNum + 1;  // 모든 숫자가 채워져 있으면 마지막 번호 + 1
    }




    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
