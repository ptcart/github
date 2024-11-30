package admin.window; // admin.window 패키지 선언

import java.awt.*; // AWT 클래스 임포트
import java.awt.event.*; // AWT 이벤트 클래스 임포트
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; // ArrayList 클래스 임포트
import java.util.List; // List 인터페이스 임포트

// TransferHandler 클래스 임포트
import javax.swing.*; // Swing 클래스 임포트
import javax.swing.table.DefaultTableModel; // JTable 모델 클래스 임포트

import foodSort.vo.FoodSortVO; // MemberVO 클래스 임포트
import foodSort.controller.FoodSortController; // RemainTimeController 인터페이스 임포트
import foodSort.controller.FoodSortControllerImpl; // RemainTissmeControllerImpl 클래스 임포트

import foodMenu.controller.FoodMenuController; // SeatController 인터페이스 임포트
import foodMenu.controller.FoodMenuControllerImpl; // SeatControllerImpl 클래스 임포트
import foodMenu.vo.FoodMenuVO; // SeatVO 클래스 임포트

import order.controller.OrderController; // SeatController 인터페이스 임포트
import order.controller.OrderControllerImpl; // SeatControllerImpl 클래스 임포트
import order.vo.OrderVO; // SeatVO 클래스 임포트

import orderDetail.vo.OrderDetailVO;
import orderDetail.controller.OrderDetailController; // SeatController 인터페이스 임포트
import orderDetail.controller.OrderDetailControllerImpl; // SeatControllerImpl 클래스 임포트

import admin.window.RegMemDialog2; // RegMemDialog2 클래스 임포트
import remainTime.vo.RemainTimeVO; // RemainTimeVO 클래스 임포트

import member.controller.MemberController; // MemberController 인터페이스 임포트
import member.controller.MemberControllerImpl; // MemberControllerImpl 클래스 임포트
import member.vo.MemberVO;

import javax.swing.event.ListSelectionEvent; // ListSelectionEvent 클래스 임포트
import javax.swing.event.ListSelectionListener; // ListSelectionListener 인터페이스 임포트

public class orderManage { // userManage 클래스 정의

    private JFrame frame; // JFrame 객체 선언
    private JTable table; // JTable 객체 선언
    private JTextField searchField; // 검색 필드 선언
    private JButton updateButton, deleteButton;  // 수정 및 삭제 버튼 선언
    static FoodSortController foodSortController = new FoodSortControllerImpl(); 
    static FoodMenuController foodMenuController = new FoodMenuControllerImpl(); 
    static OrderController orderController = new OrderControllerImpl(); 
    static OrderDetailController orderDetailController = new OrderDetailControllerImpl(); 
    //static RemainTimeController remainTimeController = new RemainTimeControllerImpl();

    public static void main(String[] args) { // 메인 메서드
        EventQueue.invokeLater(new Runnable() { // 이벤트 큐에 작업 추가
            public void run() { // run 메서드 정의
                try {
                    orderManage window = new orderManage(); // userManage 인스턴스 생성
                    window.frame.setVisible(true); // 프레임을 보이도록 설정
                } catch (Exception e) { // 예외 처리
                    e.printStackTrace(); // 예외 스택 트레이스 출력
                }
            }
        });
    }

    public orderManage() { // 생성자
        initialize(); // UI 초기화 메서드 호출
    }

    private void initialize() { // UI 초기화 메서드
        frame = new JFrame(); // JFrame 객체 생성
        frame.setBounds(100, 100, 650, 450); // 프레임 크기 및 위치 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 종료 시 애플리케이션 종료
        frame.getContentPane().setLayout(null); // 레이아웃을 null로 설정하여 자유로운 배치 가능
        frame.getContentPane().setBackground(new Color(64, 64, 64)); // 배경색 설정

        String[] columnNames = {"주문번호", "주문시간", "결제방식", "좌석번호", "주문상태"}; // 테이블 컬럼 이름 배열
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // 테이블 모델 생성
        table = new JTable(model) { // JTable 생성
            @Override
            public boolean isCellEditable(int row, int column) { // 셀 수정 가능 여부 설정
                return false; // 셀은 수정 불가능
            }
        };
        
        // 컬럼 헤더 배경색을 진하게 설정
        table.getTableHeader().setBackground(new Color(200, 200, 200)); // 약간 진한 회색
        table.getTableHeader().setForeground(Color.BLACK); // 글자색 설정

        table.setDragEnabled(true); // 드래그 가능 설정
        table.setDropMode(DropMode.INSERT_ROWS); // 드롭 모드 설정
        table.setTransferHandler(new TransferHandler("text")); // 텍스트 전송 핸들러 설정

        // 주문내역 정보를 저장할 리스트 생성
        // OrderVO 객체 생성
        
        OrderVO order = new OrderVO();
     // 주문내역 목록 가져오기
        List<OrderVO> orderList = orderController.listOrder(order);
       
        String orderState = null;
        
     // 초기 검색 실행
        String conditionText = "주문 준비"; // 초기 조건 값
        model.setRowCount(0); // 테이블 모델 초기화
        for (OrderVO od : orderList) {
            String orderTimeString = od.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            if(od.getState() == 0) {
            	orderState = "주문 준비";
            }
            else if(od.getState() == 1) {
            	orderState = "주문 완료";
            }
            else if(od.getState() == 2) {
            	orderState = "주문 취소";
            }
            
            

            if ("주문 준비".equals(orderState)) {
                Object[] rowData = {
                    od.getOrderId(),
                    orderTimeString,
                    od.getPayment(),
                    od.getSeatNum(),
                    orderState
                };
                model.addRow(rowData); // 조건에 맞는 데이터 추가
            }
        }

        JScrollPane scrollPane = new JScrollPane(table); // 테이블을 스크롤 패널에 추가
        scrollPane.setBounds(29, 85, 577, 307); // 테이블 위치와 크기 설정
        scrollPane.setBorder(BorderFactory.createTitledBorder("주문 내역 목록")); // 테이블 테두리 설정
        frame.getContentPane().add(scrollPane); // 프레임에 스크롤 패널 추가

        // 상단 패널 및 검색창
        JPanel topPanel = new JPanel(); // 상단 패널 생성
        topPanel.setBounds(26, 10, 550, 40); // 위치와 크기 설정
        topPanel.setLayout(new BorderLayout()); // 레이아웃 설정
        topPanel.setBackground(new Color(64, 64, 64)); // 배경색을 프레임과 맞춤

        JPanel searchPanel = new JPanel(); // 검색 패널 생성
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 레이아웃 설정
        searchPanel.setBackground(new Color(64, 64, 64)); // 배경색을 프레임과 맞춤
        searchField = new JTextField(20); // 검색 필드 생성
        searchField.setPreferredSize(new Dimension(120, 25)); // 검색 필드 크기 설정
        JButton searchButton = new JButton("검색"); // 검색 버튼 생성
        searchButton.setBackground(new Color(220, 220, 220)); // 연한 회색
        searchButton.setPreferredSize(new Dimension(80, 25)); // 버튼 크기 설정
        
        String[] searchOptions = {"전체", "주문번호", "주문시간", "결제방식", "좌석번호", "주문상태"}; // 검색 옵션 배열
        JComboBox<String> searchComboBox = new JComboBox<>(searchOptions); // 콤보박스 생성
        searchPanel.add(searchComboBox); // 검색 패널에 콤보박스 추가
        
     // 두 번째 콤보박스 추가
        JComboBox<String> conditionComboBox = new JComboBox<>(); // 새로운 콤보박스 생성
        conditionComboBox.setPreferredSize(new Dimension(80, 25)); // 크기 설정
        conditionComboBox.setEnabled(false); // 초기에는 비활성화
        searchPanel.add(conditionComboBox); // 검색 패널에 추가
        
     // 초기값 설정
        searchComboBox.setSelectedItem("주문상태"); // 왼쪽 콤보박스 초기값 설정
        conditionComboBox.setEnabled(true); // 오른쪽 콤보박스 활성화
        conditionComboBox.addItem("주문 준비");
        conditionComboBox.addItem("주문 완료");
        conditionComboBox.addItem("주문 취소");
        conditionComboBox.setSelectedItem("주문 준비"); // 오른쪽 콤보박스 초기값 설정
        
        searchField.setEnabled(false); // 검색 필드 비활성화
        searchField.setBackground(Color.LIGHT_GRAY); // 배경색 변경
        searchField.setDisabledTextColor(Color.DARK_GRAY); // 비활성 상태 텍스트 색상 설정
        
        
     // 검색 조건 콤보박스 이벤트 리스너 추가
        searchComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) searchComboBox.getSelectedItem();

                // 검색 필드와 조건 콤보박스 동작 설정
                if ("결제방식".equals(selectedOption)) {
                    searchField.setEnabled(false); // 검색 필드 비활성화
                    searchField.setBackground(Color.LIGHT_GRAY); // 배경색 변경
                    searchField.setDisabledTextColor(Color.DARK_GRAY); // 비활성 상태 텍스트 색상 설정
                    conditionComboBox.setEnabled(true); // 조건 콤보박스 활성화
                    conditionComboBox.removeAllItems(); // 기존 아이템 초기화
                    conditionComboBox.addItem("카드"); // 결제 방식 옵션 추가
                    conditionComboBox.addItem("현금");
                } else if ("주문상태".equals(selectedOption)) {
                    searchField.setEnabled(false); // 검색 필드 비활성화
                    searchField.setBackground(Color.LIGHT_GRAY); // 배경색 변경
                    searchField.setDisabledTextColor(Color.DARK_GRAY); // 비활성 상태 텍스트 색상 설정
                    conditionComboBox.setEnabled(true); // 조건 콤보박스 활성화
                    conditionComboBox.removeAllItems(); // 기존 아이템 초기화
                    conditionComboBox.addItem("주문 준비"); // 주문 상태 옵션 추가
                    conditionComboBox.addItem("주문 완료");
                    conditionComboBox.addItem("주문 취소");
                } else {
                    searchField.setEnabled(true); // 검색 필드 활성화
                    searchField.setBackground(Color.WHITE); // 배경색 변경
                    searchField.setDisabledTextColor(Color.WHITE); // 비활성 상태 텍스트 색상 설정
                    conditionComboBox.setEnabled(false); // 조건 콤보박스 비활성화
                    conditionComboBox.removeAllItems(); // 아이템 초기화
                }
            }
        });
        
     // 검색 버튼 이벤트 수정
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase(); // 검색어 소문자 변환
                String conditionText = (String) conditionComboBox.getSelectedItem(); // 조건 콤보박스 선택 값
                model.setRowCount(0); // 테이블 모델 초기화

                // 전체 주문 내역 가져오기
                List<OrderVO> orderList = orderController.listOrder(new OrderVO());
                String orderState = null;

                // 선택된 검색 기준에 따른 검색 수행
                for (OrderVO od : orderList) {
                    boolean matchFound = false; // 일치 여부 플래그
                    String orderTimeString = od.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    if (od.getState() == 0) {
                        orderState = "주문 준비";
                    } 
                    else if (od.getState() == 1) {
                        orderState = "주문 완료";
                    }
                    else if (od.getState() == 2) {
                        orderState = "주문 취소";
                    }

                    if (searchComboBox.getSelectedItem().equals("전체")) {
                        // 기존 전체 검색 로직
                        if (String.valueOf(od.getOrderId()).toLowerCase().contains(searchText) ||
                            orderTimeString.toLowerCase().contains(searchText) ||
                            od.getPayment().toLowerCase().contains(searchText) ||
                            String.valueOf(od.getSeatNum()).contains(searchText) ||
                            orderState.contains(searchText)) {
                            matchFound = true;
                        }
                    } else {
                        // 선택된 기준에 따라 검색
                        switch (searchComboBox.getSelectedItem().toString()) {
                            case "주문번호":
                                matchFound = String.valueOf(od.getOrderId()).toLowerCase().contains(searchText);
                                break;
                            case "주문시간":
                                matchFound = orderTimeString.toLowerCase().contains(searchText);
                                break;
                            case "결제방식":
                                // 결제방식 검색 시 조건값과 비교
                                matchFound = od.getPayment().equals(conditionText);
                                break;
                            case "좌석번호":
                                matchFound = String.valueOf(od.getSeatNum()).contains(searchText);
                                break;
                            case "주문상태":
                                // 주문상태 검색 시 조건값과 비교
                                matchFound = orderState.equals(conditionText);
                                break;
                        }
                    }

                    if (matchFound) {
                        Object[] rowData = {
                            od.getOrderId(),
                            orderTimeString,
                            od.getPayment(),
                            od.getSeatNum(),
                            orderState
                        };
                        model.addRow(rowData);
                    }
                }

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "검색 결과가 없습니다."); // 검색 결과 없음 알림
                } else {
                    table.setRowSelectionInterval(0, 0); // 첫 번째 행 선택
                    table.scrollRectToVisible(table.getCellRect(0, 0, true)); // 스크롤 이동
                }
            }
        });
        searchPanel.add(searchField); // 검색 패널에 검색 필드 추가
        searchPanel.add(searchButton); // 검색 패널에 검색 버튼 추가
        topPanel.add(searchPanel, BorderLayout.WEST); // 상단 패널에 검색 패널 추가
        frame.getContentPane().add(topPanel); // 프레임에 상단 패널 추가

        // 버튼 패널
        JPanel buttonPanel = new JPanel(); // 버튼 패널 생성
        buttonPanel.setBounds(50, 320, 550, 40); // 버튼 패널 위치와 크기 설정
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 레이아웃 설정
        buttonPanel.setBackground(new Color(64, 64, 64)); // 배경색을 프레임과 맞춤
        frame.getContentPane().add(buttonPanel); // 프레임에 버튼 패널 추가

        // 메뉴 수정 버튼
        updateButton = new JButton("주문 완료"); // 135, 50, 100, 25
        updateButton.setBounds(30, 50, 100, 25);
        updateButton.setEnabled(false); // 초기에는 비활성화 상태
        frame.getContentPane().add(updateButton);
        updateButton.setBackground(new Color(220, 220, 220)); // 연한 회색 배경

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
                if (selectedRow == -1) { // 선택된 행이 없는 경우
                    JOptionPane.showMessageDialog(frame, "완료할 내역을 선택하세요.");
                    return;
                }
                
                // 완료 확인 메시지 표시
                JOptionPane.showMessageDialog(frame, "주문을 완료처리되었습니다.");
                
                OrderVO order = new OrderVO();
                // 선택된 주문내역 정보 가져오기
                String orderId = model.getValueAt(selectedRow, 0).toString();
                order.setOrderId(Integer.parseInt(orderId));
                order.setState(1);

                orderController.modOrder(order);
               
             // 현재 화면 닫기
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(updateButton); // deleteButton이 속한 화면 가져오기
                if (currentFrame != null) {
                    currentFrame.dispose(); // 현재 화면 닫기
                }
                
                // 화면 갱신
                orderManage oM = new orderManage();
                oM.showUI();
            }
        });
        updateButton.setPreferredSize(new Dimension(100, 25)); // 수정 버튼 크기 설정

        // 회원 삭제 버튼
        deleteButton = new JButton("주문 취소");
        deleteButton.setBounds(135, 50, 100, 25);
        deleteButton.setEnabled(false); // 초기에는 비활성화 상태
        frame.getContentPane().add(deleteButton);
        deleteButton.setBackground(new Color(220, 220, 220)); // 연한 회색 배경

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
                if (selectedRow == -1) { // 선택된 행이 없는 경우
                    JOptionPane.showMessageDialog(frame, "취소할 내역를 선택하세요.");
                    return;
                }
                
                // 삭제 확인 메시지 표시
                JOptionPane.showMessageDialog(frame, "주문이 취소처리되었습니다.");
                
                OrderVO order = new OrderVO();
                // 선택된 주문내역 정보 가져오기
                String orderId = model.getValueAt(selectedRow, 0).toString();
                order.setOrderId(Integer.parseInt(orderId));
                order.setState(2);
                orderController.modOrder(order);
               
                
             // 현재 화면 닫기
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(deleteButton); // deleteButton이 속한 화면 가져오기
                if (currentFrame != null) {
                    currentFrame.dispose(); // 현재 화면 닫기
                }
                
                // 화면 갱신
                orderManage oM = new orderManage();
                oM.showUI();
            }
        });
        deleteButton.setPreferredSize(new Dimension(100, 25)); // 삭제 버튼 크기 설정

        // 테이블 선택 상태 변경 시 회원 수정 및 삭제 버튼 활성화/비활성화
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                boolean rowSelected = table.getSelectedRow() != -1; // 선택된 행이 있는지 확인
                updateButton.setEnabled(rowSelected); // 선택된 행이 있으면 수정 버튼 활성화
                deleteButton.setEnabled(rowSelected); // 선택된 행이 있으면 삭제 버튼 활성화
            }
        });
        
     // 표를 더블클릭할시 나오게 만들기
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 확인
                    int row = table.getSelectedRow(); // 선택된 행 가져오기
                    if (row != -1) {
                        // 선택된 행의 데이터 가져오기
                        String orderId = table.getValueAt(row, 0).toString(); // 주문번호
                        //String orderTime = table.getValueAt(row, 1).toString(); // 주문시간
                        String payment = table.getValueAt(row, 2).toString(); // 결제방식
                        String seatNum = table.getValueAt(row, 3).toString(); // 좌석번호
                        //String orderState = table.getValueAt(row, 4).toString(); // 주문상태
                        
                        OrderDetailVO orderDetail = new OrderDetailVO();
                        orderDetail.setOrderId(Integer.parseInt(orderId));

                     // 주문 상세 정보 조회
                        List<OrderDetailVO> listOrderDetail = orderDetailController.listDetail(orderDetail);
                        StringBuilder foodMenuNames = new StringBuilder();
                        int totalPrice = 0; // 총 가격 계산용 변수

                        // 주문 상세 항목에서 menuId를 가져와서 음식 메뉴 이름과 수량, 가격을 출력
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
                                    totalPrice += itemTotalPrice; // 총 가격에 추가

                                    foodMenuNames.append("● ")
                                    .append(menu.getName())
                                    .append(" (")
                                    .append(menuCount)
                                    .append("개 * ")
                                    .append(menuPrice)
                                    .append("원 = ")
                                    .append(itemTotalPrice)
                                    .append("원)\n");
                                }
                            } else {
                                foodMenuNames.append("해당 메뉴 ID에 대한 음식 정보를 찾을 수 없습니다. menuId: ")
                                             .append(menuId)
                                             .append("\n");
                            }
                        }

                        // 안내창 생성
                        JOptionPane.showMessageDialog(frame,
                                "주문번호: " + orderId + "\n" +
                                "좌석번호: " + seatNum + "\n" +
                                "결제방식: " + payment + "\n\n" +
                                "음식 목록:\n" + foodMenuNames.toString() + // 음식 이름, 수량, 가격 출력
                                "\n총 가격: " + totalPrice + "원", // 총 가격 추가
                                "주문 상세 정보",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });

        
     // 나가기 버튼
        JButton exitButton = new JButton("나가기"); // 회원 등록 버튼 생성
        exitButton.setBounds(505, 50, 100, 25); // 버튼 위치 및 크기 설정
        frame.getContentPane().add(exitButton); // 프레임에 회원 등록 버튼 추가

        // 버튼 색상 설정
        exitButton.setBackground(new Color(220, 220, 220)); // 연한 회색

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeUI(); // 현재 UI 닫기
            }
        });
    }
        // UI 보이기
        public void showUI() {
            frame.setVisible(true);
        }

        // UI 닫기
        public void closeUI() {
            frame.setVisible(false);
        }
}