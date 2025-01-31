

package Logic;

import java.sql.SQLException;
import java.util.List;

import DAO.UserDAO;
import model.UserModel;

/**
 * ユーザーロジッククラス
 */
public class UserLogic {

	/**
	 * ユーザーを1件追加します。
	 * @param model ユーザーモデルクラス
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード(SQLSTATE)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int create(UserModel model) throws ClassNotFoundException, SQLException {

		UserDAO dao = new UserDAO();

		return dao.create(model);
	}

	/**
	 * ユーザーを全件取得します。
	 * @return 検索結果（ユーザーモデルのリスト）
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserModel> find() {

		UserDAO dao = new UserDAO();

		return dao.findAll();
	}

	/*
	 * 指定ユーザーIDのユーザーを取得します。
	 * @param userId ユーザーID
	 * @return 検索結果（ユーザーモデル）
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserModel find(int Id) {//userIdをIdに変更 

		UserDAO dao = new UserDAO();

		return dao.findOne(Id);//userIdをIdに変更
	}

	/**
	 * 指定E-mailアドレスとパスワードのユーザーを取得します。
	 * @param email E-mailアドレス
	 * @param password パスワード
	 * @return 検索結果（ユーザーモデル）
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserModel find(String email, String password) {

		UserDAO dao = new UserDAO();

		return dao.findOne(email, password);
	}

	/**
	 * 指定ユーザーIDのユーザーを1件更新します。
	 * @param model ユーザーモデル
	 * @return 実行結果 1:成功、0:失敗、その他:エラーコード
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int update(UserModel model) {
		UserDAO dao = new UserDAO();
		return dao.update(model);
	}
	
	/**
	 * 新規登録したユーザーIDのユーザーを1件取得します。
	 * @param model ユーザーモデル
	 * @return 実行結果 userId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int getUserId(UserModel model) {
		UserDAO dao = new UserDAO();
		return dao.getUserId(model);
	}
}
