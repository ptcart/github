package foodMenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import foodMenu.vo.FoodMenuVO;



public class FoodMenuDAOImpl extends AbstractBaseDAO implements FoodMenuDAO {

	public List<FoodMenuVO> selectFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
	    List<FoodMenuVO> foodList = new ArrayList<FoodMenuVO>();
	    String _foodName = foodMenuVO.getName();
	    String _menuId = foodMenuVO.getMenuId(); // 메뉴 번호를 추가로 가져옴
	    
	    // 메뉴 번호가 있을 때만 해당 번호로 검색하는 쿼리 실행
	    if (_menuId != null && !_menuId.isEmpty()) {
	        pstmt = conn.prepareStatement("SELECT * FROM foodMenu WHERE MenuId = ? ORDER BY Sort");
	        pstmt.setString(1, _menuId);
	    } else if (_foodName != null && _foodName.length() != 0) { // 음식 이름이 있을 때 검색
	        pstmt = conn.prepareStatement("SELECT * FROM foodMenu WHERE Name = ? ORDER BY Sort");
	        pstmt.setString(1, _foodName);
	    } else { // 메뉴 번호와 음식 이름이 없으면 전체 메뉴를 검색
	        pstmt = conn.prepareStatement("SELECT * FROM foodMenu ORDER BY CAST(MenuId AS UNSIGNED)");
	    }

	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
	        String MenuId = rs.getString("MenuId");
	        String Sort = rs.getString("Sort");
	        String Name = rs.getString("Name");
	        int Price = rs.getInt("Price");
	        String ImagePath = rs.getString("ImagePath");
	        FoodMenuVO _foodMenu = new FoodMenuVO();

	        _foodMenu.setMenuId(MenuId);
	        _foodMenu.setSort(Sort);
	        _foodMenu.setName(Name);
	        _foodMenu.setPrice(Price);
	        _foodMenu.setImagePath(ImagePath);
	        foodList.add(_foodMenu);
	    } // end while
	    rs.close();
	    
	    return foodList;
	} // end list()

	
	public List<FoodMenuVO> selectFoodImagePath(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
		List<FoodMenuVO> foodList = new ArrayList<FoodMenuVO>();
		String _foodId = foodMenuVO.getMenuId();
		if(_foodId!= null && _foodId.length() != 0) {
			pstmt = conn.prepareStatement("SELECT * FROM foodMenu WHERE MenuId = ?");
			pstmt.setString(1, _foodId);
			
		}else {
			//pstmt = conn.prepareStatement("SELECT * FROM foodMenu ORDER BY MenuId");
		}

		
		
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String MenuId = rs.getString("MenuId");
			String Sort = rs.getString("Sort");
			String Name = rs.getString("Name");
			int Price = rs.getInt("Price");
			String ImagePath = rs.getString("ImagePath");
			FoodMenuVO _foodMenu = new FoodMenuVO();
			
			_foodMenu.setMenuId(MenuId);
			_foodMenu.setSort(Sort);
			_foodMenu.setName(Name);
			_foodMenu.setPrice(Price);
			_foodMenu.setImagePath(ImagePath);
			foodList.add(_foodMenu);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + memList);
		return foodList;
	} // end list()
	
	
	
	public String findMenuId(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
	    String menuId = null;
	    String query = "SELECT MenuId FROM foodMenu WHERE Name = ?";

	    pstmt = conn.prepareStatement(query);
	    pstmt.setString(1, foodMenuVO.getName());
	    ResultSet rs = pstmt.executeQuery();

	    // 첫 번째 결과만 가져옴
	    if (rs.next()) {
	        menuId = rs.getString("MenuId"); // MenuId를 Integer로 추출
	    }

	    rs.close();
	    return menuId;
	}


	// 회원 정보 등록 메서드
	public void insertFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
		
		pstmt = conn.prepareStatement("INSERT INTO foodMenu (MenuId, Sort, Name, Price, ImagePath) VALUES (?, ?, ?, ?, ?)");
		pstmt.setString(1, foodMenuVO.getMenuId());
		pstmt.setString(2, foodMenuVO.getSort());
		pstmt.setString(3, foodMenuVO.getName());
		pstmt.setInt(4, foodMenuVO.getPrice());
		pstmt.setString(5, foodMenuVO.getImagePath());
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 회원 정보 수정 메소드
	public void updateFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE foodMenu SET Sort = ?, Name = ?, Price = ?, ImagePath = ? WHERE MenuId = ?");
		pstmt.setString(1, foodMenuVO.getSort());
		pstmt.setString(2, foodMenuVO.getName());
		pstmt.setInt(3, foodMenuVO.getPrice());
		pstmt.setString(4, foodMenuVO.getImagePath());
		pstmt.setString(5, foodMenuVO.getMenuId());
		
		pstmt.executeUpdate();
		

	}

	// 회원 정보 삭제 메소드
	public void deleteFoodMenu(FoodMenuVO foodMenuVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM foodMenu WHERE MenuId = ?");
		pstmt.setString(1, foodMenuVO.getMenuId());
		pstmt.executeUpdate();
	}

}// end class MemberDAOImpl
