package admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import admin.vo.AdminVO;



public class AdminDAOImpl extends AbstractBaseDAO implements AdminDAO {
	// 관리자 정보 보여주는 메서드
	public List<AdminVO> selectAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException {
		List<AdminVO> adminList = new ArrayList<AdminVO>();
		String adminId = adminVO.getId();
		
		pstmt = conn.prepareStatement("SELECT * FROM Admin");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String Id = rs.getString("Id");
			String Pw = rs.getString("Pw");
			
			AdminVO _admin = new AdminVO();
			
			_admin.setId(Id);
			_admin.setPw(Pw);
			
			adminList.add(_admin);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + memList);
		return adminList;
	} // end list()
	
	// 관리자 정보 등록 메서드
	public void insertAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException {
		
		pstmt = conn.prepareStatement("INSERT INTO Admin (Id, Pw) VALUES (?, ?)");
		pstmt.setString(1, adminVO.getId());
		pstmt.setString(2, adminVO.getPw());
		pstmt.executeUpdate();
	}

	// 관리자 정보 수정 메소드
	public void updateAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE foodMenu SET Id = ?, Pw = ? WHERE Id = ?");
		pstmt.setString(1, adminVO.getId());
		pstmt.setString(2, adminVO.getPw());
		pstmt.setString(3, adminVO.getId());
		pstmt.executeUpdate();
		

	}

	// 관리자 정보 삭제 메소드
	public void deleteAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE Admin WHERE Id = ?");
		pstmt.setString(1, adminVO.getId());
		pstmt.executeUpdate();
	}

}// end class MemberDAOImpl
