package seat.dao;

import java.sql.SQLException;
import java.util.List;

import member.vo.MemberVO;
import seat.vo.SeatVO;



public interface SeatDAO {
	
	//public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<SeatVO> selectSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException;
	
	public void insertSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException;
	
	public void updateSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException;
	
	public void deleteSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException;
	
	public List<SeatVO> pickSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException;
}


