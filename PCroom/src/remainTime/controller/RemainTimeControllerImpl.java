package remainTime.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;
import remainTime.dao.RemainTimeDAO;
import remainTime.dao.RemainTimeDAOImpl;
import remainTime.vo.RemainTimeVO;
import seat.dao.SeatDAO;
import seat.dao.SeatDAOImpl;
import seat.vo.SeatVO;

public class RemainTimeControllerImpl extends AbstractBaseController implements RemainTimeController {
	public RemainTimeDAO remainTimeDAO;

	public RemainTimeControllerImpl() {
		remainTimeDAO = new RemainTimeDAOImpl();
	}

	@Override
	public List<RemainTimeVO> listRemainTime(RemainTimeVO remainTimeVO) {
		List<RemainTimeVO> remainTimeList = new ArrayList<RemainTimeVO>();
		try {
			remainTimeList = remainTimeDAO.selectRemainTime(remainTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return remainTimeList;
	}

	@Override
	public void regRemainTime(RemainTimeVO remainTimeVO) {
		try {
			remainTimeDAO.insertRemainTime(remainTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modRemainTime(RemainTimeVO remainTimeVO) {
		try {
			remainTimeDAO.updateRemainTime(remainTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeRemainTime(RemainTimeVO remainTimeVO) {
		try {
			remainTimeDAO.deleteRemainTime(remainTimeVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
