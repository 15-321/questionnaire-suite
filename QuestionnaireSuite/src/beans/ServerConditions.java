package beans;

public class ServerConditions {	
	private String r;//��ʾ���ж���
	private String b;//��ʾ�����Ľ���
	private String wspd;//�����ڴ���ʹ�õĴ�С
	private String free;//���е������ڴ�Ĵ�С
	private String buff;//�����洢��Ŀ¼������ʲô���ݣ�Ȩ�޵ȵĻ���
	private String cache;//ֱ�������������Ǵ򿪵��ļ�,���ļ�������
	private String si;//ÿ��Ӵ��̶��������ڴ�Ĵ�С
	private String so;//ÿ�������ڴ�д����̵Ĵ�С
	private String bi;//���豸ÿ����յĿ�����
	private String bo;//���豸ÿ�뷢�͵Ŀ�����
	private String in;//ÿ��CPU���жϴ���
	private String cs;//ÿ���������л�����
	private String us;// �û�CPUʱ��
	private String sy;//ϵͳCPUʱ��
	private String id;//���� CPUʱ��
	private String wa;//�ȴ�IO CPUʱ��
	private String st;//�������豸�л�õ�ʱ��
	
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
