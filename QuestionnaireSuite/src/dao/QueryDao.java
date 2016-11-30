package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Utils;

public class QueryDao {
	public JSONArray get(String query) {
		Connection connection = Utils.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			JSONArray jsonArray = new JSONArray();
			while (resultSet.next()) {
				JSONObject object = new JSONObject();
				object.put("id", resultSet.getInt("id"));
				object.put("name", resultSet.getString("name"));
				object.put("sex", resultSet.getString("sex"));
				object.put("school", resultSet.getString("school"));
				object.put("major", resultSet.getString("major"));
				object.put("degree", resultSet.getString("degree"));
				object.put("nation", resultSet.getString("nation"));
				object.put("census", resultSet.getString("census"));
				jsonArray.add(object);
			}
//			System.out.println("jsonArray:"+jsonArray.toString());
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
