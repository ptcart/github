package admin.window; // admin.window 패키지 선언

import java.awt.*; // AWT 클래스 임포트
import java.awt.event.*; // AWT 이벤트 클래스 임포트
import java.util.ArrayList; // ArrayList 클래스 임포트
import java.util.List; // List 인터페이스 임포트

import javax.swing.*; // Swing 클래스 임포트
import javax.swing.table.DefaultTableModel; // JTable 모델 클래스 임포트

import member.vo.MemberVO; // MemberVO 클래스 임포트
import remainTime.controller.RemainTimeController; // RemainTimeController 인터페이스 임포트
import remainTime.controller.RemainTimeControllerImpl; // RemainTimeControllerImpl 클래스 임포트
import remainTime.vo.RemainTimeVO; // RemainTimeVO 클래스 임포트
import seat.controller.SeatController; // SeatController 인터페이스 임포트
import seat.controller.SeatControllerImpl; // SeatControllerImpl 클래스 임포트
import seat.vo.SeatVO; // SeatVO 클래스 임포트
import admin.window.RegMemDialog2; // RegMemDialog2 클래스 임포트
import member.controller.MemberController; // MemberController 인터페이스 임포트
import member.controller.MemberControllerImpl; // MemberControllerImpl 클래스 임포트

import javax.swing.TransferHandler; // TransferHandler 클래스 임포트
import javax.swing.event.ListSelectionEvent; // ListSelectionEvent 클래스 임포트
import javax.swing.event.ListSelectionListener; // ListSelectionListener 인터페이스 임포트

public class userManage extends JFrame{ // userManage 클래스 정의

    private JFrame frame; // JFrame 객체 선언
    private JTable table; // JTable 객체 선언
    private JTextField searchField; // 검색 필드 선언
    private JButton updateButton, deleteButton;  // 수정 및 삭제 버튼 선언
    static MemberController memberController = new MemberControllerImpl(); // 회원 컨트롤러 인스턴스 생성
    static SeatController seatController = new SeatControllerImpl(); // 좌석 컨트롤러 인스턴스 생성
    static RemainTimeController remainTimeController = new RemainTimeControllerImpl(); // 남은 시간 컨트롤러 인스턴스 생성

    public static void main(String[] args) { // 메인 메서드
        EventQueue.invokeLater(new Runnable() { // 이벤트 큐에 작업 추가
            public void run() { // run 메서드 정의
                try {
                    userManage window = new userManage(); // userManage 인스턴스 생성
                    window.frame.setVisible(true); // 프레임을 보이도록 설정
                } catch (Exception e) { // 예외 처리
                    e.printStackTrace(); // 예외 스택 트레이스 출력
                }
            }
        });
    }

    public userManage() { // 생성자
        initialize(); // UI 초기화 메서드 호출
    }

    private void initialize() { // UI 초기화 메서드
        frame = new JFrame(); // JFrame 객체 생성
        frame.setBounds(100, 100, 650, 450); // 프레임 크기 및 위치 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 종료 시 애플리케이션 종료
        frame.getContentPane().setLayout(null); // 레이아웃을 null로 설정하여 자유로운 배치 가능
        frame.getContentPane().setBackground(new Color(64, 64, 64)); // 배경색 설정

        String[] columnNames = { "이름", "성별", "아이디", "비밀번호", "전화번호" }; // 테이블 컬럼 이름 배열
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

        // 회원 정보를 저장할 리스트 생성
        MemberVO member = new MemberVO(); // MemberVO 객체 생성
        List<MemberVO> memberList = memberController.listMember(member); // 회원 목록 가져오기

        // 리스트에 있는 데이터를 테이블 모델에 추가
        for (MemberVO m : memberList) { // 회원 리스트 반복
            Object[] rowData = { 
                m.getName(), // 이름
                m.getGender(), // 성별
                m.getId(), // 아이디
                m.getPw(), // 비밀번호
                m.getPhoneNum() // 전화번호
            };
            model.addRow(rowData); // 테이블 모델에 행 추가
        }

        JScrollPane scrollPane = new JScrollPane(table); // 테이블을 스크롤 패널에 추가
        scrollPane.setBounds(29, 85, 577, 307); // 테이블 위치와 크기 설정
        scrollPane.setBorder(BorderFactory.createTitledBorder("회원 목록")); // 테이블 테두리 설정
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
        searchField.setPreferredSize(new Dimension(150, 25)); // 검색 필드 크기 설정
        JButton searchButton = new JButton("검색"); // 검색 버튼 생성
        searchButton.setBackground(new Color(220, 220, 220)); // 연한 회색
        searchButton.setPreferredSize(new Dimension(80, 25)); // 버튼 크기 설정
        
        String[] searchOptions = { "전체", "이름", "성별", "아이디", "비밀번호", "전화번호" }; // 검색 옵션 배열
        JComboBox<String> searchComboBox = new JComboBox<>(searchOptions); // 콤보박스 생성
        searchPanel.add(searchComboBox); // 검색 패널에 콤보박스 추가
        
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase(); // 검색어 소문자 변환
                model.setRowCount(0); // 테이블 모델 초기화

                // 전체 회원 목록 가져오기
                List<MemberVO> memberList = memberController.listMember(new MemberVO());

                // 선택된 검색 기준에 따른 검색 수행
                for (MemberVO m : memberList) {
                    boolean matchFound = false; // 일치 여부 플래그

                    // 전체 검색 시 모든 정보에서 검색어를 비교
                    if (searchComboBox.getSelectedItem().equals("전체")) {
                        if (m.getName().toLowerCase().contains(searchText) ||
                            m.getGender().toLowerCase().contains(searchText) ||
                            m.getId().toLowerCase().contains(searchText) ||
                            m.getPw().toLowerCase().contains(searchText) ||
                            m.getPhoneNum().toLowerCase().contains(searchText)) {
                            matchFound = true;
                        }
                    } else {
                        // 선택된 기준에 따라 검색
                        switch (searchComboBox.getSelectedItem().toString()) {
                            case "이름":
                                matchFound = m.getName().toLowerCase().contains(searchText);
                                break;
                            case "성별":
                                matchFound = m.getGender().toLowerCase().contains(searchText);
                                break;
                            case "아이디":
                                matchFound = m.getId().toLowerCase().contains(searchText);
                                break;
                            case "비밀번호":
                                matchFound = m.getPw().toLowerCase().contains(searchText);
                                break;
                            case "전화번호":
                                matchFound = m.getPhoneNum().toLowerCase().contains(searchText);
                                break;
                        }
                    }

                    // 일치하는 경우 테이블 모델에 추가
                    if (matchFound) {
                        Object[] rowData = { 
                            m.getName(), 
                            m.getGender(), 
                            m.getId(), 
                            m.getPw(), 
                            m.getPhoneNum() 
                        };
                        model.addRow(rowData); // 테이블 모델에 행 추가
                    }
                }

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "검색 결과가 없습니다."); // 검색 결과 없음 알림
                } else {
                    // 첫 번째 행을 선택 (결과가 있는 경우)
                    table.setRowSelectionInterval(0, 0);
                    table.scrollRectToVisible(table.getCellRect(0, 0, true)); // 스크롤을 첫 번째 행으로 이동
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
        JButton addButton = new JButton("회원 등록"); // 회원 등록 버튼 생성
        addButton.setBounds(30, 50, 100, 25); // 버튼 위치 및 크기 설정
        frame.getContentPane().add(addButton); // 프레임에 회원 등록 버튼 추가

        // 버튼 색상 설정
        addButton.setBackground(new Color(220, 220, 220)); // 연한 회색

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원 등록 대화 상자 열기
                RegMemDialog2 regmember = new RegMemDialog2();
                regmember.setVisible(true);
                closeUI(); // 현재 UI 닫기
            }
        });

        addButton.setPreferredSize(new Dimension(100, 25)); // 등록 버튼 크기 설정

        // 회원 수정 버튼
        updateButton = new JButton("회원 수정");
        updateButton.setBounds(135, 50, 100, 25);
        updateButton.setEnabled(false); // 초기에는 비활성화 상태
        frame.getContentPane().add(updateButton);
        updateButton.setBackground(new Color(220, 220, 220)); // 연한 회색 배경

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
                if (selectedRow == -1) { // 선택된 행이 없는 경우
                    JOptionPane.showMessageDialog(frame, "수정할 회원을 선택하세요.");
                    return;
                }
                
                // 선택된 회원 정보 가져오기
                String name = model.getValueAt(selectedRow, 0).toString();
                String gender = model.getValueAt(selectedRow, 1).toString();
                String id = model.getValueAt(selectedRow, 2).toString();
                String pw = model.getValueAt(selectedRow, 3).toString();
                String phoneNum = model.getValueAt(selectedRow, 4).toString();
                
                // 회원 정보 수정 UI 열기
                MyInfoUI2 myInfoUI = new MyInfoUI2(id, name, pw, gender, phoneNum);
                myInfoUI.setVisible(true);
                closeUI(); // 현재 UI 닫기
            }
        });
        updateButton.setPreferredSize(new Dimension(100, 25)); // 수정 버튼 크기 설정

        // 회원 삭제 버튼
        deleteButton = new JButton("회원 삭제");
        deleteButton.setBounds(240, 50, 100, 25);
        deleteButton.setEnabled(false); // 초기에는 비활성화 상태
        frame.getContentPane().add(deleteButton);
        deleteButton.setBackground(new Color(220, 220, 220)); // 연한 회색 배경

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
                if (selectedRow == -1) { // 선택된 행이 없는 경우
                    JOptionPane.showMessageDialog(frame, "삭제할 회원을 선택하세요.");
                    return;
                }
                
                // 삭제 확인 메시지 표시
                JOptionPane.showMessageDialog(frame, "회원이 삭제되었습니다.");
                
                // 삭제할 회원의 아이디 가져오기
                String id = model.getValueAt(selectedRow, 2).toString();
                MemberVO removeMem = new MemberVO();
                RemainTimeVO rTime = new RemainTimeVO();
                SeatVO _seat = new SeatVO();
                
                // 삭제할 회원의 아이디 설정
                removeMem.setId(id);
                rTime.setUserId(id);
                
                // 남은 시간 및 회원 정보 삭제
                remainTimeController.removeRemainTime(rTime);
                memberController.removeMember(removeMem);
                
                // 현재 화면 닫기
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(deleteButton); // deleteButton이 속한 화면 가져오기
                if (currentFrame != null) {
                    currentFrame.dispose(); // 현재 화면 닫기
                }
                
                // 화면 갱신
                userManage uM = new userManage();
                uM.showUI();
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