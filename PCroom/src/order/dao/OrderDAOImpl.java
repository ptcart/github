package order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import order.vo.OrderVO;




public class OrderDAOImpl extends AbstractBaseDAO implements OrderDAO {
	// 주문 종류 정보 검색 메소드
	public List<OrderVO> selectOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException {
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		// 나중에 관리자에서 좌석으로 주문내역 보게끔 하기
		int _seatNum = orderVO.getSeatNum();
		// 나중에 관리자꺼 생각해야된다 if 문으로 해야된다.
		pstmt = conn.prepareStatement("SELECT * FROM Orders");
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int orderId = rs.getInt("orderId");
			// 여기서 orderTime을 LocalDateTime으로 가져옴
            Timestamp timestamp = rs.getTimestamp("orderTime");
            LocalDateTime orderTime = timestamp != null ? timestamp.toLocalDateTime() : null;
			String Payment = rs.getString("Payment");
			int seatNum = rs.getInt("seatNum");
			int State = rs.getInt("State");
			OrderVO _Order = new OrderVO();
			
			_Order.setOrderId(orderId);
			_Order.setOrderTime(orderTime);
			_Order.setPayment(Payment);
			_Order.setSeatNum(seatNum);
			_Order.setState(State);

			orderList.add(_Order);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + orderList);
		return orderList;
	} // end list()
	
	public int getNextOrderId() throws SQLException, ClassNotFoundException {
        int nextOrderId = 1; // 기본값으로 1을 설정 (만약 테이블이 비어있다면 첫 번째 ID는 1)
        
        String query = "SELECT MAX(orderId) AS maxOrderId FROM Orders";
        
        pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int maxOrderId = rs.getInt("maxOrderId");
            if (maxOrderId != 0) {
                nextOrderId = maxOrderId + 1;
            }
        }
        
        rs.close();
        return nextOrderId;
    }
	
	// 주문 종류 정보 등록 메서드
	public void insertOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO Orders (orderId, orderTime, Payment, seatNum, State) VALUES (?, ?, ?, ?, ?)");
		pstmt.setInt(1, orderVO.getOrderId());
		pstmt.setObject(2, orderVO.getOrderTime());
		pstmt.setString(3, orderVO.getPayment());
		pstmt.setInt(4, orderVO.getSeatNum());
		pstmt.setInt(5, orderVO.getState());
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 주문 정보 수정 메소드
	public void updateOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException {
	    if (orderVO == null) {
	        throw new IllegalArgumentException("OrderVO가 null입니다.");
	    }
	    
	    if (orderVO.getPayment() == null && orderVO.getSeatNum() == 0) {
	        // Payment와 seatNum이 제공되지 않은 경우, State만 업데이트
	        pstmt = conn.prepareStatement("UPDATE Orders SET State = ? WHERE orderId = ?");
	        pstmt.setInt(1, orderVO.getState());
	        pstmt.setInt(2, orderVO.getOrderId());
	    } else {
	        // 기존 쿼리 실행
	        pstmt = conn.prepareStatement("UPDATE Orders SET Payment = ?, seatNum = ?, State = ? WHERE orderId = ?");
	        pstmt.setString(1, orderVO.getPayment());
	        pstmt.setInt(2, orderVO.getSeatNum());
	        pstmt.setInt(3, orderVO.getState());
	        pstmt.setInt(4, orderVO.getOrderId());
	    }
	    
	    pstmt.executeUpdate();
	}


	// 주문 정보 삭제 메소드
	public void deleteOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE Orders WHERE orderId = ?");
		pstmt.setInt(1, orderVO.getOrderId());
		pstmt.executeUpdate();
	}

}// end class MemberDAOImpl
