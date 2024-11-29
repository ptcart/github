package timeMenu.controller;

import java.util.List;

import seat.vo.SeatVO;
import timeMenu.vo.TimeMenuVO;



public interface TimeMenuController {
	public List<TimeMenuVO> listTimeMenu(TimeMenuVO timeMenuVO);
	
	public List<TimeMenuVO> listTimeCost(TimeMenuVO timeMenuVO);
	
	public void regTimeMenu(TimeMenuVO timeMenuVO);
	
	public void modTimeMenu(TimeMenuVO timeMenuVO);
	
	public void removeTimeMenu(TimeMenuVO timeMenuVO);
}


