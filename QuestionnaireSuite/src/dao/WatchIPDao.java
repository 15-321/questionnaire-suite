package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.IP;
import util.Utils;

public class WatchIPDao {

    public void addIPs(List<IP> ips) {
        Connection con = Utils.getConnection();
        String sql = "Insert Ignore Into ips(ip, district) Values(?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            for (IP ip : ips) {
                pstmt.setString(1, ip.getIp());
                pstmt.setString(2, ip.getDistrict());
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    public List<String> getIPsByDistrict(String district) {
        Connection con = Utils.getConnection();
        String sql = "Select ip From ips Where district Like ?";
        List<String> ips = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            String t = "%" + district + "%";
            pstmt.setString(1, t);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ips.add(rs.getString(1));
            }
            return ips;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
