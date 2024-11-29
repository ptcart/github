package orderDetail.dao;

import java.sql.SQLException;
import java.util.List;

import foodMenu.vo.FoodMenuVO;
import member.vo.MemberVO;
import orderDetail.vo.OrderDetailVO;



public interface OrderDetailDAO {
	
	//public List<FoodMenuVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<OrderDetailVO> selectDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException;
	
	public void insertDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException;
	
	public void updateDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException;
	
	public void deleteDetail(OrderDetailVO orderDetailVO) throws SQLException, ClassNotFoundException;
}


