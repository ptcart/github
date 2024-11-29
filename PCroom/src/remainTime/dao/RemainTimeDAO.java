package remainTime.dao;

import java.sql.SQLException;
import java.util.List;

import member.vo.MemberVO;
import remainTime.vo.RemainTimeVO;
import seat.vo.SeatVO;



public interface RemainTimeDAO {
	
	//public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<RemainTimeVO> selectRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException;
	
	public void insertRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException;
	
	public void updateRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException;
	
	public void deleteRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException;
}


