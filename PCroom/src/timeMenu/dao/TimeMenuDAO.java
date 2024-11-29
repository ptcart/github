package timeMenu.dao;

import java.sql.SQLException;
import java.util.List;

import member.vo.MemberVO;
import seat.vo.SeatVO;
import timeMenu.vo.TimeMenuVO;



public interface TimeMenuDAO {
	
	//public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<TimeMenuVO> selectTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException;
	
	public List<TimeMenuVO> selectTimeCost(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException;
	
	public void insertTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException;
	
	public void updateTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException;
	
	public void deleteTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException;
}


