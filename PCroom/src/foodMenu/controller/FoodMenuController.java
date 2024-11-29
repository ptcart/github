package foodMenu.controller;

import java.util.List;

import foodMenu.vo.FoodMenuVO;



public interface FoodMenuController {
	public List<FoodMenuVO> listFoodMenu(FoodMenuVO foodMenuVO);
	
	// 관리자 음식종류 관리 가져오는 용도
	public List<FoodMenuVO> listFoodImagePath(FoodMenuVO foodMenuVO); 
	
	public void regFoodMenu(FoodMenuVO foodMenuVO) ;
	
	public void modFoodMenu(FoodMenuVO foodMenuVO) ;
	
	public void removeFoodMenu(FoodMenuVO foodMenuVO) ;
	
	public String findMenuIds(FoodMenuVO foodMenuVO);
	
	//public List<MemberVO> logMember(MemberVO memVO);

}


