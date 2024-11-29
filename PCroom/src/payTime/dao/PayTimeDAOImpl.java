package payTime.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import seat.vo.SeatVO;
import payTime.vo.PayTimeVO;



public class PayTimeDAOImpl extends AbstractBaseDAO implements PayTimeDAO {

	public List<PayTimeVO> selectPayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException {
		List<PayTimeVO> payList = new ArrayList<PayTimeVO>();
		//String seatName = SeatVO.getId();

		pstmt = conn.prepareStatement("SELECT * FROM payTime ORDER BY timeId");
//		pstmt.setString(1, seatName);
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int timeId = rs.getInt("timeId");
			Timestamp timestamp = rs.getTimestamp("payTime");
            LocalDateTime payTime = timestamp != null ? timestamp.toLocalDateTime() : null;
			String userId = rs.getString("userId");
			int timeNum = rs.getInt("timeNum");

			PayTimeVO _payTimeVO = new PayTimeVO();
			_payTimeVO.setTimeNum(timeNum);
			_payTimeVO.setPayTime(payTime);
			_payTimeVO.setUserId(userId);
			_payTimeVO.setTimeNum(timeNum);

			payList.add(_payTimeVO);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + seatList);
		return payList;
	} // end list()

	// 시간메뉴 정보 등록 메서드
	public void insertPayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException {
	    // 1. 현재 payTime 테이블의 레코드 수 확인
	    int currentCount = 0;
	    String countQuery = "SELECT COUNT(*) AS rowCount FROM payTime";
	    try (PreparedStatement countStmt = conn.prepareStatement(countQuery);
	         ResultSet rs = countStmt.executeQuery()) {
	        if (rs.next()) {
	            currentCount = rs.getInt("rowCount");
	        }
	    }

	    // 2. 다음 timeId 계산 (1~10, 11~20 순서)
	    int nextTimeId = (currentCount / 10) * 10 + (currentCount % 10 + 1);

	    // 3. INSERT 실행
	    String insertQuery = "INSERT INTO payTime (timeId, payTime, userId, timeNum) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
	        pstmt.setInt(1, nextTimeId); // 계산된 timeId
	        pstmt.setObject(2, payTimeVO.getPayTime()); // payTime 값
	        pstmt.setString(3, payTimeVO.getUserId()); // userId
	        pstmt.setInt(4, payTimeVO.getTimeNum()); // timeNum

	        pstmt.executeUpdate();
	        System.out.println("Record inserted with timeId: " + nextTimeId);
	    }
	}


	// 시간메뉴 정보 수정 메소드
	public void updatePayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE payTime SET timeNum = ?, payTime = ?, userId = ? WHERE timeId = ?");
		pstmt.setInt(1, payTimeVO.getTimeNum());
		pstmt.setObject(2, payTimeVO.getPayTime());
		pstmt.setString(3, payTimeVO.getUserId());
		pstmt.setInt(4, payTimeVO.getTimeId());
		pstmt.executeUpdate();

	}

	// 시간메뉴 정보 삭제 메소드
	public void deletePayTime(PayTimeVO payTimeVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM payTime WHERE TimeId = ?");
		pstmt.setInt(1, payTimeVO.getTimeId());
		pstmt.executeUpdate();


	}
	
	public int getNextTimeId() throws SQLException, ClassNotFoundException {
        int nextTimeId = 1; // 기본값으로 1을 설정 (만약 테이블이 비어있다면 첫 번째 ID는 1)
        
        String query = "SELECT MAX(timeId) AS maxTimeId FROM payTime";
        
        pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int maxTimeId = rs.getInt("maxTimeId");
            if (maxTimeId != 0) {
                nextTimeId = maxTimeId + 1;
            }
        }
        
        rs.close();
        return nextTimeId;
    }
	
	

	
}// end class MemberDAOImpl
