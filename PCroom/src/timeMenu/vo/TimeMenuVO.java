package timeMenu.vo;

public class TimeMenuVO {
	// 필드 선언
	private int timeNum;
	private int addTime;
	private String payment;
	private int cost;


	// 생성자 선언
	public TimeMenuVO() {}

	public TimeMenuVO(int timeNum, int addTime, String payment, int cost) {
		this.timeNum = timeNum;
		this.addTime = addTime;
		this.payment = payment;
		this.cost = cost;
		
	}
	
//	@Override
//    public String toString() {
//        return timeNum + "," + addTime + "," + payment + "," + cost;
//    }

	public int getTimeNum() {
		return timeNum;
	}

	public void setTimeNum(int timeNum) {
		this.timeNum = timeNum;
	}
	
	public int getAddTime() {
		return addTime;
	}

	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}
	
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	

}
