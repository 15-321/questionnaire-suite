package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import beans.ServerConditions;
import util.Utils;

public class WatchServerDao {
	
	public boolean SaveServerConditionsToDB(ServerConditions condition){
		Timestamp timeStamp = new Timestamp(condition.getDate().getTime()); 
		Connection conn = Utils.getConnection();
		String sql = "INSERT INTO serverconditions (date,r,b,swpd,free,buff,cache,si,so,bi,bo,ins,cs,us,sy,id,wa,st)"
				+ " VALUES ( " +"'"+timeStamp+"' ,"
				+" '"+condition.getR()+"' "+", "
				+" '"+condition.getB() +"' "+ ", " +" '"+condition.getWspd()+"' "+", "
				+" '"+condition.getFree() +"' "+ ", " +" '"+condition.getBuff()+"' "+", "
				+" '"+condition.getCache() +"' "+ ", " +" '"+condition.getSi()+"' "+", "
				+" '"+condition.getSo() +"' "+ ", " +" '"+condition.getBi()+"' "+", "
				+" '"+condition.getBo() +"' "+ ", " +" '"+condition.getIn()+"' "+", "
				+" '"+condition.getCs() +"' "+ ", " +" '"+condition.getUs()+"' "+", "
				+" '"+condition.getSy() +"' "+ ", " +" '"+condition.getId()+"' "+", "
				+" '"+condition.getWa() +"' "+ ", " +" '"+condition.getSt()+"' "+") ";
				
		System.out.println(sql);
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
