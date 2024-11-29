package foodSort.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import foodMenu.dao.FoodMenuDAO;
import foodMenu.dao.FoodMenuDAOImpl;
import foodSort.vo.FoodSortVO;
import foodSort.dao.FoodSortDAO;
import foodSort.dao.FoodSortDAOImpl;


public class FoodSortControllerImpl extends AbstractBaseController implements FoodSortController {
	public FoodSortDAO foodSortDAO;

	public FoodSortControllerImpl() {
		foodSortDAO = new FoodSortDAOImpl();
	}
	
	@Override
	public List<FoodSortVO> listFoodSort(FoodSortVO foodSortVO) {
		List<FoodSortVO> foodSortList = new ArrayList<FoodSortVO>();
		try {
			foodSortList = foodSortDAO.selectFoodSort(foodSortVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return foodSortList;
	}

	@Override
	public void regFoodSort(FoodSortVO foodSortVO) {
		try {
			foodSortDAO.insertFoodSort(foodSortVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modFoodSort(FoodSortVO foodMenuVO) {
		try {
			foodSortDAO.updateFoodSort(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeFoodSort(FoodSortVO foodMenuVO) {
		try {
			foodSortDAO.deleteFoodSort(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
