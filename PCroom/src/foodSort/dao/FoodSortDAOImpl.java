package foodSort.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.base.AbstractBaseDAO;
import foodSort.vo.FoodSortVO;




public class FoodSortDAOImpl extends AbstractBaseDAO implements FoodSortDAO {
	// 음식 종류 정보 검색 메소드
	public List<FoodSortVO> selectFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException {
		List<FoodSortVO> sortList = new ArrayList<FoodSortVO>();
		String _sortName = foodSortVO.getName();

		if(_sortName != null && _sortName.length() != 0) {
			pstmt = conn.prepareStatement("SELECT * FROM foodSort  WHERE Name = ?");
			pstmt.setString(1, _sortName);
			
		}else {
			pstmt = conn.prepareStatement("SELECT * FROM foodSort");
		}
		
	
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int SortId = rs.getInt("sortId");
			String Name = rs.getString("Name");
			FoodSortVO _SortMenu = new FoodSortVO();
			
			_SortMenu.setSortId(SortId);
			_SortMenu.setName(Name);

			sortList.add(_SortMenu);
		} // end while
		rs.close();
		//System.out.println("DB에서 나온 결과 : " + memList);
		return sortList;
	} // end list()
	
	// 음식 종류 정보 등록 메서드
	public void insertFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("INSERT INTO foodSort (SortId, Name) VALUES (?, ?)");
		pstmt.setInt(1, foodSortVO.getSortId());
		pstmt.setString(2, foodSortVO.getName());
		
		pstmt.executeUpdate();
	} // end insertMember()

	// 음식 종류 정보 수정 메소드
	public void updateFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("UPDATE foodSort SET Name = ? WHERE SortId = ?");
		pstmt.setString(1, foodSortVO.getName());
		pstmt.setInt(2, foodSortVO.getSortId());
		pstmt.executeUpdate();

	}

	// 음식 종류 정보 삭제 메소드
	public void deleteFoodSort(FoodSortVO foodSortVO) throws SQLException, ClassNotFoundException {
		pstmt = conn.prepareStatement("DELETE FROM foodSort WHERE sortId = ?");
		pstmt.setInt(1, foodSortVO.getSortId());
		pstmt.executeUpdate();
	}

}// end class MemberDAOImpl
