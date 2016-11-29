package service;

import java.util.List;

import beans.IP;

public interface WatchIPService {
	public List<IP> getOnlineNum();
	public void saveRecord(List<IP> ips);
    public List<String> getIPsByDistrict(String district);
}
