package beans;

public class ServerConditions {	
	private String r;//表示运行队列
	private String b;//表示阻塞的进程
	private String wspd;//虚拟内存已使用的大小
	private String free;//空闲的物理内存的大小
	private String buff;//用来存储，目录里面有什么内容，权限等的缓存
	private String cache;//直接用来记忆我们打开的文件,给文件做缓冲
	private String si;//每秒从磁盘读入虚拟内存的大小
	private String so;//每秒虚拟内存写入磁盘的大小
	private String bi;//块设备每秒接收的块数量
	private String bo;//块设备每秒发送的块数量
	private String in;//每秒CPU的中断次数
	private String cs;//每秒上下文切换次数
	private String us;// 用户CPU时间
	private String sy;//系统CPU时间
	private String id;//空闲 CPU时间
	private String wa;//等待IO CPU时间
	private String st;//从虚拟设备中获得的时间
	
	public ServerConditions() {
		super();
	}

	public ServerConditions(String r, String b, String wspd, String free, String buff, String cache, String si,
			String so, String bi, String bo, String in, String cs, String us, String sy, String id, String wa,
			String st) {
		super();
		this.r = r;
		this.b = b;
		this.wspd = wspd;
		this.free = free;
		this.buff = buff;
		this.cache = cache;
		this.si = si;
		this.so = so;
		this.bi = bi;
		this.bo = bo;
		this.in = in;
		this.cs = cs;
		this.us = us;
		this.sy = sy;
		this.id = id;
		this.wa = wa;
		this.st = st;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getWspd() {
		return wspd;
	}

	public void setWspd(String wspd) {
		this.wspd = wspd;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getBuff() {
		return buff;
	}

	public void setBuff(String buff) {
		this.buff = buff;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public String getBo() {
		return bo;
	}

	public void setBo(String bo) {
		this.bo = bo;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWa() {
		return wa;
	}

	public void setWa(String wa) {
		this.wa = wa;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	@Override
	public String toString() {
		return "ServerConditions [r=" + r + ", b=" + b + ", wspd=" + wspd + ", free=" + free + ", buff=" + buff
				+ ", cache=" + cache + ", si=" + si + ", so=" + so + ", bi=" + bi + ", bo=" + bo + ", in=" + in
				+ ", cs=" + cs + ", us=" + us + ", sy=" + sy + ", id=" + id + ", wa=" + wa + ", st=" + st + "]";
	}
	
	

}
