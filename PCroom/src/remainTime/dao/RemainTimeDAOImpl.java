package remainTime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import remainTime.vo.RemainTimeVO;
import seat.vo.SeatVO;



public class RemainTimeDAOImpl extends AbstractBaseDAO implements RemainTimeDAO {

	// 시간 정보 검색 메서드
	public List<RemainTimeVO> selectRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException {
		List<RemainTimeVO> remainTimeList = new ArrayList<RemainTimeVO>();
		String Id = remainTimeVO.getUserId();

		pstmt = conn.prepareStatement("SELECT * FROM remainTime WHERE userId = ?");
		pstmt.setString(1, Id);
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String userId = rs.getString("userId");
			int remainTime = rs.getInt("remainTime");
			int useMoney = rs.getInt("useMoney");

			RemainTimeVO _remainTimeVO = new RemainTimeVO();
			
			_remainTimeVO.setUserId(userId);
			_remainTimeVO.setRemainTime(remainTime);
			_remainTimeVO.setUseMoney(useMoney);

			remainTimeList.add(_remainTimeVO);
		} 
		rs.close();
		return remainTimeList;
	}

	// 시간 정보 등록 메서드
	public void insertRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO RemainTime (userId, remainTime, useMoney) VALUES (?, ?, ?)");
		pstmt.setString(1, remainTimeVO.getUserId());
		pstmt.setInt(2, remainTimeVO.getRemainTime());
		pstmt.setInt(3, remainTimeVO.getUseMoney());
		pstmt.executeUpdate();
	} // end insertMember()

	// 시간 정보 수정 메소드
	public void updateRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE RemainTime SET remainTime = ?, useMoney = ? WHERE userId = ?");
		pstmt.setInt(1, remainTimeVO.getRemainTime());
		pstmt.setInt(2, remainTimeVO.getUseMoney());
		pstmt.setString(3, remainTimeVO.getUserId());
		pstmt.executeUpdate();
	}

	// 좌석 정보 삭제 메소드
	public void deleteRemainTime(RemainTimeVO remainTimeVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM RemainTime WHERE userId = ?");
		pstmt.setString(1, remainTimeVO.getUserId());
		pstmt.executeUpdate();
	}

}
