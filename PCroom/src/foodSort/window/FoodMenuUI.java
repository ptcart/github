package foodSort.window;

import java.awt.Color; // 색상 처리를 위한 패키지
import java.awt.Dimension; // 크기 설정을 위한 패키지
import java.awt.EventQueue; // 이벤트 큐를 위한 패키지
import java.awt.Font; // 폰트 처리를 위한 패키지
import java.awt.Image; // 이미지 처리를 위한 패키지
import java.awt.event.ActionEvent; // 액션 이벤트를 위한 패키지
import java.awt.event.ActionListener; // 액션 리스너를 위한 패키지
import java.awt.event.KeyAdapter; // 키 어댑터를 위한 패키지
import java.awt.event.KeyEvent; // 키 이벤트를 위한 패키지
import java.awt.event.MouseAdapter; // 마우스 어댑터를 위한 패키지
import java.awt.event.MouseEvent; // 마우스 이벤트를 위한 패키지
import javax.swing.BorderFactory; // 경계 생성기를 위한 패키지
import javax.swing.ImageIcon; // 이미지 아이콘을 위한 패키지
import javax.swing.JButton; // 버튼을 위한 패키지
import javax.swing.JFrame; // 프레임을 위한 패키지
import javax.swing.JLabel; // 라벨을 위한 패키지
import javax.swing.JPanel; // 패널을 위한 패키지
import javax.swing.JScrollPane; // 스크롤 패널을 위한 패키지
import javax.swing.JTextField; // 텍스트 필드를 위한 패키지
import javax.swing.border.EmptyBorder; // 빈 경계를 위한 패키지
import javax.swing.border.MatteBorder; // 매트 경계를 위한 패키지

import chatNetwork.Client;
import foodMenu.vo.FoodMenuVO;
import timeMenu.vo.TimeMenuVO;

import java.util.ArrayList; // 배열 리스트를 위한 패키지
import java.util.HashMap; // 해시맵을 위한 패키지
import java.util.List;

public class FoodMenuUI {

	private Client client;
    private JFrame frame; // 프레임 객체
    private JTextField searchField; // 검색 필드
    private JPanel foodPanel; // 음식 패널
    private JScrollPane scrollPane; // 스크롤 패널
    private JPanel cartPanel; // 장바구니 패널
    private JScrollPane cartScrollPane; // 장바구니 스크롤 패널
    private ArrayList<String> cartItems; // 장바구니 아이템 리스트
    private JPanel cartItemsPanel; // 장바구니 아이템 패널
    private JButton selectedButton = null; // 선택된 버튼
    private JButton[] menuButtons; // 메뉴 버튼 배열
    private JButton orderButton; // 주문 버튼
    private HashMap<String, Integer> itemQuantities; // 아이템 수량 해시맵
    private String currentMenuType = "추천상품"; // 현재 선택된 메뉴 타입
    private List<FoodMenuVO> foodMenuList = new ArrayList<FoodMenuVO>();

    // 데이터베이스를 흉내낸 데이터들
    private String[] allFoodItems = {"파파야 + 메론에이드 set", "ICE아메리카노", "복숭아아이스티", "얼닥사", "메론에이드", "파파야에이드",
            "HOT아메리카노", "수제더치 콜드라떼", "믹스커피", "ICE초코", "박하수", "아삭쥬", "ICE바닐라카페라떼", "레몬에이드"};
    private int[] allPrices = {6000, 3300, 3000, 3500, 3500, 3500, 3300, 4300, 3000, 3500, 4000, 4000, 4300, 3500};
    private String[] allImagePaths = {"path/to/papaya_melon.jpg", "path/to/ice_americano.jpg", "path/to/peach_iced_tea.jpg",
            "path/to/eoldaksa.jpg", "path/to/melonade.jpg", "path/to/papaya_ade.jpg",
            "path/to/hot_americano.jpg", "path/to/handmade_dutch.jpg", "path/to/mixed_coffee.jpg",
            "path/to/ice_choco.jpg", "path/to/mint.jpg", "path/to/crisp_juice.jpg",
            "path/to/ice_vanilla.jpg", "path/to/lemonade.jpg"};



    public FoodMenuUI(Client client) {
        cartItems = new ArrayList<>(); // 장바구니 아이템 리스트 초기화
        itemQuantities = new HashMap<>(); // 아이템 수량 해시맵 초기화
        this.client = client;
        initialize(); // 초기화 메서드 호출
    }

    private void initialize() {
        frame = new JFrame(); // 프레임 객체 생성
        frame.setBounds(100, 100, 1240, 685); // 프레임 크기 및 위치 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 기본 닫기 작업 설정
        frame.getContentPane().setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); // 전체 배경색 회색으로 설정
        
        System.out.println("test 1");
        String response = client.sendCommand("FOOD_MENU,");
        foodMenuList = parseResponse(response);
        System.out.println("test 2");
        
        // 컴포넌트 초기화
        initHeaderPanel(); // 헤더 패널 초기화
        initMenuTypePanel(); // 메뉴 타입 패널 초기화
        initFoodPanel(); // 음식 패널 초기화
        initCartPanel(); // 장바구니 패널 초기화
    }


    private void initHeaderPanel() {
        JPanel headerPanel = new JPanel(); // 헤더 패널 생성
        headerPanel.setBounds(0, 0, 1240, 50); // 헤더 패널 크기 및 위치 설정
        headerPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        headerPanel.setBackground(new Color(70, 130, 180)); // 헤더 패널 배경색 설정
        
        JLabel headerLabel = new JLabel("주문은 24시간 언제든지 가능합니다! 언제든지 주문해주세요!", JLabel.CENTER); // 헤더 라벨 생성 및 텍스트 설정
        headerLabel.setBounds(10, 10, 940, 30); // 헤더 라벨 크기 및 위치 설정
        headerLabel.setForeground(Color.WHITE); // 헤더 라벨 글자 색상 설정
        headerLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 16)); // 헤더 라벨 폰트 설정
        headerPanel.add(headerLabel); // 헤더 패널에 헤더 라벨 추가

        searchField = new JTextField("상품명 검색"); // 검색 필드 생성 및 초기 텍스트 설정
        searchField.setBounds(1016, 11, 200, 30); // 검색 필드 크기 및 위치 설정
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 검색 필드 경계 설정
        searchField.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 검색 필드 폰트 설정
        searchField.addKeyListener(new KeyAdapter() { // 검색 필드에 키 리스너 추가
            @Override
            public void keyReleased(KeyEvent e) { // 키가 릴리즈 될 때
                updateFoodPanelWithSearch(searchField.getText()); // 검색어에 따라 음식 패널 업데이트
            }
        });
        headerPanel.add(searchField); // 헤더 패널에 검색 필드 추가

        frame.getContentPane().add(headerPanel); // 프레임에 헤더 패널 추가
    }

    /**
     * Initialize the menu type panel.
     */
    private void initMenuTypePanel() {
        JPanel menuTypePanel = new JPanel(); // 메뉴 타입 패널 생성
        menuTypePanel.setBounds(0, 50, 980, 50); // 메뉴 타입 패널 크기 및 위치 설정
        menuTypePanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        menuTypePanel.setBackground(new Color(240, 240, 240)); // 메뉴 타입 패널 배경색 설정
        String[] menuTypes = new String[foodMenuList.size()]; // 메뉴 타입 배열
        int index = 0;
        for(FoodMenuVO f : foodMenuList) {
        	menuTypes[index++] = f.getSort();
        }
        
        int buttonWidth = 130; // 버튼 너비
        int buttonHeight = 30; // 버튼 높이
        int spacing = 10; // 버튼 간격
        
        menuButtons = new JButton[menuTypes.length]; // 메뉴 버튼 배열 초기화

        for (int i = 0; i < menuTypes.length; i++) { // 메뉴 타입 배열을 순회
            JButton button = new JButton(menuTypes[i]); // 메뉴 버튼 생성
            button.setBounds(10 + (buttonWidth + spacing) * i, 10, buttonWidth, buttonHeight); // 버튼 크기 및 위치 설정
            button.setBackground(new Color(70, 130, 180)); // 버튼 배경색 설정
            button.setForeground(Color.WHITE); // 버튼 글자 색상 설정
            button.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); // 버튼 폰트 설정
            button.setFocusPainted(false); // 버튼 포커스 페인팅 비활성화
            button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 버튼 경계 설정
            button.addActionListener(new ActionListener() { // 버튼에 액션 리스너 추가
                @Override
                public void actionPerformed(ActionEvent e) { // 버튼이 클릭될 때
                    if (selectedButton != null) { // 이전에 선택된 버튼이 있을 경우
                        selectedButton.setBackground(new Color(70, 130, 180)); // 이전 버튼 배경색 초기화
                    }
                    selectedButton = button; // 현재 버튼을 선택된 버튼으로 설정
                    selectedButton.setBackground(new Color(100, 149, 237)); // 현재 버튼 배경색 변경
                    currentMenuType = button.getText(); // 현재 선택된 메뉴 타입을 저장
                    updateFoodPanel(currentMenuType); // 메뉴 타입에 따라 음식 패널 업데이트
                }
            });
            menuTypePanel.add(button); // 메뉴 타입 패널에 버튼 추가
            menuButtons[i] = button; // 메뉴 버튼 배열에 버튼 추가
        }

        frame.getContentPane().add(menuTypePanel); // 프레임에 메뉴 타입 패널 추가
    }

    /**
     * Initialize the food panel.
     */
    private void initFoodPanel() {
        foodPanel = new JPanel(); // 음식 패널 생성
        foodPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        foodPanel.setBackground(new Color(255, 255, 255)); // 음식 패널 배경색 설정

        scrollPane = new JScrollPane(foodPanel); // 음식 패널을 감싸는 스크롤 패널 생성
        scrollPane.setBounds(0, 100, 980, 566); // 스크롤 패널 크기 및 위치 설정
        frame.getContentPane().add(scrollPane); // 프레임에 스크롤 패널 추가

        // Initially display "추천상품"
        updateFoodPanel("추천상품"); // 초기 화면에 "추천상품" 표시
        if (menuButtons.length > 0) { // 메뉴 버튼 배열이 비어있지 않으면
            menuButtons[0].setBackground(new Color(100, 149, 237)); // 첫 번째 버튼 배경색 변경
            selectedButton = menuButtons[0]; // 첫 번째 버튼을 선택된 버튼으로 설정
        }
    }

    /**
     * Update the food panel based on the selected menu type.
     */
    private void updateFoodPanel(String menuType) {
        foodPanel.removeAll(); // 음식 패널의 모든 컴포넌트 제거

        //for(FoodMenuVO f : )
        String[] foodItems; // 음식 아이템 배열
        int[] prices; // 가격 배열
        String[] discounts; // 할인 배열
        String[] imagePaths; // 이미지 경로 배열

        switch (menuType) { // 메뉴 타입에 따라 음식 아이템 설정
            case "12 pound":
                foodItems = new String[]{"Item 1", "Item 2", "Item 3", "Item 4"};
                prices = new int[]{1000, 2000, 3000, 4000};
                discounts = new String[]{"", "", "", ""};
                imagePaths = new String[]{"path/to/image1.jpg", "path/to/image2.jpg", "path/to/image3.jpg", "path/to/image4.jpg"};
                break;
            case "무비세트":
                foodItems = new String[]{"Item A", "Item B", "Item C", "Item D"};
                prices = new int[]{500, 1500, 2500, 3500};
                discounts = new String[]{"", "", "", ""};
                imagePaths = new String[]{"path/to/imageA.jpg", "path/to/imageB.jpg", "path/to/imageC.jpg", "path/to/imageD.jpg"};
                break;
            default: // 추천상품, nu Cafe, 밥줌먹자(꿀맛), nu 분식, 시원한 음료
                foodItems = new String[]{"파파야 + 메론에이드 set", "ICE아메리카노", "복숭아아이스티", "얼닥사", "메론에이드", "파파야에이드",
                        "HOT아메리카노", "수제더치 콜드라떼", "믹스커피", "ICE초코", "박하수", "아삭쥬", "ICE바닐라카페라떼", "레몬에이드"};
                prices = new int[]{6000, 3300, 3000, 3500, 3500, 3500, 3300, 4300, 3000, 3500, 4000, 4000, 4300, 3500};
                discounts = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", ""};
                imagePaths = new String[]{"path/to/papaya_melon.jpg", "path/to/ice_americano.jpg", "path/to/peach_iced_tea.jpg",
                        "path/to/eoldaksa.jpg", "path/to/melonade.jpg", "path/to/papaya_ade.jpg",
                        "path/to/hot_americano.jpg", "path/to/handmade_dutch.jpg", "path/to/mixed_coffee.jpg",
                        "path/to/ice_choco.jpg", "path/to/mint.jpg", "path/to/crisp_juice.jpg",
                        "path/to/ice_vanilla.jpg", "path/to/lemonade.jpg"};
                break;
        }

        displayFoodItems(foodItems, prices, discounts, imagePaths); // 음식 아이템 디스플레이

        foodPanel.revalidate(); // 음식 패널 리벨리데이트
        foodPanel.repaint(); // 음식 패널 리페인트
    }

    private void updateFoodPanelWithSearch(String query) {
        foodPanel.removeAll(); // 음식 패널의 모든 컴포넌트 제거

        ArrayList<String> matchedItems = new ArrayList<>(); // 일치하는 아이템 리스트
        ArrayList<Integer> matchedPrices = new ArrayList<>(); // 일치하는 가격 리스트
        ArrayList<String> matchedImagePaths = new ArrayList<>(); // 일치하는 이미지 경로 리스트

        String[] foodItems; // 음식 아이템 배열
        int[] prices; // 가격 배열
        String[] imagePaths; // 이미지 경로 배열

        // 현재 선택된 메뉴 타입에 따라 음식 항목 설정
        switch (currentMenuType) { // 현재 메뉴 타입에 따라 음식 아이템 설정
            case "12 pound":
                foodItems = new String[]{"Item 1", "Item 2", "Item 3", "Item 4"};
                prices = new int[]{1000, 2000, 3000, 4000};
                imagePaths = new String[]{"path/to/image1.jpg", "path/to/image2.jpg", "path/to/image3.jpg", "path/to/image4.jpg"};
                break;
            case "무비세트":
                foodItems = new String[]{"Item A", "Item B", "Item C", "Item D"};
                prices = new int[]{500, 1500, 2500, 3500};
                imagePaths = new String[]{"path/to/imageA.jpg", "path/to/imageB.jpg", "path/to/imageC.jpg", "path/to/imageD.jpg"};
                break;
            default: // 추천상품, nu Cafe, 밥줌먹자(꿀맛), nu 분식, 시원한 음료
                foodItems = new String[]{"파파야 + 메론에이드 set", "ICE아메리카노", "복숭아아이스티", "얼닥사", "메론에이드", "파파야에이드",
                        "HOT아메리카노", "수제더치 콜드라떼", "믹스커피", "ICE초코", "박하수", "아삭쥬", "ICE바닐라카페라떼", "레몬에이드"};
                prices = new int[]{6000, 3300, 3000, 3500, 3500, 3500, 3300, 4300, 3000, 3500, 4000, 4000, 4300, 3500};
                imagePaths = new String[]{"path/to/papaya_melon.jpg", "path/to/ice_americano.jpg", "path/to/peach_iced_tea.jpg",
                        "path/to/eoldaksa.jpg", "path/to/melonade.jpg", "path/to/papaya_ade.jpg",
                        "path/to/hot_americano.jpg", "path/to/handmade_dutch.jpg", "path/to/mixed_coffee.jpg",
                        "path/to/ice_choco.jpg", "path/to/mint.jpg", "path/to/crisp_juice.jpg",
                        "path/to/ice_vanilla.jpg", "path/to/lemonade.jpg"};
                break;
        }

        for (int i = 0; i < foodItems.length; i++) { // 음식 아이템 배열을 순회
            if (foodItems[i].toLowerCase().contains(query.toLowerCase())) { // 검색어가 포함된 경우
                matchedItems.add(foodItems[i]); // 일치하는 아이템 추가
                matchedPrices.add(prices[i]); // 일치하는 가격 추가
                matchedImagePaths.add(imagePaths[i]); // 일치하는 이미지 경로 추가
            }
        }

        displayFoodItems(matchedItems.toArray(new String[0]), matchedPrices.stream().mapToInt(i -> i).toArray(), new String[0], matchedImagePaths.toArray(new String[0])); // 일치하는 아이템 디스플레이

        foodPanel.revalidate(); // 음식 패널 리벨리데이트
        foodPanel.repaint(); // 음식 패널 리페인트
    }

    private void displayFoodItems(String[] foodItems, int[] prices, String[] discounts, String[] imagePaths) {
        int itemWidth = 220; // 아이템 패널 너비
        int itemHeight = 220; // 아이템 패널 높이
        int xSpacing = 10; // 아이템 패널 가로 간격
        int ySpacing = 10; // 아이템 패널 세로 간격
        int cols = 4; // 열 수
        int rows = (int) Math.ceil(foodItems.length / (double) cols); // 행 수 계산

        foodPanel.setPreferredSize(new Dimension(1000, rows * (itemHeight + ySpacing))); // 음식 패널 크기 설정

        for (int i = 0; i < foodItems.length; i++) { // 음식 아이템 배열을 순회
            JPanel itemPanel = new JPanel(null); // 아이템 패널 생성
            itemPanel.setBounds((i % cols) * (itemWidth + xSpacing) + 10, (i / cols) * (itemHeight + ySpacing) + 10, itemWidth, itemHeight); // 아이템 패널 크기 및 위치 설정
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // 아이템 패널 경계 설정
            itemPanel.setBackground(Color.WHITE); // 아이템 패널 배경색 설정

            final int index = i; // 인덱스를 효과적으로 final로 만듦

            JLabel itemImage = new JLabel(); // 아이템 이미지 라벨 생성
            itemImage.setBounds(10, 10, 200, 120); // 아이템 이미지 라벨 크기 및 위치 설정
            ImageIcon icon = new ImageIcon(imagePaths[i]); // 이미지 아이콘 생성
            Image img = icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH); // 이미지 크기 조정
            itemImage.setIcon(new ImageIcon(img)); // 이미지 아이콘 설정
            itemPanel.add(itemImage); // 아이템 패널에 이미지 라벨 추가
            
            JLabel itemName = new JLabel(foodItems[i]); // 아이템 이름 라벨 생성 및 텍스트 설정
            itemName.setBounds(10, 140, 200, 20); // 아이템 이름 라벨 크기 및 위치 설정
            itemName.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 아이템 이름 라벨 폰트 설정
            itemPanel.add(itemName); // 아이템 패널에 이름 라벨 추가
            
            JLabel itemPrice = new JLabel(prices[i] + "원"); // 아이템 가격 라벨 생성 및 텍스트 설정
            itemPrice.setBounds(10, 170, 200, 20); // 아이템 가격 라벨 크기 및 위치 설정
            itemPrice.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 아이템 가격 라벨 폰트 설정
            itemPanel.add(itemPrice); // 아이템 패널에 가격 라벨 추가
            
            JLabel itemDiscount = new JLabel(""); // 아이템 할인 라벨 생성
            if (discounts.length > i) { // 할인 배열이 현재 인덱스보다 길면
                itemDiscount = new JLabel(discounts[i]); // 할인 라벨 텍스트 설정
            }
            itemDiscount.setBounds(10, 190, 200, 20); // 할인 라벨 크기 및 위치 설정
            itemDiscount.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 할인 라벨 폰트 설정
            itemPanel.add(itemDiscount); // 아이템 패널에 할인 라벨 추가

            itemPanel.addMouseListener(new MouseAdapter() { // 아이템 패널에 마우스 리스너 추가
                @Override
                public void mouseClicked(MouseEvent e) { // 마우스 클릭 이벤트
                    addToCart(foodItems[index]); // 해당 아이템을 장바구니에 추가
                }
            });

            foodPanel.add(itemPanel); // 음식 패널에 아이템 패널 추가
        }
    }

    /**
     * Initialize the cart panel.
     */
    private void initCartPanel() {
        cartPanel = new JPanel(); // 장바구니 패널 생성
        cartPanel.setBounds(980, 50, 246, 598); // 장바구니 패널 크기 및 위치 설정
        cartPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        cartPanel.setBackground(new Color(245, 245, 245)); // 장바구니 패널 배경색 설정
        cartPanel.setBorder(new MatteBorder(0, 1, 0, 0, Color.GRAY)); // 장바구니 패널 좌측 경계 설정
        
        JLabel lblNewLabel = new JLabel("장바구니"); // 장바구니 라벨 생성 및 텍스트 설정
        lblNewLabel.setBounds(10, 10, 200, 30); // 장바구니 라벨 크기 및 위치 설정
        lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 16)); // 장바구니 라벨 폰트 설정
        cartPanel.add(lblNewLabel); // 장바구니 패널에 장바구니 라벨 추가

        cartItemsPanel = new JPanel(); // 장바구니 아이템 패널 생성
        cartItemsPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
        cartItemsPanel.setBackground(Color.WHITE); // 장바구니 아이템 패널 배경색 설정
        cartScrollPane = new JScrollPane(cartItemsPanel); // 장바구니 아이템 패널을 감싸는 스크롤 패널 생성
        cartScrollPane.setBounds(10, 50, 230, 460); // 장바구니 스크롤 패널 크기 및 위치 설정
        cartPanel.add(cartScrollPane); // 장바구니 패널에 스크롤 패널 추가

        orderButton = new JButton("주문하기"); // 주문 버튼 생성 및 텍스트 설정
        orderButton.setBounds(10, 520, 230, 40); // 주문 버튼 크기 및 위치 설정
        orderButton.setBackground(new Color(70, 130, 180)); // 주문 버튼 배경색 설정
        orderButton.setForeground(Color.WHITE); // 주문 버튼 글자 색상 설정
        orderButton.setFont(new Font("Malgun Gothic", Font.BOLD, 12)); // 주문 버튼 폰트 설정
        orderButton.setFocusPainted(false); // 주문 버튼 포커스 페인팅 비활성화
        orderButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 주문 버튼 경계 설정
        orderButton.setEnabled(false); // 초기에는 버튼 비활성화
        orderButton.addActionListener(new ActionListener() { // 주문 버튼에 액션 리스너 추가
            @Override
            public void actionPerformed(ActionEvent e) { // 주문 버튼이 클릭될 때
                processOrder(); // 주문 처리 메서드 호출
            }
        });
        cartPanel.add(orderButton); // 장바구니 패널에 주문 버튼 추가

        frame.getContentPane().add(cartPanel); // 프레임에 장바구니 패널 추가
    }

    /**
     * Add item to cart.
     */
    private void addToCart(String item) {
        if (!cartItems.contains(item)) { // 장바구니에 아이템이 없으면
            cartItems.add(item); // 아이템 추가
        }
        itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1); // 아이템 수량 증가
        updateCart(); // 장바구니 업데이트
    }

    /**
     * Update the cart panel.
     */
    private void updateCart() {
        cartItemsPanel.removeAll(); // 장바구니 아이템 패널의 모든 컴포넌트 제거
        
        int y = 0; // y 위치 초기화
        for (String item : cartItems) { // 장바구니 아이템 리스트 순회
            int quantity = itemQuantities.get(item); // 아이템 수량 가져오기

            JPanel cartItemPanel = new JPanel(); // 장바구니 아이템 패널 생성
            cartItemPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
            cartItemPanel.setBounds(0, y, 230, 90); // 장바구니 아이템 패널 크기 및 위치 설정
            cartItemPanel.setBackground(Color.WHITE); // 장바구니 아이템 패널 배경색 설정
            cartItemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); // 장바구니 아이템 패널 경계 설정

            JLabel itemNameLabel = new JLabel(item); // 아이템 이름 라벨 생성 및 텍스트 설정
            itemNameLabel.setBounds(10, 10, 190, 20); // 아이템 이름 라벨 크기 및 위치 설정
            itemNameLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 아이템 이름 라벨 폰트 설정
            cartItemPanel.add(itemNameLabel); // 장바구니 아이템 패널에 아이템 이름 라벨 추가

            JLabel itemPriceLabel = new JLabel(quantity * getItemPrice(item) + "원", JLabel.RIGHT); // 아이템 가격 라벨 생성 및 텍스트 설정
            itemPriceLabel.setBounds(10, 30, 190, 20); // 아이템 가격 라벨 크기 및 위치 설정
            itemPriceLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 아이템 가격 라벨 폰트 설정
            cartItemPanel.add(itemPriceLabel); // 장바구니 아이템 패널에 아이템 가격 라벨 추가

            JPanel quantityPanel = new JPanel(); // 수량 패널 생성
            quantityPanel.setLayout(null); // 레이아웃 매니저 비활성화 (절대 위치 사용)
            quantityPanel.setBounds(10, 50, 190, 20); // 수량 패널 크기 및 위치 설정
            quantityPanel.setBackground(Color.WHITE); // 수량 패널 배경색 설정
            cartItemPanel.add(quantityPanel); // 장바구니 아이템 패널에 수량 패널 추가

            JLabel quantityLabel = new JLabel("수량: ", JLabel.LEFT); // 수량 라벨 생성 및 텍스트 설정
            quantityLabel.setBounds(0, 0, 40, 20); // 수량 라벨 크기 및 위치 설정
            quantityLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 수량 라벨 폰트 설정
            quantityPanel.add(quantityLabel); // 수량 패널에 수량 라벨 추가

            JButton addButton = new JButton("+"); // 추가 버튼 생성 및 텍스트 설정
            addButton.setBounds(100, 0, 40, 20); // 추가 버튼 크기 및 위치 설정
            addButton.setFont(new Font("Malgun Gothic", Font.PLAIN, 12)); // 추가 버튼 폰트 설정
            addButton.setBackground(new Color(34, 139, 34)); // 추가 버튼 배경색 설정
            addButton.setForeground(Color.WHITE); // 추가 버튼 글자 색상 설정
            addButton.setFocusPainted(false); // 추가 버튼 포커스 페인팅 비활성화
            addButton.addActionListener(new ActionListener() { // 추가 버튼에 액션 리스너 추가
                @Override
                public void actionPerformed(ActionEvent e) { // 추가 버튼이 클릭될 때
                    addToCart(item); // 해당 아이템을 장바구니에 추가
                }
            });
            quantityPanel.add(addButton); // 수량 패널에 추가 버튼 추가

            JLabel qtyLabel = new JLabel(String.valueOf(quantity), JLabel.CENTER); // 수량 라벨 생성 및 텍스트 설정
            qtyLabel.setBounds(80, 0, 20, 20); // 수량 라벨 크기 및 위치 설정
            qtyLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14)); // 수량 라벨 폰트 설정
            quantityPanel.add(qtyLabel); // 수량 패널에 수량 라벨 추가

            JButton removeButton = new JButton("-"); // 제거 버튼 생성 및 텍스트 설정
            removeButton.setBounds(40, 0, 40, 20); // 제거 버튼 크기 및 위치 설정
            removeButton.setFont(new Font("Malgun Gothic", Font.PLAIN, 12)); // 제거 버튼 폰트 설정
            removeButton.setBackground(new Color(220, 20, 60)); // 제거 버튼 배경색 설정
            removeButton.setForeground(Color.WHITE); // 제거 버튼 글자 색상 설정
            removeButton.setFocusPainted(false); // 제거 버튼 포커스 페인팅 비활성화
            removeButton.addActionListener(new ActionListener() { // 제거 버튼에 액션 리스너 추가
                @Override
                public void actionPerformed(ActionEvent e) { // 제거 버튼이 클릭될 때
                    removeFromCart(item); // 해당 아이템을 장바구니에서 제거
                }
            });
            quantityPanel.add(removeButton); // 수량 패널에 제거 버튼 추가

            cartItemsPanel.add(cartItemPanel); // 장바구니 아이템 패널에 장바구니 아이템 패널 추가
            y += 95; // y 위치 증가 (아이템 패널 높이만큼)
        }

        cartItemsPanel.setPreferredSize(new Dimension(200, y)); // 장바구니 아이템 패널 크기 설정
        cartItemsPanel.revalidate(); // 장바구니 아이템 패널 리벨리데이트
        cartItemsPanel.repaint(); // 장바구니 아이템 패널 리페인트
        cartScrollPane.revalidate(); // 장바구니 스크롤 패널 리벨리데이트
        cartScrollPane.repaint(); // 장바구니 스크롤 패널 리페인트

        // 장바구니에 아이템이 있으면 주문하기 버튼 활성화, 없으면 비활성화
        orderButton.setEnabled(!cartItems.isEmpty()); // 장바구니에 아이템이 있으면 주문 버튼 활성화
    }

    private void removeFromCart(String item) {
        if (itemQuantities.get(item) > 1) { // 아이템 수량이 1보다 크면
            itemQuantities.put(item, itemQuantities.get(item) - 1); // 아이템 수량 감소
        } else { // 아이템 수량이 1이면
            itemQuantities.remove(item); // 아이템 수량 해시맵에서 제거
            cartItems.remove(item); // 장바구니 아이템 리스트에서 제거
        }
        updateCart(); // 장바구니 업데이트
    }

    private int getItemPrice(String item) {
        // 가격 정보를 반환하는 로직을 추가하세요
        // 예를 들어, item에 따라 가격을 반환하는 코드를 작성할 수 있습니다.
        for (int i = 0; i < allFoodItems.length; i++) { // 모든 음식 아이템 배열을 순회
            if (allFoodItems[i].equals(item)) { // 아이템이 일치하면
                return allPrices[i]; // 해당 가격 반환
            }
        }
        return 0; // 아이템을 찾지 못한 경우
    }

    /**
     * Process the order.
     */
    private void processOrder() {
        System.out.println("주문 처리 중..."); // 주문 처리 중 메시지 출력
        cartItems.clear(); // 장바구니 아이템 리스트 초기화
        itemQuantities.clear(); // 아이템 수량 해시맵 초기화
        updateCart(); // 장바구니 업데이트
    }
    
    private List<FoodMenuVO> parseResponse(String response) {
        List<FoodMenuVO> foodMenuList = new ArrayList<>();
        if (response != null && !response.isEmpty()) {
            System.out.println("Parsing response: " + response);
            String[] lines = response.split("\n");
            for (String line : lines) {
                if (line.equals("END")) {
                    break;
                }
                String[] data = line.split(",");
                FoodMenuVO foodMenuVO = new FoodMenuVO();
                foodMenuVO.setMenuId((data[0]));
                foodMenuVO.setSort((data[1]));
                foodMenuVO.setName(data[2]);
                foodMenuVO.setPrice(Integer.parseInt(data[3]));
                foodMenuList.add(foodMenuVO);
            }
        }
        return foodMenuList;
    }
    
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
