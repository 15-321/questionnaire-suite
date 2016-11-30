package service;

import beans.ServerConditions;

public interface WatchServerService {

	public ServerConditions getConditions();
	void saveToDB(ServerConditions conditions);
}
