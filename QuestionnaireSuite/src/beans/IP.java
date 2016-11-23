package beans;

public class IP {
	public String ip;
	public String district;

	public IP() {
	}

	public IP(String ip, String district) {
		this.ip = ip;
		this.district = district;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
