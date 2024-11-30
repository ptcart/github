package admin.window;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.*;
import chatNetwork.Client; // Client 클래스가 정의되어 있어야 합니다.
import chatNetwork.Server;


public class ServerMessage extends JFrame {

    // 좌석 번호와 클라이언트 ID를 매핑하는 맵
    private static Map<Integer, String> seatClientMap = new HashMap<>();
    
    private JTextArea chatArea; 
    private JTextField inputField;
    private JButton exitButton;
    private JButton sendButton;
    private JLabel clientIdLabel;
    private Client client; 
    private int seatNum;
    private Server server;
    // 이미 처리된 메시지의 해시코드를 저장
    private Set<Integer> processedMessages = new HashSet<>();
    
    public ServerMessage(int seatNum, Server server) {
        this.seatNum = seatNum;
        this.server = server;
        initialize();
    }

    private void initialize() {
        setTitle("서버 메시지 창");
        setBounds(100, 100, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60, 63, 65));

     // 대화 영역
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBackground(new Color(60, 63, 65));
        chatScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(chatScrollPane, BorderLayout.CENTER);

        // 클라이언트 ID 라벨
        clientIdLabel = new JLabel("좌석 ID: " + seatNum);
        clientIdLabel.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        clientIdLabel.setForeground(Color.WHITE);
        getContentPane().add(clientIdLabel, BorderLayout.NORTH);

        // 하단 입력 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null); // 레이아웃을 null로 설정
        inputPanel.setPreferredSize(new Dimension(400, 110)); // 패널 크기 설정

        // 메시지 입력 필드
        inputField = new JTextField();
        inputField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        inputField.setBounds(10, 10, 230, 40); // 위치 및 크기 설정
        inputPanel.add(inputField);

        // 전송 버튼
        sendButton = new JButton("전송");
        sendButton.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        sendButton.setBackground(new Color(40, 167, 69));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBounds(250, 10, 130, 40); // 입력 필드 옆에 위치
        sendButton.addActionListener(e -> sendMessageToAllClients());
        inputPanel.add(sendButton);

        // 나가기 버튼
        exitButton = new JButton("나가기");
        exitButton.setFont(new Font("나눔고딕", Font.PLAIN, 14));
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(10, 60, 370, 40); // 하단 중앙 배치
        exitButton.addActionListener(e -> dispose());
        inputPanel.add(exitButton);

        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
        
        // 대화창에 메시지를 실시간으로 업데이트하는 스레드
        startMessageUpdateThread();
    }
    private void sendMessageToAllClients() {
        if (!inputField.getText().trim().isEmpty()) {
            String message = inputField.getText().trim();
            String formattedMessage = "MSG|" + seatNum + "|" + message;

            // 모든 클라이언트에게 메시지 전송
            if (server != null) {
                Server.sendMessageToAllClients1(formattedMessage); // Server의 메서드를 호출하여 모든 클라이언트에게 메시지 전송
            }

            // 대화창에 서버 메시지 추가
            appendMessage("관리자: " + message);	
            inputField.setText(""); // 입력창 초기화
            
         // 클라이언트의 응답을 기다리도록 설정
            // 이때 클라이언트는 응답을 기다리는 상태로 유지됩니다.
            // 서버에서 클라이언트의 응답을 기다리게 하는 비동기 처리 추가
        } else {
            JOptionPane.showMessageDialog(this, "메시지를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
        }
        
  
    }
    
 
    /**
     * 클라이언트에게 메시지 전송
     */
    private void startMessageUpdateThread() {
        Thread messageUpdateThread = new Thread(() -> {
            while (true) {
                try {
                    // 큐에서 모든 요청을 가져와서 처리
                    Queue<String> requests = new LinkedList<>(Server.getRequestQueue()); // 큐 복사

                    // 큐에 있는 모든 메시지를 처리
                    while (!requests.isEmpty()) {
                        String latestRequest = requests.poll(); // 큐에서 하나의 요청을 꺼냄

                        if (latestRequest != null && !latestRequest.isEmpty()) {
                            // "MSG|"로 시작하는 메시지일 때만 처리
                            if (latestRequest.startsWith("MSG|")) {
                                // "MSG|seatNum|message" 형식에서 seatNum과 메시지를 분리
                                String[] messageParts = latestRequest.split("\\|");
                                if (messageParts.length > 2) {
                                    // seatNum을 추출
                                    int messageSeatNum = Integer.parseInt(messageParts[1]); // 메시지에 포함된 seatNum

                                    // 현재 서버에서 관리하는 seatNum과 비교
                                    if (messageSeatNum == seatNum) {
                                        System.out.println("좌석비교 일치합니다");
                                        appendMessage("컴퓨터(" + messageSeatNum + "번): " + messageParts[2]); // 대화창에 메시지 추가
                                        Server.handleClientRequest(""); // 메시지 처리 후 초기화
                                    }
                                }
                            }
                        }
                    }

                    Server.clearRequestQueue();
                    requests.clear();
                    

                    Thread.sleep(500); // 주기적으로 큐를 확인 (500ms마다)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        messageUpdateThread.start();
    }

    // 대화창에 메시지 추가
    public void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // 스크롤을 맨 아래로 이동
    }
}