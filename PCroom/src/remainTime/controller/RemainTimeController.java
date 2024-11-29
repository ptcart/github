package remainTime.controller;

import java.util.List;
import remainTime.vo.RemainTimeVO;

public interface RemainTimeController {
	public List<RemainTimeVO> listRemainTime(RemainTimeVO remainTime) ;
	
	public void regRemainTime(RemainTimeVO remainTime) ;
	
	public void modRemainTime(RemainTimeVO remainTime) ;
	
	public void removeRemainTime(RemainTimeVO remainTime) ;	
}


