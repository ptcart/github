package foodMenu.dao;

import java.sql.SQLException;
import java.util.List;

import foodMenu.vo.FoodMenuVO;
import member.vo.MemberVO;



public interface FoodMenuDAO {
	
	//public List<FoodMenuVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<FoodMenuVO> selectFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
	
	public List<FoodMenuVO> selectFoodImagePath(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
	
	public void insertFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
	
	public void updateFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
	
	public void deleteFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
	
	public String findMenuId(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException;
}


