package seat.controller;

import java.util.List;

import seat.vo.SeatVO;



public interface SeatController {
	public List<SeatVO> listSeat(SeatVO seatVO) ;
	
	public void regSeat(SeatVO seatVO) ;
	
	public void modSeat(SeatVO seatVO) ;
	
	public void removeSeat(SeatVO seatVO) ;
	
	public List<SeatVO> pickSeat(SeatVO seatVO) ;
	
	//public List<SeatVO> logMember(SeatVO seatVO);

}


