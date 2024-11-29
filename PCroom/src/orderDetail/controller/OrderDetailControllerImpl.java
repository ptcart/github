package orderDetail.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import orderDetail.vo.OrderDetailVO;
import orderDetail.dao.OrderDetailDAO;
import orderDetail.dao.OrderDetailDAOImpl;


public class OrderDetailControllerImpl extends AbstractBaseController implements OrderDetailController {
	public OrderDetailDAO orderDetailDAO;

	public OrderDetailControllerImpl() {
		orderDetailDAO = new OrderDetailDAOImpl();
	}
	
	@Override
	public List<OrderDetailVO> listDetail(OrderDetailVO orderDetailVO) {
		List<OrderDetailVO> detailList = new ArrayList<OrderDetailVO>();
		try {
			detailList = orderDetailDAO.selectDetail(orderDetailVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return detailList;
	}

	@Override
	public void regDetail(OrderDetailVO orderDetailVO) {
		try {
			orderDetailDAO.insertDetail(orderDetailVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modDetail(OrderDetailVO orderDetailVO) {
		try {
			orderDetailDAO.updateDetail(orderDetailVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeDetail(OrderDetailVO orderDetailVO) {
		try {
			orderDetailDAO.deleteDetail(orderDetailVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
