package foodSort.controller;

import java.util.List;

import foodSort.vo.FoodSortVO;



public interface FoodSortController {
	public List<FoodSortVO> listFoodSort(FoodSortVO foodSortVO);
	
	public void regFoodSort(FoodSortVO foodSortVO) ;
	
	public void modFoodSort(FoodSortVO foodSortVO) ;
	
	public void removeFoodSort(FoodSortVO foodSortVO) ;
	
	//public List<MemberVO> logMember(MemberVO memVO);

}


