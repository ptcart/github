package payTime.dao;

import java.sql.SQLException;
import java.util.List;

import member.vo.MemberVO;
import payTime.vo.PayTimeVO;
import seat.vo.SeatVO;
import timeMenu.vo.TimeMenuVO;



public interface PayTimeDAO {
	
	//public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<PayTimeVO> selectPayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException;
	
	public void insertPayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException;
	
	public void updatePayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException;
	
	public void deletePayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException;
	
	public int getNextTimeId() throws SQLException, ClassNotFoundException;
}


