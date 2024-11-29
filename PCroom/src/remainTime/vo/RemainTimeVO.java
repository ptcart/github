package remainTime.vo;

import java.time.Duration;

public class RemainTimeVO {
	// 필드 선언
	private String userId;
    private int remainTime;
    private int useMoney;


	// 생성자 선언
	public RemainTimeVO() {}

	public RemainTimeVO(String userId, int remainTime, int useMoney) {
		this.remainTime = remainTime;
		this.userId = userId;
		this.useMoney = useMoney;
	}
	
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }
    
    public int getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(int useMoney) {
        this.useMoney = useMoney;
    }

    @Override
    public String toString() {
        return
        		userId + ',' +
                remainTime;
    }

}
