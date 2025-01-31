package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

/**
 * ユーザーDAOクラス
 */
public class UserDAO {
	private int DB_SUCCESS = 1;

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	// 文字コードをutf8mb4_general_ciに設定
	private final String JDBC_URL = "jdbc:mysql://localhost/tweets?connectionCollation=utf8mb4_general_ci";
	private final String DB_USER = "root";
	private final String DB_PASS = "";

	/**
	 * ユーザーを全件取得します。
	 *
	 * @param Connection connection データベースコネクションのインスタンス
	 * @return 検索結果（ユーザーモデルのリスト）
	 */
	public List<UserModel> findAll() {
		// レコードを格納するArrayListを生成する。
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			// SQL文を設定する。
			String sql = "select * from users order by id";

			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をして、実行する。
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
					// レコードが存在する間、処理を行う。
					while (rs.next()) {
						// UserModelのインスタンスを生成する。
						UserModel model = new UserModel();
						// フィールドに値を設定する。
						model.setId(rs.getInt("id"));
						model.setUserName(rs.getString("user_name"));
						model.setEmail(rs.getString("email"));
						model.setPassword(rs.getString("password"));
						model.setBirthday(rs.getDate("birthday"));
						model.setCreateDateTime(rs.getTimestamp("create_date_time"));
						model.setRegistrationDate(rs.getTimestamp("registration_date"));
						model.setIsDeleted(rs.getInt("is_deleted"));

						// レコードをArrayListに追加する。
						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 指定ユーザーIDのユーザーを1件検索します。
	 *
	 * @param Connection connection データベースコネクションのインスタンス
	 * @param id         ユーザーID
	 * @return 検索結果（ユーザーモデル）
	 */
	public UserModel findOne(int id) {
		// レコードを格納するUserModelのインスタンスを生成する。
		UserModel model = new UserModel();
		try {
			// SQL文を設定する。
			String sql = "select * from users where id=?";

			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をする
			// SQLを実行する準備をして、実行する。
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					// パラメータを設定する。
					stmt.setInt(1, id);
					// SQLを実行する
					try (ResultSet rs = stmt.executeQuery()) {
						// レコードが存在するとき、
						if (rs.next()) {
							// フィールドに値を設定する。
							model.setId(rs.getInt("id"));
							model.setUserName(rs.getString("user_name"));
							model.setEmail(rs.getString("email"));
							model.setPassword(rs.getString("password"));
							model.setBirthday(rs.getDate("birthday"));
							model.setCreateDateTime(rs.getTimestamp("create_date_time"));
							model.setRegistrationDate(rs.getTimestamp("registration_date"));
							model.setIsDeleted(rs.getInt("is_deleted"));
						} else {
							model = null;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();

				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * 指定ユーザーemailとpasswordのユーザーを1件検索します。
	 *
	 *
	 * @param email    emailアドレス
	 * @param password パスワード
	 * @return 検索結果（ユーザーモデル）
	 */
	public UserModel findOne(String email, String password) {
		// レコードを格納するUserModelのインスタンスを生成する。
		UserModel model = new UserModel();
		try {
			// SQL文を設定する
			String sql = "select * from users where is_deleted=0 and email=? and password=?";

			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をする。
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setString(1, email);
				stmt.setString(2, password);
				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果を取得する。
					if (rs.next()) {
						model.setId(rs.getInt("id"));
						model.setUserName(rs.getString("user_name"));
						model.setEmail(rs.getString("email"));
						model.setPassword(rs.getString("password"));
						model.setBirthday(rs.getDate("birthday"));
						model.setCreateDateTime(rs.getTimestamp("create_date_time"));
						model.setRegistrationDate(rs.getTimestamp("registration_date"));
						model.setIsDeleted(rs.getInt("is_deleted"));
					} else {
						model = null;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * ユーザーを1件追加します。
	 *
	 * @param Connection connection データベースコネクションのインスタンス
	 * @param model      UserModel
	 * @return 実行結果 1:成功、その他:エラーコード
	 */
	public int create(UserModel model) {
		try {
			// SQL文を設定する。
			String sql = "insert into users(user_name, email,password,birthday,is_deleted)values(?, ?, ?, ?,?)";
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をする。
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			try (PreparedStatement stmt = conn.prepareStatement(sql)) { // try-with-resources構文でPreparedStatementを扱う
				// パラメータを設定
				stmt.setString(1, model.getUserName());
				stmt.setString(2, model.getEmail());
				stmt.setString(3, model.getPassword());
				stmt.setDate(4, model.getBirthday());
				stmt.setInt(5, model.getIsDeleted());

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return e.getErrorCode();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return DB_SUCCESS;
	}

	public int update(UserModel model) {
		try {
			// SQL文を設定する。
			String sql = "update users set user_name=?, email=?, password=?, birthday=?, is_deleted=? where id=?"; // birthdayを追加
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をする。
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				// パラメータを設定
				stmt.setString(1, model.getUserName());
				stmt.setString(2, model.getEmail());
				stmt.setString(3, model.getPassword());
				stmt.setDate(4, model.getBirthday()); // birthdayを設定
				stmt.setInt(5, model.getIsDeleted());
				stmt.setInt(6, model.getId()); // idを設定

				// SQLを実行する。
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return e.getErrorCode();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return DB_SUCCESS;
	}
	
	/**
	 * 新規登録したユーザーIDのユーザーを1件取得します。
	 * @param model ユーザーモデル
	 * @return 実行結果 userId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public int getUserId(UserModel model) {
		int userId = 0;
		try {
			// SQL文を設定する
			String sql = "select id from users where is_deleted=0 and email=? and password=?";

			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// SQLを実行する準備をする。
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				// パラメータを設定する。
				stmt.setString(1, model.getEmail());
				stmt.setString(2, model.getPassword());
				// SQLを実行する。
				try (ResultSet rs = stmt.executeQuery()) {
					// SQLの実行結果を取得する。
					if (rs.next()) {
						userId = rs.getInt("id");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return 0;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return userId;
	}
}