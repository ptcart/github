package foodSort.vo;

public class FoodSortVO {
	// 필드 선언
	private int sortId;
	private String Name;

	// 생성자 선언
	public FoodSortVO() {}

	public FoodSortVO(int sortId, String Name) {
		this.sortId = sortId;
		this.Name = Name;
		}
	
	@Override
    public String toString() {
        return
        		sortId + ',' +
               Name + ',';
    }

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}


}
