package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * ユーザーモデルクラス
 * このクラスはユーザーの情報を保持するためのモデルです。
 */
public class UserModel implements Serializable {

	private int id;
	private String userName;
	private String email;
	private String password;
	private Date birthday;
	private Timestamp registrationDate;
	private Timestamp createDateTime;
	private int isDeleted;

	// getterメソッドとsetterメソッド
	// これらのメソッドは、 private なフィールドにアクセスするための public なインターフェースを提供します。

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}