package timeMenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import seat.vo.SeatVO;
import timeMenu.vo.TimeMenuVO;



public class TimeMenuDAOImpl extends AbstractBaseDAO implements TimeMenuDAO {

	public List<TimeMenuVO> selectTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException {
		List<TimeMenuVO> timeList = new ArrayList<TimeMenuVO>();
		//TimeMenuVO tm = new TimeMenuVO();
		String paymentMethod = timeMenuVO.getPayment();
		Integer timeNum = timeMenuVO.getTimeNum(); // timeNum 값 가져오기
		//String seatName = SeatVO.getId();

		if (paymentMethod == null || paymentMethod.isEmpty()) {
		    String sql = "SELECT * FROM timeMenu ORDER BY timeNum";
		    pstmt = conn.prepareStatement(sql);
		} 

		else {
		    String sql = "SELECT * FROM timeMenu WHERE payment = ? ORDER BY timeNum";
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, paymentMethod);
		}
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int timeNum2 = rs.getInt("timeNum");
			int addTime = rs.getInt("addTime");
			String payment = rs.getString("payment");
			int cost = rs.getInt("cost");

			TimeMenuVO _timeMenuVO = new TimeMenuVO();
			_timeMenuVO.setTimeNum(timeNum2);
			_timeMenuVO.setAddTime(addTime);
			_timeMenuVO.setPayment(payment);
			_timeMenuVO.setCost(cost);

			timeList.add(_timeMenuVO);
		} 
		rs.close();
		return timeList;
	} 
	
	public List<TimeMenuVO> selectTimeCost(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException {
		List<TimeMenuVO> timeList = new ArrayList<TimeMenuVO>();
		//TimeMenuVO tm = new TimeMenuVO();
		Integer timeNum = timeMenuVO.getTimeNum(); // timeNum 값 가져오기
		//String seatName = SeatVO.getId();

		String sql = "SELECT * FROM timeMenu WHERE timeNum = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, timeNum);

	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int timeNum2 = rs.getInt("timeNum");
			int addTime = rs.getInt("addTime");
			String payment = rs.getString("payment");
			int cost = rs.getInt("cost");

			TimeMenuVO _timeMenuVO = new TimeMenuVO();
			_timeMenuVO.setTimeNum(timeNum2);
			_timeMenuVO.setAddTime(addTime);
			_timeMenuVO.setPayment(payment);
			_timeMenuVO.setCost(cost);

			timeList.add(_timeMenuVO);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + seatList);
		return timeList;
	} // end list()
	
	
	
	

	// 시간메뉴 정보 등록 메서드
	public void insertTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO timeMenu (timeNum, addTime, payment, cost) VALUES (?, ?, ?, ?)");
		pstmt.setInt(1, timeMenuVO.getTimeNum());
		pstmt.setInt(2, timeMenuVO.getAddTime());
		pstmt.setString(3, timeMenuVO.getPayment());
		pstmt.setInt(4, timeMenuVO.getCost());
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 시간메뉴 정보 수정 메소드
	public void updateTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE timeMenu SET addTime = ?, payment = ?, cost = ? WHERE timeNum = ?");
		pstmt.setInt(1, timeMenuVO.getAddTime());
		pstmt.setString(2, timeMenuVO.getPayment());
		pstmt.setInt(3, timeMenuVO.getCost());
		pstmt.setInt(4, timeMenuVO.getTimeNum());
		pstmt.executeUpdate();

	}

	// 시간메뉴 정보 삭제 메소드
	public void deleteTimeMenu(TimeMenuVO timeMenuVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM timeMenu WHERE timeNum = ?");
		pstmt.setInt(1, timeMenuVO.getTimeNum());
		pstmt.executeUpdate();


	}	
}
