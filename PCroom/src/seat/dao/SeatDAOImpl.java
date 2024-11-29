package seat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import seat.vo.SeatVO;



public class SeatDAOImpl extends AbstractBaseDAO implements SeatDAO {

	public List<SeatVO> selectSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException {
		List<SeatVO> seatList = new ArrayList<SeatVO>();
		//String seatName = SeatVO.getId();

		pstmt = conn.prepareStatement("SELECT * FROM Seat ORDER BY seatNum");
//		pstmt.setString(1, seatName);
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String userId = rs.getString("userId");
			int seatNum = rs.getInt("seatNum");

			SeatVO _seatVO = new SeatVO();
			
			_seatVO.setUserId(userId);
			_seatVO.setSeatNum(seatNum);

			seatList.add(_seatVO);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + seatList);
		return seatList;
	} // end list()
	
	public List<SeatVO> pickSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException {
		List<SeatVO> seatList = new ArrayList<SeatVO>();
		int seatName = seatVO.getSeatNum();

		pstmt = conn.prepareStatement("SELECT * FROM Seat WHERE seatNum = ?");
		pstmt.setInt(1, seatName);
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String userId = rs.getString("userId");
			int seatNum = rs.getInt("seatNum");

			SeatVO _seatVO = new SeatVO();
			
			_seatVO.setUserId(userId);
			_seatVO.setSeatNum(seatNum);

			seatList.add(_seatVO);
		} // end while
		rs.close();
		System.out.println("DB에서 나온 결과 : " + seatList);
		return seatList;
	} // end list()
	
//	public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException {
//		List<MemberVO> memList = new ArrayList<MemberVO>();
//		pstmt = conn.prepareStatement("SELECT * FROM Member");
//		
//		
//		ResultSet rs = pstmt.executeQuery();
//
//		while (rs.next()) {
//			String Id = rs.getString("Id");
//			String Pw = rs.getString("Pw");
//			String Name = rs.getString("Name");
//			String Gender = rs.getString("Gender");
//			String PhoneNum = rs.getString("PhoneNum");
//			MemberVO _memVO = new MemberVO();
//			
//			_memVO.setId(Id);
//			_memVO.setPw(Pw);
//			_memVO.setName(Name);
//			_memVO.setGender(Gender);
//			_memVO.setPhoneNum(PhoneNum);
//			memList.add(_memVO);
//		} // end while
//		rs.close();
//		return memList;
//	} // end list()

	// 좌석 정보 등록 메서드
	public void insertSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO Seat (seatNum, userId) VALUES (?, ?)");
		pstmt.setInt(1, seatVO.getSeatNum());
		pstmt.setString(2, seatVO.getUserId());
		
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 좌석 정보 수정 메소드
	public void updateSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE Seat SET userId = ? WHERE seatNum = ?");
		pstmt.setString(1, seatVO.getUserId());
		pstmt.setInt(2, seatVO.getSeatNum());
		pstmt.executeUpdate();

	}

	// 좌석 정보 삭제 메소드
	public void deleteSeat(SeatVO seatVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM Seat WHERE seatNum = ?");
		pstmt.setString(1, seatVO.getUserId());
		pstmt.executeUpdate();


	}
	
	

	
}// end class MemberDAOImpl
