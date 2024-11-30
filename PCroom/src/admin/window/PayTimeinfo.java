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

public class PayTimeinfo extends JDialog {

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

    public PayTimeinfo(String payment, int timeNum, int seconds, int cost) {
        this.payment = payment;
        initialize(timeNum, seconds, cost);
    }

    private void initialize(int timeNum, int seconds, int cost) {
        setTitle("요금제 등록");
        setBounds(100, 100, 320, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        Font labelFont = new Font("맑은 고딕", Font.PLAIN, 12);

        // 기존 라벨 추가 (TimeNum, Payment, Time, Cost)
        JLabel timeNumLabel = new JLabel("시간 번호:");
        timeNumLabel.setBounds(20, 20, 60, 25);
        timeNumLabel.setFont(labelFont);
        getContentPane().add(timeNumLabel);

        JLabel paymentLabel = new JLabel("결제 방법:");
        paymentLabel.setBounds(20, 60, 60, 25);
        paymentLabel.setFont(labelFont);
        getContentPane().add(paymentLabel);

        JLabel timeLabel = new JLabel("시간:");
        timeLabel.setBounds(20, 100, 60, 25);
        timeLabel.setFont(labelFont);
        getContentPane().add(timeLabel);
        
     // 분, 시간 입력 필드 옆에 단위 표시 라벨
        JLabel hourLabel = new JLabel("시간");
        hourLabel.setBounds(140, 100, 30, 25);
        hourLabel.setFont(labelFont);
        getContentPane().add(hourLabel);

        JLabel minuteLabel = new JLabel("분");
        minuteLabel.setBounds(230, 100, 30, 25);
        minuteLabel.setFont(labelFont);
        getContentPane().add(minuteLabel);

        JLabel priceLabel = new JLabel("금액:");
        priceLabel.setBounds(20, 140, 60, 25);
        priceLabel.setFont(labelFont);
        getContentPane().add(priceLabel);
        
        // "원" 단위 레이블
        JLabel priceUnitLabel = new JLabel("원");
        priceUnitLabel.setBounds(275, 140, 30, 25);  // "원" 라벨 위치
        priceUnitLabel.setFont(labelFont);
        getContentPane().add(priceUnitLabel);

        // 시간 번호 필드
        timeNumField = new JTextField(String.valueOf(timeNum));
        timeNumField.setBounds(90, 20, 200, 25);
        timeNumField.setEditable(false);
        getContentPane().add(timeNumField);

        // 결제 방법 콤보박스
        paymentComboBox = new JComboBox<>();
        paymentComboBox.setBounds(90, 60, 200, 25);
        getContentPane().add(paymentComboBox);
        paymentComboBox.addItem(payment);
        paymentComboBox.setEnabled(false);

        // 시간 필드 (시간, 분)
        menuHourField = new JTextField(String.valueOf(seconds / 3600));
        menuHourField.setBounds(90, 100, 45, 25);
        getContentPane().add(menuHourField);

        menuMinuteField = new JTextField(String.valueOf((seconds % 3600) / 60));
        menuMinuteField.setBounds(175, 100, 45, 25);
        getContentPane().add(menuMinuteField);

        // 금액 필드
        menuPriceField = new JTextField(String.valueOf(cost));
        menuPriceField.setBounds(90, 140, 180, 25);
        getContentPane().add(menuPriceField);

        // 수정 버튼
        JButton registerButton = new JButton("수정");
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

                int seconds = Integer.parseInt(hour) * 3600 + Integer.parseInt(minute) * 60;

                TimeMenuVO timeMenu = new TimeMenuVO();
                timeMenu.setTimeNum(Integer.parseInt(timeNum));
                timeMenu.setPayment(payment);
                timeMenu.setAddTime(seconds);
                timeMenu.setCost(Integer.parseInt(cost));

                timeMenuController.modTimeMenu(timeMenu);
                showMessage("요금제를 수정했습니다.");
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


    
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}