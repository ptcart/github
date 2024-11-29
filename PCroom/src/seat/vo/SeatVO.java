package seat.vo;

public class SeatVO {
	// 필드 선언
	private int seatNum;
	private String userId;


	// 생성자 선언
	public SeatVO() {}

	public SeatVO(int seatNum, String userId) {
		this.seatNum = seatNum;
		this.userId = userId;
		
	}
	
	@Override
    public String toString() {
        return
        		userId + ',' +
               seatNum + ',';

    }

	public  int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
