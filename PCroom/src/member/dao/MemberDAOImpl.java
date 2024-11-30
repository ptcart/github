package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;

import member.vo.MemberVO;



public class MemberDAOImpl extends AbstractBaseDAO implements MemberDAO {

	public List<MemberVO> selectMember(MemberVO memVO) throws SQLException, ClassNotFoundException {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		//String _memName = memVO.getName();
		String _memId = memVO.getId();
		if(_memId != null && _memId.length() != 0) {
			pstmt = conn.prepareStatement("SELECT * FROM Member  WHERE Id = ? ORDER BY Id");
			pstmt.setString(1, _memId);
			
		}else {
			pstmt = conn.prepareStatement("SELECT * FROM Member ORDER BY Id");
		}

		
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String Id = rs.getString("Id");
			String Pw = rs.getString("Pw");
			String Name = rs.getString("Name");
			String Gender = rs.getString("Gender");
			String PhoneNum = rs.getString("PhoneNum");
			MemberVO _memVO = new MemberVO();
			
			_memVO.setId(Id);
			_memVO.setPw(Pw);
			_memVO.setName(Name);
			_memVO.setGender(Gender);
			_memVO.setPhoneNum(PhoneNum);
			memList.add(_memVO);
		} 
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + memList);
		return memList;
	} 
	
	public List<MemberVO> loginMember() throws SQLException, ClassNotFoundException {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		pstmt = conn.prepareStatement("SELECT * FROM Member");
		
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String Id = rs.getString("Id");
			String Pw = rs.getString("Pw");
			String Name = rs.getString("Name");
			String Gender = rs.getString("Gender");
			String PhoneNum = rs.getString("PhoneNum");
			MemberVO _memVO = new MemberVO();
			
			_memVO.setId(Id);
			_memVO.setPw(Pw);
			_memVO.setName(Name);
			_memVO.setGender(Gender);
			_memVO.setPhoneNum(PhoneNum);
			memList.add(_memVO);
		} // end while
		rs.close();
		return memList;
	} // end list()

	// 회원 정보 등록 메서드
	public void insertMember(MemberVO memVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO Member (Id, Pw, Name, Gender, PhoneNum) VALUES (?, ?, ?, ?, ?)");
		pstmt.setString(1, memVO.getId());
		pstmt.setString(2, memVO.getPw());
		pstmt.setString(3, memVO.getName());
		pstmt.setString(4, memVO.getGender());
		pstmt.setString(5, memVO.getPhoneNum());
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 회원 정보 수정 메소드
	public void updateMember(MemberVO memVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE Member SET Pw = ?, Name = ?, Gender = ?, PhoneNum = ? WHERE Id = ?");
		pstmt.setString(1, memVO.getPw());
		pstmt.setString(2, memVO.getName());
		pstmt.setString(3, memVO.getGender());
		pstmt.setString(4, memVO.getPhoneNum());
		pstmt.setString(5, memVO.getId());
		pstmt.executeUpdate();

	}

	// 회원 정보 삭제 메소드
	public void deleteMember(MemberVO memVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM Member WHERE Id = ?");
		pstmt.setString(1, memVO.getId());
		pstmt.executeUpdate();


	}
	
	

	
}// end class MemberDAOImpl
