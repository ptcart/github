package timeMenu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;

import seat.dao.SeatDAO;
import seat.dao.SeatDAOImpl;
import seat.vo.SeatVO;
import timeMenu.dao.TimeMenuDAO;
import timeMenu.dao.TimeMenuDAOImpl;
import timeMenu.vo.TimeMenuVO;

public class TimeMenuControllerImpl extends AbstractBaseController implements TimeMenuController {
	public TimeMenuDAO timeMenuDAO;

	public TimeMenuControllerImpl() {
		timeMenuDAO = new TimeMenuDAOImpl();
	}

	@Override
	public List<TimeMenuVO> listTimeMenu(TimeMenuVO timeMenuVO) {
		List<TimeMenuVO> timeMenuList = new ArrayList<TimeMenuVO>();
		try {
			timeMenuList = timeMenuDAO.selectTimeMenu(timeMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return timeMenuList;
	}

	@Override
	public List<TimeMenuVO> listTimeCost(TimeMenuVO timeMenuVO) {
		List<TimeMenuVO> timeMenuList = new ArrayList<TimeMenuVO>();
		try {
			timeMenuList = timeMenuDAO.selectTimeCost(timeMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return timeMenuList;
	}
	
	@Override
	public void regTimeMenu(TimeMenuVO timeMenuVO) {
		try {
			timeMenuDAO.insertTimeMenu(timeMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modTimeMenu(TimeMenuVO timeMenuVO) {
		try {
			timeMenuDAO.updateTimeMenu(timeMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeTimeMenu(TimeMenuVO timeMenuVO) {
		try {
			timeMenuDAO.deleteTimeMenu(timeMenuVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
