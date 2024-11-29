package foodMenu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import foodMenu.dao.FoodMenuDAO;
import foodMenu.dao.FoodMenuDAOImpl;
import foodMenu.vo.FoodMenuVO;


public class FoodMenuControllerImpl extends AbstractBaseController implements FoodMenuController {
	public FoodMenuDAO foodMenuDAO;

	public FoodMenuControllerImpl() {
		foodMenuDAO = new FoodMenuDAOImpl();
	}
	
	
	@Override
	public String findMenuIds(FoodMenuVO foodMenuVO) {
	    String menuId = null;
	    try {
	        // DAO 메서드에서 단일 menuId 반환
	        menuId = foodMenuDAO.findMenuId(foodMenuVO);
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return menuId;
	}

	
	@Override
	public List<FoodMenuVO> listFoodMenu(FoodMenuVO foodMenuVO) {
		List<FoodMenuVO> foodMenuList = new ArrayList<FoodMenuVO>();
		try {
			foodMenuList = foodMenuDAO.selectFoodMenu(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return foodMenuList;
	}
	
	@Override
	public List<FoodMenuVO> listFoodImagePath(FoodMenuVO foodMenuVO) {
		List<FoodMenuVO> foodMenuList = new ArrayList<FoodMenuVO>();
		try {
			foodMenuList = foodMenuDAO.selectFoodImagePath(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return foodMenuList;
	}

	@Override
	public void regFoodMenu(FoodMenuVO foodMenuVO) {
		try {
			foodMenuDAO.insertFoodMenu(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modFoodMenu(FoodMenuVO foodMenuVO) {
		try {
			foodMenuDAO.updateFoodMenu(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeFoodMenu(FoodMenuVO foodMenuVO) {
		try {
			foodMenuDAO.deleteFoodMenu(foodMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
