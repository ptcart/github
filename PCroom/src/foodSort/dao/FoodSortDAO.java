package foodSort.dao;

import java.sql.SQLException;
import java.util.List;

import foodMenu.vo.FoodMenuVO;
import foodSort.vo.FoodSortVO;
import member.vo.MemberVO;



public interface FoodSortDAO {
	
	//public List<FoodMenuVO> loginMember() throws SQLException, ClassNotFoundException;
	
	public List<FoodSortVO> selectFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException;
	
	public void insertFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException;
	
	public void updateFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException;
	
	public void deleteFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException;
}


