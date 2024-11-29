package seat.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseController;

import seat.dao.SeatDAO;
import seat.dao.SeatDAOImpl;
import seat.vo.SeatVO;

public class SeatControllerImpl extends AbstractBaseController implements SeatController {
	public SeatDAO seatDAO;

	public SeatControllerImpl() {
		seatDAO = new SeatDAOImpl();
	}

	@Override
	public List<SeatVO> listSeat(SeatVO seatVO) {
		List<SeatVO> seatList = new ArrayList<SeatVO>();
		try {
			seatList = seatDAO.selectSeat(seatVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return seatList;
	}

	@Override
	public List<SeatVO> pickSeat(SeatVO seatVO) {
		List<SeatVO> seatList = new ArrayList<SeatVO>();
		try {
			seatList = seatDAO.pickSeat(seatVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return seatList;
	}
	
	@Override
	public void regSeat(SeatVO seatVO) {
		try {
			seatDAO.insertSeat(seatVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modSeat(SeatVO seatVO) {
		try {
			seatDAO.updateSeat(seatVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeSeat(SeatVO seatVO) {
		try {
			seatDAO.deleteSeat(seatVO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
