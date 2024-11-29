package member.vo;

public class MemberVO {
	// 필드 선언
	private String Id;
	private String Pw;
	private String Name;
	private String Gender;
	private String PhoneNum;

	// 생성자 선언
	public MemberVO() {}

	public MemberVO(String Id, String Pw, String Name, String Gender, String PhoneNum) {
		this.Id = Id;
		this.Pw = Pw;
		this.Name = Name;
		this.Gender = Gender;
		this.PhoneNum = PhoneNum;
	}
	
	@Override
    public String toString() {
        return
               Id + ',' +
               Pw + ',' +
               Name + ',' +
               Gender +  ',' +
               PhoneNum;
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

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String Gender) {
		this.Gender = Gender;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String PhoneNum) {
		this.PhoneNum = PhoneNum;
	}
}
