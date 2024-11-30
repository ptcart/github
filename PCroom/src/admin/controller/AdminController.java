package admin.controller;

import java.util.List;

import admin.vo.AdminVO;



public interface AdminController {
	
	public List<AdminVO> listAdmin(AdminVO adminVO);
	
	public void regAdmin(AdminVO adminVO) ;
	
	public void modAdmin(AdminVO adminVO) ;
	
	public void removeAdmin(AdminVO adminVO) ;

	
}


