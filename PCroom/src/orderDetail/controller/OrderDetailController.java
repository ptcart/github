package orderDetail.controller;

import java.util.List;

import orderDetail.vo.OrderDetailVO;



public interface OrderDetailController {
	public List<OrderDetailVO> listDetail(OrderDetailVO orderDetailVO);
	
	public void regDetail(OrderDetailVO orderDetailVO) ;
	
	public void modDetail(OrderDetailVO orderDetailVO) ;
	
	public void removeDetail(OrderDetailVO orderDetailVO) ;
	
	//public List<MemberVO> logMember(MemberVO memVO);

}


