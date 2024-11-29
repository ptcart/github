package order.vo;

import java.time.LocalDateTime;

public class OrderVO {
	// 필드 선언
	private int orderId;
	private LocalDateTime orderTime;
	private String Payment;
	private int seatNum;
	private int State;

	// 생성자 선언
	public OrderVO() {}

	public OrderVO(int orderId, LocalDateTime orderTime, String Payment, int seatNum, int State) {
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.Payment = Payment;
		this.seatNum = seatNum;
		this.State = State;
		}
	
	@Override
    public String toString() {
        return
        		orderId + ',' +
       // 		orderTime + ',' +
        		Payment + ',' +
        		seatNum + ',' +
        		State + ',' ;
    }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String Payment) {
		this.Payment = Payment;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getState() {
		return State;
	}

	public void setState(int State) {
		this.State = State;
	}

}
