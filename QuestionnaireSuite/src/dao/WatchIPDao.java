package dao;

import java.sql.Connection;
import java.util.List;

import beans.IP;
import util.Utils;

public class WatchIPDao {

    public void addIPs(List<IP> ips) {
        Connection con = Utils.getConnection();
        String sql = "";
        
    }

    public void getIPsByDistrict(String district) {
        Connection con = Utils.getConnection();
        String sql = "";
        
    }

}
