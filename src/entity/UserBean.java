package entity;

public class UserBean {
	String userName;
	String passWord;
	private static String userString="muweijia";
	private static String passString="123";
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public boolean isSuspect(){
		if(userString.equals(userName)&&passString.equals(passWord))
			return true;
		else {
			return false;
		}
		
		
	}

}
