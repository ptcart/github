package payTime.vo;

import java.time.LocalDateTime;

public class PayTimeVO {
	// 필드 선언
	private int timeId;
	private LocalDateTime payTime;
	private String userId;
	private int timeNum;


	// 생성자 선언
	public PayTimeVO() {}

	public PayTimeVO(int timeId, LocalDateTime payTime, String userId, int timeNum) {
		this.timeId = timeId;
		this.payTime = payTime;
		this.userId = userId;
		this.timeNum = timeNum;
		
	}
	
//	@Override
//    public String toString() {
//        return timeNum + "," + addTime + "," + payment + "," + cost;
//    }

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}
	
	public LocalDateTime getPayTime() {
		return payTime;
	}

	public void setPayTime(LocalDateTime payTime) {
		this.payTime = payTime;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getTimeNum() {
		return timeNum;
	}

	public void setTimeNum(int timeNum) {
		this.timeNum = timeNum;
	}

	

}
