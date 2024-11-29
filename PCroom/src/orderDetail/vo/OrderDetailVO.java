package orderDetail.vo;

public class OrderDetailVO {
	// 필드 선언
	private int orderId;
	private String menuId;
	private int count;

	// 생성자 선언
	public OrderDetailVO() {}

	public OrderDetailVO(int orderId, String menuId, int count) {
		this.orderId = orderId;
		this.menuId = menuId;
		this.count = count;
	}
	
	@Override
    public String toString() {
        return
        		orderId + ',' +
               menuId + ',' +
               count + ',';
    }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
