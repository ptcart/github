package order.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import foodMenu.dao.FoodMenuDAO;
import foodMenu.dao.FoodMenuDAOImpl;
import order.vo.OrderVO;
import order.dao.OrderDAO;
import order.dao.OrderDAOImpl;


public class OrderControllerImpl extends AbstractBaseController implements OrderController {
	public OrderDAO OrderDAO;

	public OrderControllerImpl() {
		OrderDAO = new OrderDAOImpl();
	}
	
	@Override
	public List<OrderVO> listOrder(OrderVO orderVO) {
		List<OrderVO> foodSortList = new ArrayList<OrderVO>();
		try {
			foodSortList = OrderDAO.selectOrder(orderVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return foodSortList;
	}

	@Override
	public void regOrder(OrderVO orderVO) {
		try {
			OrderDAO.insertOrder(orderVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modOrder(OrderVO orderVO) {
		try {
			OrderDAO.updateOrder(orderVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeOrder(OrderVO orderVO) {
		try {
			OrderDAO.deleteOrder(orderVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public int maxOrder() {
		int lastId = 0;
		try {
			lastId = OrderDAO.getNextOrderId();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lastId;
	}

}
