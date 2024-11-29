package admin.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class orderDetail {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderDetail window = new orderDetail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public orderDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setBounds(100, 100, 450, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);

	    // 음식 이름, 갯수 라벨
	    JLabel foodNameLabel = new JLabel("음식 이름: 햄버거 x2");
	    foodNameLabel.setBounds(10, 10, 200, 30); // 위치 및 크기 설정
	    frame.getContentPane().add(foodNameLabel);

	    JLabel foodNameLabel2 = new JLabel("음식 이름: 감자튀김 x1");
	    foodNameLabel2.setBounds(10, 40, 200, 30); // 위치 및 크기 설정
	    frame.getContentPane().add(foodNameLabel2);

	    JLabel foodNameLabel3 = new JLabel("음식 이름: 콜라 x2");
	    foodNameLabel3.setBounds(10, 70, 200, 30); // 위치 및 크기 설정
	    frame.getContentPane().add(foodNameLabel3);

	    // 총 가격 라벨
	    JLabel totalPriceLabel = new JLabel("총 가격: 14000원");
	    totalPriceLabel.setBounds(10, 220, 200, 30); // 위치 및 크기 설정
	    frame.getContentPane().add(totalPriceLabel);
	}


}
