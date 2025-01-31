package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.TweetModel;
import model.UserModel;

/**
 * TWEETDAOクラス
 */
public class TweetDAO {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	// 文字コードをutf8mb4_general_ciに設定
	private final String JDBC_URL = "jdbc:mysql://localhost/tweets?connectionCollation=utf8mb4_general_ci";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	private final int DELETE = 1;

	/**
	 * TWEETリストを全件取得します
	 * 
	 * @return TWEETリスト
	 */
	public List<TweetModel> findAll(UserModel user) {

		List<TweetModel> list = new ArrayList<TweetModel>();
		String sql = "select "
				+ "* "
				+ "from tweets "
				+ "where is_deleted=0 "
				+ "and user_id=? "
				+ "order by id desc";
		//String sql = "SELECT * FROM tweets WHERE user_id = ?"

		try {
			// JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			// try-with-resourcesの構文
			// データベースへ接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				// SQLを実行
				try (PreparedStatement stmt = conn.prepareStatement(sql) ) {
					//パラメータを設定
					stmt.setInt(1, user.getId());
					ResultSet rs = stmt.executeQuery();
				
					// SQLの実行結果をArrayListに格納
					while (rs.next()) {
						TweetModel model = new TweetModel();
						model.setId(rs.getInt("id"));
						model.setuserId(rs.getInt("user_id"));
						model.setTweet(rs.getString("tweet"));
						model.setisDeleted(rs.getInt("is_deleted"));
						model.setcreateDateTime(Timestamp.valueOf(rs.getString("create_date_time")));
						model.setupDateTime(Timestamp.valueOf(rs.getString("update_date_time")));

						list.add(model);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return list;
	}

	/**
	 * TODOリストを追加します
	 * 
	 * @param model
	 * @return
	 */
	public boolean create(TweetModel model,UserModel user) {
		Connection conn = null;
		try {
			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// インサート文の準備（idは自動連番なので指定しなくて良い）
			String sql = "insert into tweets (user_id, tweet) values (?, ?)"; // ここはuser_idとtweetのカラムに値を入れるように修正
			PreparedStatement stmt = conn.prepareStatement(sql);

			// パラメータを設定
			stmt.setInt(1, user.getId()); // user_idを設定
			stmt.setString(2, model.getTweet());

			// インサート文を実行
			int result = stmt.executeUpdate();

			// 結果行数が1でなかったら、falseを返却
			if (result != 1) {
				return false;
			}

			// PreparedStatementを明示的に閉じる
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * TWEETリストを更新します
	 * 
	 * @param model
	 * @return
	 */
	public boolean update(TweetModel model) {
		Connection conn = null;
		try {
			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// インサート文の準備（idは自動連番なので指定しなくて良い）
			String sql = "update tweets set "
					+ "is_deleted=? "
					+ "where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// パラメータを設定
			stmt.setInt(1, model.getisDeleted()); // 1番目のパラメータにis_deletedを設定
			stmt.setInt(2, model.getId()); // 2番目のパラメータにidを設定

			// アップデート文を実行
			int result = stmt.executeUpdate();
			// 結果行数が1でなかったら、falseを返却
			if (result != 1) {
				return false;
			}

			// PreparedStatementを明示的に閉じる
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean update(int id) {
		Connection conn = null;
		try {
			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// インサート文の準備（idは自動連番なので指定しなくて良い）
			String sql = "update tweets set "
					+ "is_deleted=? "
					+ "where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// パラメータを設定
			stmt.setInt(1, DELETE); // 1番目のパラメータにis_deletedを設定
			stmt.setInt(2,  id); // 2番目のパラメータにidを設定

			// アップデート文を実行
			int result = stmt.executeUpdate();
			// 結果行数が1でなかったら、falseを返却
			if (result != 1) {
				return false;
			}

			// PreparedStatementを明示的に閉じる
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}