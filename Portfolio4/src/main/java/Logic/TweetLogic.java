package Logic;

import java.util.List;

import DAO.TweetDAO;
import model.TweetModel;
import model.UserModel;
/**
 * tweetロジッククラス
 */
public class TweetLogic {
	
	
	public List<TweetModel> getTweet(UserModel user){
		TweetDAO dao = new TweetDAO();
		List<TweetModel> list = dao.findAll(user);
		return list;
	}
	
	public boolean create(TweetModel model,UserModel user) {
		TweetDAO dao = new TweetDAO();
		return dao.create(model,user);
	}
	public boolean update(TweetModel model) {
		
	TweetDAO dao = new TweetDAO();
		return dao.update(model);
	}
	public boolean delete(int id) {
		TweetDAO dao = new TweetDAO();
		return dao.update(id);
	}
	
}

