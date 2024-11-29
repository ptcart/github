package order.controller;

import java.util.List;

import order.vo.OrderVO;



public interface OrderController {
	public List<OrderVO> listOrder(OrderVO orderVO);
	
	public void regOrder(OrderVO orderVO) ;
	
	public void modOrder(OrderVO orderVO) ;
	
	public void removeOrder(OrderVO orderVO) ;
	
	public int maxOrder();
	
	//public List<MemberVO> logMember(MemberVO memVO);

}


