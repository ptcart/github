package admin.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import admin.dao.AdminDAO;
import admin.dao.AdminDAOImpl;
import admin.vo.AdminVO;


public class AdminControllerImpl extends AbstractBaseController implements AdminController {
	public AdminDAO adminDAO;

	public AdminControllerImpl() {
		adminDAO = new AdminDAOImpl();
	}
	
	@Override
	public List<AdminVO> listAdmin(AdminVO adminVO) {
		List<AdminVO> adminList = new ArrayList<AdminVO>();
		try {
			adminList = adminDAO.selectAdmin(adminVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return adminList;
	}

	@Override
	public void regAdmin(AdminVO adminVO) {
		try {
			adminDAO.insertAdmin(adminVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modAdmin(AdminVO adminVO) {
		try {
			adminDAO.updateAdmin(adminVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeAdmin(AdminVO adminVO) {
		try {
			adminDAO.deleteAdmin(adminVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
