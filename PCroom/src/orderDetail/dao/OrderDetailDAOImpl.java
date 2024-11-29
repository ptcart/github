package orderDetail.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import orderDetail.vo.OrderDetailVO;



public class OrderDetailDAOImpl extends AbstractBaseDAO implements OrderDetailDAO {

	public List<OrderDetailVO> selectDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException {
		List<OrderDetailVO> detailList = new ArrayList<OrderDetailVO>();
		int _detail = orderDetailVO.getOrderId();
		if (_detail > 0) {
			pstmt = conn.prepareStatement("SELECT * FROM orderDetail WHERE orderId = ?");
			pstmt.setInt(1, _detail);
			
		}else {
			pstmt = conn.prepareStatement("SELECT * FROM orderDetail");
		}

		
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int orderId = rs.getInt("orderId");
			String menuId = rs.getString("menuId");
			int count = rs.getInt("Count");
			
			OrderDetailVO _detailVO = new OrderDetailVO();
			
			_detailVO.setOrderId(orderId);
			_detailVO.setMenuId(menuId);
			_detailVO.setCount(count);

			detailList.add(_detailVO);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + memList);
		return detailList;
	} // end list()
	
	// 주문 상세정보 등록 메서드
	public void insertDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException {
		
		pstmt = conn.prepareStatement("INSERT INTO orderDetail (orderId, menuId, Count) VALUES (?, ?, ?)");
		pstmt.setInt(1, orderDetailVO.getOrderId());
		pstmt.setString(2, orderDetailVO.getMenuId());
		pstmt.setInt(3, orderDetailVO.getCount());

		pstmt.executeUpdate();
	} // end insertMember()

	// 주문 상세정보 수정 메소드
	public void updateDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE orderDetail SET menuId = ?, Count = ? WHERE orderId = ?");	
		pstmt.setString(1, orderDetailVO.getMenuId());
		pstmt.setInt(2, orderDetailVO.getCount());
		pstmt.setInt(3, orderDetailVO.getOrderId());

		pstmt.executeUpdate();
		

	}

	// 주문 상세정보 삭제 메소드
	public void deleteDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE orderDetail WHERE orderId = ?");
		pstmt.setInt(1, orderDetailVO.getOrderId());
		pstmt.executeUpdate();
	}

}// end class MemberDAOImpl
