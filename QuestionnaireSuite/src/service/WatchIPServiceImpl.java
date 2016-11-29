package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;

import beans.IP;
import dao.WatchIPDao;
import net.sf.json.JSONObject;
import util.Utils;

public class WatchIPServiceImpl implements WatchIPService {
    String localhost = "10.135.143.149";
    String host = "139.199.192.64";
    String user = "root";
    String psw = "z1s9j9m4!";
    int port = 22;
    
    WatchIPDao dao = new WatchIPDao();
    
    public List<String> getIPsByDistrict(String district) {
        return dao.getIPsByDistrict(district);
    }
    
    public void saveRecord(List<IP> ips) {
        dao.addIPs(ips);
    }
    
    public List<IP> getOnlineNum() {
        String result = Utils.exec(host, user, psw, port, "netstat -ant");
        if (result!=null && !"".equals(result)) {
            List<IP> ips = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(result, "\n");
            tokenizer.nextToken();
            tokenizer.nextToken();
            while (tokenizer.hasMoreTokens()) {
                String[] record = tokenizer.nextToken().split("\\s+");
                if ((localhost+":"+port).equals(record[3])) {
                    IP ip = new IP();
                    String ipv4 = record[4].split(":")[0];
                    ip.setIp(ipv4);
                    if (!ips.contains(ip)) {
                        ips.add(ip);
                    }
                }
            }
            for (IP ip : ips) {
                ip.setDistrict(getAddressByIP(ip.getIp()));
            }
            return ips;
        } else {
            return null;
        }
    }
    
    private String getAddressByIP(String ip) {
        String path = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;  
        String inputline="";  
        String info="";  
          
        try {  
            URL url = new URL(path);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setReadTimeout(10*1000);  
            conn.setRequestMethod("GET");  
              
            InputStreamReader  inStream = new InputStreamReader(conn.getInputStream(),"UTF-8");  
            BufferedReader buffer=new BufferedReader(inStream);  
               
            while((inputline=buffer.readLine())!=null){  
                info+=inputline;  
            }  
              
        } catch (Exception e) {
            return "未知地区";
        }
      
        JSONObject jsonob = JSONObject.fromObject((JSONObject.fromObject(info).getString("data")));  
        String district = StringEscapeUtils.escapeSql(jsonob.getString("region")) 
                + StringEscapeUtils.escapeSql(jsonob.getString("city"));  
          
        return district; 
    }
    
}
