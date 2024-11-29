package payTime.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import payTime.dao.PayTimeDAO;
import payTime.dao.PayTimeDAOImpl;
import seat.dao.SeatDAO;
import seat.dao.SeatDAOImpl;
import seat.vo.SeatVO;
import payTime.vo.PayTimeVO;

public class PayTimeControllerImpl extends AbstractBaseController implements PayTimeController {
	public PayTimeDAO payTimeDAO;

	public PayTimeControllerImpl() {
		payTimeDAO = new PayTimeDAOImpl();
	}

	@Override
	public List<PayTimeVO> listPayTime(PayTimeVO payTimeVO) {
		List<PayTimeVO> payTimeList = new ArrayList<PayTimeVO>();
		try {
			payTimeList = payTimeDAO.selectPayTime(payTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return payTimeList;
	}

	@Override
	public void regPayTime(PayTimeVO payTimeVO) {
		try {
			payTimeDAO.insertPayTime(payTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modPayTime(PayTimeVO payTimeVO) {
		try {
			payTimeDAO.updatePayTime(payTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removePayTime(PayTimeVO payTimeVO) {
		try {
			payTimeDAO.deletePayTime(payTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public int maxPayTime() {
		int lastId = 0;
		try {
			lastId = payTimeDAO.getNextTimeId();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lastId;
	}

}
