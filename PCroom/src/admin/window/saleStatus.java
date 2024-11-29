package admin.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.*;

import foodMenu.vo.FoodMenuVO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import order.vo.OrderVO;
import orderDetail.vo.OrderDetailVO;
import timeMenu.controller.TimeMenuController;
import timeMenu.controller.TimeMenuControllerImpl;
import timeMenu.vo.TimeMenuVO;
import payTime.controller.PayTimeController;
import payTime.controller.PayTimeControllerImpl;
import payTime.vo.PayTimeVO;

import order.controller.OrderController;
import order.controller.OrderControllerImpl;

import orderDetail.controller.OrderDetailController;
import orderDetail.controller.OrderDetailControllerImpl;

import foodMenu.controller.FoodMenuController;
import foodMenu.controller.FoodMenuControllerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class saleStatus extends JFrame {

    private JComboBox<String> startYearComboBox, startMonthComboBox, startDayComboBox;
    private JComboBox<String> endYearComboBox, endMonthComboBox, endDayComboBox;
    private JLabel cashLabel;
    private JLabel cardLabel;
    private JLabel totalLabel;
    private XChartPanel<CategoryChart> barChartPanel;
    

    private int foodCardCost = 0; // 카드 결제 총액
    private int foodCashCost = 0; // 현금 결제 총액
    private int totalFoodCost = 0;
    
    private int timeMoneyCost = 0;
    private int timeCardCost = 0;
    private int totalTimeCost = 0;
    
 // 클래스 필드로 선언
    private XChartPanel<PieChart> pieChartPanel;
//    private int timePerson = 0;
//    private int foodPerson = 0;
    
    private String formattedStartDate;
    private String formattedEndDate;
	static TimeMenuController timeMenuController = new TimeMenuControllerImpl(); // 회원 컨트롤러 인스턴스 생성
	static PayTimeController payTimeController = new PayTimeControllerImpl();
	static OrderController orderController = new OrderControllerImpl();
	static OrderDetailController orderDetailController = new OrderDetailControllerImpl();
	static FoodMenuController foodMenuController = new FoodMenuControllerImpl();

	
	public saleStatus() {
		
		
		// 윈도우 설정
        setTitle("매출 현황");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null); // 절대 레이아웃 사용
        getContentPane().setBackground(new Color(64, 64, 64)); // 배경색 진한 회색 설정

        // 상단 버튼 영역 설정
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(10, 10, 764, 50);
        topPanel.setBackground(Color.WHITE); // 상단 패널 배경 흰색
        getContentPane().add(topPanel);

        // 시작 날짜 DatePicker
        JLabel startDateLabel = new JLabel("시작 날짜:");
        startDateLabel.setFont(new Font("굴림", Font.PLAIN, 14));
        startDateLabel.setBounds(10, 10, 80, 30);
        topPanel.add(startDateLabel);

        JDatePickerImpl startDatePicker = createDatePicker();
        startDatePicker.setBounds(90, 10, 200, 30);
        topPanel.add(startDatePicker);

        // 종료 날짜 DatePicker
        JLabel endDateLabel = new JLabel("종료 날짜:");
        endDateLabel.setFont(new Font("굴림", Font.PLAIN, 14));
        endDateLabel.setBounds(310, 10, 80, 30);
        topPanel.add(endDateLabel);

        JDatePickerImpl endDatePicker = createDatePicker();
        endDatePicker.setBounds(390, 10, 200, 30);
        topPanel.add(endDatePicker);
        
     // "나가기" 버튼 추가
        JButton exitButton = new JButton("나가기");
        exitButton.setFont(new Font("굴림", Font.PLAIN, 14));
        exitButton.setBounds(664, 10, 90, 30); // 오른쪽 끝에 위치하도록 설정
        exitButton.setBackground(new Color(220, 53, 69)); // 버튼 배경색 (빨간색)
        exitButton.setForeground(Color.WHITE); // 버튼 글자색 (흰색)
        exitButton.setFocusPainted(false); // 버튼 클릭 테두리 제거
        topPanel.add(exitButton);

        // "나가기" 버튼 이벤트 리스너 추가
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null, 
                    "정말 창을 닫으시겠습니까?", 
                    "창 닫기 확인", 
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // 현재 창만 닫기
            }
        });

     // 시작 날짜 선택 후 종료 날짜 활성화
        startDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = (Date) startDatePicker.getModel().getValue();
                if (startDate != null) {
                    endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                    endDatePicker.getModel().setDate(startDate.getYear() + 1900, startDate.getMonth(), startDate.getDate()); // 시작 날짜에 맞춰 초기화
                    endDatePicker.setEnabled(true); // 종료 날짜 활성화
                } 
                else {
                	endDatePicker.setEnabled(false); // 시작 날짜가 없으면 종료 날짜 비활성화
                }
            }
        });
        
     // 중앙 그래프 및 차트 영역
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(10, 70, 764, 300);
        centerPanel.setBackground(Color.WHITE); // 중앙 패널 배경 흰색
        getContentPane().add(centerPanel);

        // 파이 차트 데이터
        String[] pieCategories = {"요금제", "음식"};
        double[] pieValues = {totalTimeCost, totalFoodCost};
        PieChart pieChart = createPieChart(pieCategories, pieValues);
        pieChartPanel = new XChartPanel<>(pieChart);
        pieChartPanel.setBounds(0, 0, 350, 300);
        centerPanel.add(pieChartPanel);

        
//     // 1월부터 12월까지의 데이터 예시
//        List<String> barXData = Arrays.asList(
//            "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06",
//            "2024-07", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12"
//        );
//
//        List<Integer> barYData = Arrays.asList(
//            120000, 130000, 140000, 110000, 125000, 135000, 
//            145000, 150000, 155000, 160000, 170000, 180000
//        );
//
//        
//        
//        CategoryChart barChart = createBarChart("월별 매출", "월", "매출", barXData, barYData);
//        XChartPanel<CategoryChart> barChartPanel = new XChartPanel<>(barChart);
//        barChartPanel.setBounds(360, 0, 404, 300);
//        centerPanel.add(barChartPanel);
        
        endDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = (Date) startDatePicker.getModel().getValue();
                Date endDate = (Date) endDatePicker.getModel().getValue();
                
//                LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
                
                
                Map<LocalDate, Integer> dailyCosts = new TreeMap<>(); // 날짜별 비용 저장 (TreeMap으로 정렬된 상태 유지)
                if (startDate == null) {
                    JOptionPane.showMessageDialog(topPanel, "시작 날짜를 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                    endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                }
                
                
                
                if (startDate != null && endDate != null) {
                	
                	LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 종료 날짜가 시작 날짜보다 이전일 경우
                    if (endDate.before(startDate)) {
                        JOptionPane.showMessageDialog(topPanel, "종료 날짜는 시작 날짜보다 앞설 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                        endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                    }
                    else {
                        // Date를 LocalDateTime으로 변환
                        LocalDateTime startLocalDateTime = convertToLocalDateTime(startDate);
                        LocalDateTime endLocalDateTime = convertToLocalDateTime(endDate);
                     // 시작 날짜는 00:00:00으로 설정
                        startLocalDateTime = startLocalDateTime.toLocalDate().atStartOfDay(); // 00:00:00

                        // 종료 날짜는 23:59:59.999로 설정
                        endLocalDateTime = endLocalDateTime.toLocalDate().atTime(LocalTime.MAX); // 23:59:59.999999999

                        // LocalDateTime 출력
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // 출력용 변수에 저장
                        formattedStartDate = startLocalDateTime.format(formatter);
                        formattedEndDate = endLocalDateTime.format(formatter);

                        // 출력
                        System.out.println("시작 날짜 (LocalDateTime): " + formattedStartDate);
                        System.out.println("종료 날짜 (LocalDateTime): " + formattedEndDate);

                        // 날짜 범위 내의 데이터를 가져오기
                        PayTimeVO pt = new PayTimeVO();
                        List<PayTimeVO> ptList = payTimeController.listPayTime(pt);
                        
                        

                        int timeCardCost = 0;
                        int timeMoneyCost = 0;
                        int foodCardCost = 0;
                        int foodCashCost = 0;

                        for (PayTimeVO payTimeVO : ptList) {
                            // payTime 값을 LocalDateTime으로 변환
                            LocalDateTime payTime = payTimeVO.getPayTime();
                            // 날짜만 추출 (LocalDate)
                            LocalDate date = payTimeVO.getPayTime().toLocalDate();

                            // 날짜 범위 필터링
                            if (!payTime.isBefore(startLocalDateTime) && !payTime.isAfter(endLocalDateTime)) {
                                // getTimeNum() 값을 이용하여 TimeMenuVO 객체를 생성
                                int timeNum = payTimeVO.getTimeNum();
                                TimeMenuVO tm = new TimeMenuVO();
                                tm.setTimeNum(timeNum);

                                // 비용 정보를 가져오기
                                List<TimeMenuVO> tmList = timeMenuController.listTimeCost(tm);
                                if (!tmList.isEmpty()) {
                                    String payment = tmList.get(0).getPayment();
                                    int cost = tmList.get(0).getCost();

                                    // 날짜별 비용 누적
                                    dailyCosts.put(date, dailyCosts.getOrDefault(date, 0) + cost);
                                    
                                    if (payment.equals("카드")) {
                                        timeCardCost += cost;
                                    } else if (payment.equals("현금")) {
                                        timeMoneyCost += cost;
                                    }
                                    cost = 0;
                                }
                            }
                        }

                        // 총 비용 계산
                        int totalTimeCost = timeCardCost + timeMoneyCost;
                        
                        
                     // 날짜별 비용을 저장할 TreeMap 생성
                        //Map<LocalDate, Integer> dailyCosts = new TreeMap<>();

                        // 주문 목록 조회
                        OrderVO order = new OrderVO();
                        List<OrderVO> listOrder = orderController.listOrder(order);

                        // 주문 시간 기반 필터링 및 처리
                        for (OrderVO orderItem : listOrder) {
                            LocalDateTime orderTime = orderItem.getOrderTime(); // 주문 시간 가져오기
                            int state = orderItem.getState(); // 주문 상태 가져오기

                            // 주문 시간이 시작 ~ 종료 시간 사이이고 상태가 1(완료)인지 확인
                            if (!orderTime.isBefore(startLocalDateTime) && !orderTime.isAfter(endLocalDateTime) && state == 1) {
                                int orderId = orderItem.getOrderId(); // 주문 ID 가져오기
                                String payment = orderItem.getPayment(); // 결제 방식 가져오기

                                // 해당 주문의 상세 정보 조회
                                OrderDetailVO orderDetail = new OrderDetailVO();
                                orderDetail.setOrderId(orderId); // 주문 ID로 상세 정보 조회
                                List<OrderDetailVO> listOrderDetail = orderDetailController.listDetail(orderDetail);
                                
                                // 주문 상세 항목 처리
                                for (OrderDetailVO detail : listOrderDetail) {
                                    String menuId = detail.getMenuId();
                                    int menuCount = detail.getCount(); // 메뉴 수량 가져오기

                                    // 음식 메뉴 이름과 가격을 가져오기 위한 객체 생성
                                    FoodMenuVO foodMenu = new FoodMenuVO();
                                    foodMenu.setMenuId(menuId); // menuId 설정

                                    // 음식 메뉴 정보 조회
                                    List<FoodMenuVO> foodMenuResult = foodMenuController.listFoodMenu(foodMenu);

                                    // 음식 메뉴 이름, 수량, 가격 추가
                                    if (foodMenuResult != null && !foodMenuResult.isEmpty()) {
                                        for (FoodMenuVO menu : foodMenuResult) {
                                            int menuPrice = menu.getPrice(); // 단가 가져오기
                                            int itemTotalPrice = menuPrice * menuCount; // 항목별 총 가격 계산

                                            // 결제 방식에 따라 가격 누적
                                            if ("카드".equals(payment)) {
                                                foodCardCost += itemTotalPrice;
                                            } else if ("현금".equals(payment)) {
                                                foodCashCost += itemTotalPrice;
                                            }

                                            // 주문 시간에서 날짜만 추출 (LocalDate로 변환)
                                            LocalDate orderDate = orderTime.toLocalDate(); 

                                            // 날짜별로 비용 누적
                                            dailyCosts.put(orderDate, dailyCosts.getOrDefault(orderDate, 0) + itemTotalPrice);
                                            itemTotalPrice= 0;
                                        }
                                    } 
                                }
                            }
                        }



        
                        // 총 비용 계산
                        int totalFoodCost = foodCardCost + foodCashCost;

                     // JLabel 업데이트
                        cashLabel.setText("요금제 " + totalTimeCost + "원");
                        // 결과 출력
                        System.out.println("카드 비용 합계: " + timeCardCost);
                        System.out.println("현금 비용 합계: " + timeMoneyCost);
                        System.out.println("총 비용 합계: " + totalTimeCost);
                        
                     // JLabel 업데이트
                        cardLabel.setText("음식 " + totalFoodCost + "원");
                     // 결과 출력
                        System.out.println("음식 카드 비용 합계: " + foodCardCost + "원");
                        System.out.println("음식 현금 비용 합계: " + foodCashCost + "원");
                        System.out.println("음식 총 비용 합계: " + totalFoodCost + "원");
                        
                        totalLabel.setText("총 "+ (totalTimeCost + totalFoodCost) +"원");
                        
                     // 날짜별 비용 출력
                        System.out.println("날짜별 비용:");
                        for (Map.Entry<LocalDate, Integer> entry : dailyCosts.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
                        }
                        
                     // 기존 파이 차트 제거
                        if (pieChartPanel != null) {
                            centerPanel.remove(pieChartPanel); // 기존 차트 패널이 null이 아니면 삭제
                            //endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                        }
                        
                     // 새로운 파이 차트 데이터로 업데이트
                        String[] updatedPieCategories = {"요금제", "음식"};
                        double[] updatedPieValues = {totalTimeCost, totalFoodCost};

                        

                        // 새로 업데이트된 파이 차트 생성 및 추가
                        PieChart updatedPieChart = createPieChart(updatedPieCategories, updatedPieValues);
                        pieChartPanel = new XChartPanel<>(updatedPieChart);
                        pieChartPanel.setBounds(0, 0, 350, 300);
                        centerPanel.add(pieChartPanel);

                        // 패널 다시 그리기
                        centerPanel.revalidate();
                        centerPanel.repaint();
                        
                        
                        
                     // 날짜 차이 계산 (기간이 30일 이내인지 확인)
                        long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

                        // X축 데이터와 Y축 데이터를 저장할 리스트
                        List<String> barXData = new ArrayList<>();
                        List<Integer> barYData = new ArrayList<>();

                        if (barChartPanel != null) {
                            centerPanel.remove(barChartPanel); // 기존 차트 패널이 null이 아니면 삭제
                        }

                        if (daysBetween <= 30) {
                            // 30일 이내면 일별 막대 그래프
                            for (Map.Entry<LocalDate, Integer> entry : dailyCosts.entrySet()) {
                                barXData.add(entry.getKey().getDayOfMonth() + ""); // X축: 일 (날짜만 표시)
                                barYData.add(entry.getValue()); // Y축: 비용
                                
                                
                            }
                            if (barYData.isEmpty()) {
                                System.out.println("Error: barYData is empty!"); // 디버깅용 메시지
                                if (barChartPanel != null) {
                                    centerPanel.remove(barChartPanel);  // 기존 그래프 패널 제거
                                }
                                barYData.add(0); // 기본값 추가 (예: 0)
                                barXData.add("No Data"); // X축에 기본값 추가
                                endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                            }

                         // 차트 생성
                            CategoryChart barChart = createBarChart("일별 매출", "일", "매출", barXData, barYData);

                         // Y축 단위를 간소화
                            barChart.getStyler().setYAxisDecimalPattern("#.##만"); // 예: '10만', '20만' 형식으로 표시
                            // X축 항목 수에 따라 막대 너비 조정
                            if (barXData.size() == 1) {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 1개의 항목일 때 막대 너비를 30%로 설정
                            } else {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 기본적으로 막대 너비를 80%로 설정
                            }

                            barChartPanel = new XChartPanel<>(barChart);
                            barChartPanel.setBounds(360, 0, 404, 300);
                            centerPanel.add(barChartPanel);

                        } else if (daysBetween <= 365) {
                            // 30일~1년 사이면 월별 막대 그래프
                            Map<String, Integer> monthlyCosts = new HashMap<>();
                            for (Map.Entry<LocalDate, Integer> entry : dailyCosts.entrySet()) {
                                LocalDate date = entry.getKey();
                                int cost = entry.getValue();

                                // 연도 + 월을 문자열로 생성
                                String monthKey = date.getYear() + "-" + String.format("%02d", date.getMonthValue());

                                // 월별 비용 누적
                                monthlyCosts.put(monthKey, monthlyCosts.getOrDefault(monthKey, 0) + cost);
                            }

                            // X축 데이터 (월) -> "연도-월" 형식으로 표시
                            for (Map.Entry<String, Integer> entry : monthlyCosts.entrySet()) {
                                barXData.add(entry.getKey()); // X축: "YYYY-MM"
                                barYData.add(entry.getValue());
                            }
                            
                            if (barYData.isEmpty()) {
                                System.out.println("Error: barYData is empty!"); // 디버깅용 메시지
                                if (barChartPanel != null) {
                                    centerPanel.remove(barChartPanel);  // 기존 그래프 패널 제거
                                }
                                barYData.add(0); // 기본값 추가 (예: 0)
                                barXData.add("No Data"); // X축에 기본값 추가
                                endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                            }

                         // 차트 생성
                            CategoryChart barChart = createBarChart("월별 매출", "월", "매출", barXData, barYData);

                         // Y축 단위를 간소화
                            barChart.getStyler().setYAxisDecimalPattern("#.##만"); // 예: '10만', '20만' 형식으로 표시
                            // X축 항목 수에 따라 막대 너비 조정
                            if (barXData.size() == 1) {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 1개의 항목일 때 막대 너비를 30%로 설정
                            } else {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 기본적으로 막대 너비를 80%로 설정
                            }

                            barChartPanel = new XChartPanel<>(barChart);
                            barChartPanel.setBounds(360, 0, 404, 300);
                            centerPanel.add(barChartPanel);

                        } else {
                            // 1년 이상이면 연별 막대 그래프
                            Map<Integer, Integer> yearlyCosts = new HashMap<>();
                            for (Map.Entry<LocalDate, Integer> entry : dailyCosts.entrySet()) {
                                LocalDate date = entry.getKey();
                                int cost = entry.getValue();

                                // 연도를 키로 사용하여 연별 비용 누적
                                yearlyCosts.put(date.getYear(), yearlyCosts.getOrDefault(date.getYear(), 0) + cost);
                            }

                            // X축 데이터 (연도) -> 연도만 표시
                            for (Map.Entry<Integer, Integer> entry : yearlyCosts.entrySet()) {
                                barXData.add(entry.getKey() + ""); // X축: 연도만 표시
                                barYData.add(entry.getValue());
                            }
                            
                            if (barYData.isEmpty()) {
                                System.out.println("Error: barYData is empty!"); // 디버깅용 메시지
                                // 그래프를 제거하고 날짜 선택기 초기화
                                if (barChartPanel != null) {
                                    centerPanel.remove(barChartPanel);  // 기존 그래프 패널 제거
                                }
                                barYData.add(0); // 기본값 추가 (예: 0)
                                barXData.add("No Data"); // X축에 기본값 추가
                                endDatePicker.getModel().setSelected(false); // 종료 날짜 초기화
                            }

                         // 차트 생성
                            CategoryChart barChart = createBarChart("연별 매출", "연도", "매출", barXData, barYData);
                         // Y축 단위를 간소화
                            barChart.getStyler().setYAxisDecimalPattern("#.##만"); // 예: '10만', '20만' 형식으로 표시
                            // X축 항목 수에 따라 막대 너비 조정
                            if (barXData.size() == 1) {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 1개의 항목일 때 막대 너비를 30%로 설정
                            } else {
                                barChart.getStyler().setAvailableSpaceFill(0.4);  // 기본적으로 막대 너비를 80%로 설정
                            }

                            barChartPanel = new XChartPanel<>(barChart);
                            barChartPanel.setBounds(360, 0, 404, 300);
                            centerPanel.add(barChartPanel);
                            
                            
                        }

                        // 패널 다시 그리기
                        centerPanel.revalidate();
                        centerPanel.repaint();

                    }
                }
             

            

            }
        });


        setVisible(true);
        
     



        

        

        // 하단 금액 합산 영역
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(10, 380, 764, 173);
        bottomPanel.setBackground(Color.WHITE); // 하단 패널 배경 흰색
        getContentPane().add(bottomPanel);

        // 현금 정보
        JPanel cashPanel = new JPanel();
        cashPanel.setLayout(null);
        cashPanel.setBounds(0, 0, 207, 170);
        cashPanel.setBackground(Color.WHITE); // 현금 패널 배경 흰색
        bottomPanel.add(cashPanel);

        // 현금 아이콘 이미지
        ImageIcon cashIconImage = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\cash_icon.png");
        Image cashImage = cashIconImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 크기 조정 (80x80)
        JLabel cashIcon = new JLabel(new ImageIcon(cashImage));
        cashIcon.setBounds(62, 10, 132, 110); // 아이콘 위치
        cashPanel.add(cashIcon);

        cashLabel = new JLabel("요금제 "+ totalTimeCost+"원", SwingConstants.CENTER);
        cashLabel.setFont(new Font("굴림", Font.BOLD, 15));
        cashLabel.setBounds(0, 140, 260, 20);
        cashPanel.add(cashLabel);

        // 카드 정보
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(300, 0, 150, 170);
        cardPanel.setBackground(Color.WHITE); // 카드 패널 배경 흰색
        bottomPanel.add(cardPanel);

        // 카드 아이콘 이미지
        ImageIcon cardIconImage = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\food_icon.png");
        Image cardImage = cardIconImage.getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH); // 크기 조정 (80x80)
        JLabel cardIcon = new JLabel(new ImageIcon(cardImage));
        cardIcon.setBounds(12, 32, 150, 90); // 아이콘 위치
        cardPanel.add(cardIcon);

        cardLabel = new JLabel("음식 "+ totalFoodCost+"원", SwingConstants.CENTER);
        cardLabel.setFont(new Font("굴림", Font.BOLD, 15));
        cardLabel.setBounds(20, 140, 133, 20);
        cardPanel.add(cardLabel);

        // 총합 정보
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBounds(519, 0, 245, 170);
        totalPanel.setBackground(Color.WHITE); // 총합 패널 배경 흰색
        bottomPanel.add(totalPanel);

        // 총합 아이콘 이미지
        ImageIcon totalIconImage = new ImageIcon("C:\\Users\\user\\Desktop\\창프2\\total_icon.png");
        Image totalImage = totalIconImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 크기 조정 (80x80)
        JLabel totalIcon = new JLabel(new ImageIcon(totalImage));
        totalIcon.setBounds(73, 23, 105, 100); // 아이콘 위치
        totalPanel.add(totalIcon);

        totalLabel = new JLabel("총 "+ (totalTimeCost + totalFoodCost) +"원", SwingConstants.CENTER);
        totalLabel.setFont(new Font("굴림", Font.BOLD, 15));
        totalLabel.setBounds(0, 140, 244, 20);
        totalPanel.add(totalLabel);
        
        JLabel lblNewLabel = new JLabel("+");
        lblNewLabel.setBounds(239, 58, 31, 44);
        bottomPanel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 38));
        
        JLabel lblNewLabel_1 = new JLabel("=");
        lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 38));
        lblNewLabel_1.setBounds(494, 58, 31, 44);
        bottomPanel.add(lblNewLabel_1);
    }

	private JDatePickerImpl createDatePicker() {
        // DatePicker를 위한 모델 생성
		// UtilDateModel로 모델 생성
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);  // Properties는 생략
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		// 날짜를 Date 객체로 변환하는 예시
		Date selectedDate = (Date) model.getValue();  // model.getValue()는 Date로 반환됩니다.
		return datePicker;
    }

	// Date를 LocalDateTime으로 변환하는 메서드
	private LocalDateTime convertToLocalDateTime(Date date) {
	    Instant instant = date.toInstant();
	    return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	
	// 클릭된 버튼을 기억할 변수
	private JButton selectedButton = null;

	// 버튼을 클릭했을 때 색상 변경 메소드
	private void toggleButtonSelection(JButton button) {
	    // 이전에 선택된 버튼이 있으면 색상 초기화
	    if (selectedButton != null) {
	        selectedButton.setBackground(new Color(100, 149, 237)); // 원래 색상으로 되돌림
	    }

	    // 클릭된 버튼을 진하게 설정
	    if (button.getBackground().equals(new Color(100, 149, 237))) {
	        button.setBackground(button.getBackground().darker()); // 클릭 시 진하게
	    }

	    // 선택된 버튼으로 갱신
	    selectedButton = button;
	}

	
    // 파이 차트 생성 (동적 데이터)
    private PieChart createPieChart(String[] categories, double[] values) {
        if (categories.length != values.length) {
            throw new IllegalArgumentException("카테고리와 값의 길이가 같아야 합니다.");
        }

        PieChart chart = new PieChart(400, 300);
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setChartTitleVisible(false);

        // 전달받은 데이터 추가
        for (int i = 0; i < categories.length; i++) {
            chart.addSeries(categories[i], values[i]);
        }
        return chart;
    }

    // 막대 그래프 생성 (동적 데이터)
    private CategoryChart createBarChart(String title, String xAxisTitle, String yAxisTitle, java.util.List<String> xData, java.util.List<Integer> yData) {
        if (xData.size() != yData.size()) {
            throw new IllegalArgumentException("x축 데이터와 y축 데이터의 크기가 같아야 합니다.");
        }

        CategoryChart chart = new CategoryChartBuilder()
            .width(400)
            .height(300)
            .title(title)
            .xAxisTitle(xAxisTitle)
            .yAxisTitle(yAxisTitle)
            .build();

        chart.addSeries("매출", xData, yData);
        return chart;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            saleStatus ui = new saleStatus();
            ui.setVisible(true);
        });
    }
}
