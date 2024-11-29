package foodMenu.vo;

public class FoodMenuVO {
	// 필드 선언
	private String MenuId;
	private String Sort;
	private String Name;
	private int Price;
	private String ImagePath;

	// 생성자 선언
	public FoodMenuVO() {}

	public FoodMenuVO(String MenuId, String Sort, String Name, int Price, String ImagePath) {
		this.MenuId = MenuId;
		this.Sort = Sort;
		this.Name = Name;
		this.Price = Price;
		this.ImagePath = ImagePath;
	}
	
	@Override
    public String toString() {
        return
        		MenuId + ',' +
               Sort + ',' +
               Name + ',' +
               Price +  ',';
    }

	public String getMenuId() {
		return MenuId;
	}

	public void setMenuId(String MenuId) {
		this.MenuId = MenuId;
	}

	public String getSort() {
		return Sort;
	}

	public void setSort(String Sort) {
		this.Sort = Sort;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int Price) {
		this.Price = Price;
	}
	
	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String ImagePath) {
		this.ImagePath = ImagePath;
	}

}
