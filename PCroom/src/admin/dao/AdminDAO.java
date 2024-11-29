package admin.dao;

import java.sql.SQLException;
import java.util.List;

import admin.vo.AdminVO;



public interface AdminDAO {
		
	public List<AdminVO> selectAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException;
	
	public void insertAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException;
	
	public void updateAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException;
	
	public void deleteAdmin(AdminVO adminVO) throws SQLException, ClassNotFoundException;
	
}


