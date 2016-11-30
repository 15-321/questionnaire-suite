package service;

import java.util.Date;
import java.util.StringTokenizer;

import beans.ServerConditions;
import dao.WatchServerDao;
import util.Utils;

public class WatchServerServiceImpl implements WatchServerService {


	String host = "139.199.192.64";
	String user = "root";
	String psw = "z1s9j9m4!";
	int port = 22;
	WatchServerDao dao = new WatchServerDao();
	
	@Override
	public ServerConditions getConditions() {
		String query = Utils.exec(host, user, psw, port, "vmstat 1 1");
		String[] results = query.split("\n");
		if(results.length<3)
		{
			return null;
		}
		StringTokenizer tokenStat=  new StringTokenizer(results[2]);		
		ServerConditions conditions = new ServerConditions();
		conditions.setR(tokenStat.nextToken().toString());
		conditions.setDate(new Date());
		conditions.setB(tokenStat.nextToken().toString());
		conditions.setWspd(tokenStat.nextToken().toString());
		conditions.setFree(tokenStat.nextToken().toString());
		conditions.setBuff(tokenStat.nextToken().toString());
		conditions.setCache(tokenStat.nextToken().toString());
		conditions.setSi(tokenStat.nextToken().toString());
		conditions.setSo(tokenStat.nextToken().toString());
		conditions.setBi(tokenStat.nextToken().toString());
		conditions.setBo(tokenStat.nextToken().toString());
		conditions.setIn(tokenStat.nextToken().toString());
		conditions.setCs(tokenStat.nextToken().toString());
		conditions.setUs(tokenStat.nextToken().toString());
		conditions.setSy(tokenStat.nextToken().toString());
		conditions.setId(tokenStat.nextToken().toString());
		conditions.setWa(tokenStat.nextToken().toString());
		conditions.setSt(tokenStat.nextToken().toString());
		return conditions;
	}
	public static void main(String[] args) {
		WatchServerServiceImpl ws = new WatchServerServiceImpl();
		System.out.println(ws.getConditions());
	}
	@Override
	public void saveToDB(ServerConditions conditions) {
		dao.SaveServerConditionsToDB(conditions);
	}

}
