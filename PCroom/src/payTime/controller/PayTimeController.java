package payTime.controller;

import java.util.List;

import payTime.vo.PayTimeVO;



public interface PayTimeController {
	public List<PayTimeVO> listPayTime(PayTimeVO payTimeVO);
	
	public void regPayTime(PayTimeVO payTimeVO);
	
	public void modPayTime(PayTimeVO payTimeVO);
	
	public void removePayTime(PayTimeVO payTimeVO);
	
	public int maxPayTime();
}


