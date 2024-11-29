package admin.vo;

public class AdminVO {
	// 필드 선언
	private String Id;
	private String Pw;

	// 생성자 선언
	public AdminVO() {}

	public AdminVO(String Id, String Pw) {
		this.Id = Id;
		this.Pw = Pw;
	}
	
	@Override
    public String toString() {
        return
        		Id + ',' +
               Pw + ',';
    }

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getPw() {
		return Pw;
	}

	public void setPw(String Pw) {
		this.Pw = Pw;
	}
	
}
