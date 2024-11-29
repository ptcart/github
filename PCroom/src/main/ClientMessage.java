package main;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

//import network.Client;
import chatNetwork.Client;

public class ClientMessage extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton exitButton;
    private Client client;
    private String clientId;
    private int seatNum;

    /**
     * Create the application.
     */
    public ClientMessage(Client client, String clientId, int seatNum) {
        this.client = client;
        this.clientId = clientId;
        this.seatNum = seatNum;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        setTitle("메시지 창");
        setBounds(100, 100, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);  // 레이아웃 매니저를 null로 설정 (setBounds 사용)
        getContentPane().setBackground(new Color(60, 63, 65)); // 배경색 설정

        // 상단 대화창 (채팅 내역)
        chatArea = new JTextArea();
        chatArea.setEditable(false); // 대화창은 읽기 전용
        chatArea.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        chatArea.setLineWrap(true); // 텍스트 자동 줄바꿈
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea); // 스크롤 가능
        chatScrollPane.setBackground(new Color(60, 63, 65));
        chatScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가
        chatScrollPane.setBounds(10, 38, 370, 450); // 위치 및 크기 설정
        getContentPane().add(chatScrollPane);

        // 하단 입력 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 여백 설정
        inputPanel.setBounds(10, 493, 370, 60); // 위치 및 크기 설정

        // 메시지 입력창
        inputField = new JTextField();
        inputField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        inputField.setPreferredSize(new Dimension(200, 40));
        inputField.setBounds(0, 0, 270, 40); // 입력창 크기 및 위치
        inputPanel.add(inputField, BorderLayout.CENTER);

        // 전송 버튼
        JButton sendButton = new JButton("전송");
        sendButton.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        sendButton.setBackground(new Color(40, 167, 69));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(80, 40));
        sendButton.setBounds(280, 0, 80, 40); // 전송 버튼 위치 및 크기
        sendButton.addActionListener(e -> {
            String message = inputField.getText().trim(); // 입력 메시지를 가져옴
            if (!message.isEmpty()) {
                try {
                    if (client != null) { // 클라이언트 객체가 유효한지 확인
                        String formattedMessage = message; // 메시지 포맷 설정
                        
                        // 메시지 전송
                        client.sendMessage(clientId, formattedMessage, seatNum);
                        appendMessage("나: " + message); // 채팅창에 내 메시지 표시
                        inputField.setText(""); // 입력창 초기화

                     // 서버 응답을 기다리는 스레드
                        new SwingWorker<Void, String>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                while (true) {
                                    // 서버에서 메시지를 계속 대기
                                    String serverResponse = client.receiveMessage();
                                    if (serverResponse != null) {
                                    	
                                    	// 메시지를 수신했을 때 지연을 추가
                                        Thread.sleep(200); // 500ms (0.5초) 지연 추가 (원하는 시간으로 수정 가능)
                                        // "MSG|"로 시작하는 메시지 처리
                                        if (serverResponse.startsWith("MSG|")) {
                                            // "|" 이후의 값을 좌석번호로 처리
                                            String[] responseParts = serverResponse.split("\\|");
                                            if (responseParts.length > 1) {
                                                String seatNumber = responseParts[1]; // 첫 번째 구분자로부터 좌석 번호 추출
                                                
                                                // 좌석 번호가 일치하는 경우에만 해당 좌석에 메시지 표시
                                                if (seatNumber.equals(String.valueOf(seatNum))) {
                                                    String message = responseParts.length > 2 ? responseParts[2] : "";
                                                    appendMessage("관리자: " + message); // 해당 좌석의 메시지만 표시
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            protected void process(List<String> chunks) {
                                // 서버 응답 처리
                                for (String response : chunks) {
                                    appendMessage("서버: " + response); // 기본적인 메시지 표시 (필요시 수정 가능)
                                }
                            }
                        }.execute(); // 비동기적으로 서버 응답 대기

                    } else {
                        JOptionPane.showMessageDialog(this, "서버에 연결되지 않았습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "메시지 전송 실패: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "메시지를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        });


        inputPanel.add(sendButton, BorderLayout.EAST); // 입력창 옆에 전송 버튼 추가

        getContentPane().add(inputPanel);

        // 나가기 버튼 (오른쪽 상단)
        exitButton = new JButton("나가기");
        exitButton.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(305, 10, 75, 30); // 나가기 버튼 위치 및 크기
        exitButton.addActionListener(e -> {
            // 나가기 버튼 클릭 시 동작

                dispose(); // 창 닫기
            
        });
        getContentPane().add(exitButton);
        
        // 창을 표시
        setVisible(true);
    }
    
    

    /**
     * 대화창에 메시지 추가
     */
    private void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // 스크롤 맨 아래로 이동
    }
}
