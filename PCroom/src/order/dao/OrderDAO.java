																																																																																																																																																																																																																															package order.dao;

import java.sql.SQLException;
import java.util.List;

import foodMenu.vo.FoodMenuVO;
import foodSort.vo.FoodSortVO;
import member.vo.MemberVO;
import order.vo.OrderVO;



public interface OrderDAO {
	
	//public List<FoodMenuVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<OrderVO> selectOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException;
	
	public void insertOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException;
	
	public void updateOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException;
	
	public void deleteOrder(OrderVO orderVO) throws SQLException, ClassNotFoundException;
	
	public int getNextOrderId() throws SQLException, ClassNotFoundException;
}


