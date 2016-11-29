package beans;

public class Student {
	public int id;
	public String name;
	public String school;
	public String major;
	public String degree;
	public String sex;
	public String nation;
	public String census;
	public int complete;


	public Student() {
		super();
	}

	public Student(int id, String name, String school, String degree, String profession, String sex, String nation,
			String accountCategory) {
		super();
		this.id = id;
		this.name = name;
		this.school = school;
		this.degree = degree;
		this.sex = sex;
		this.nation = nation;
		this.census = accountCategory;
	}

	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public int getComplete() {
		return complete;
	}
	
	public void setComplete(int complete) {
		this.complete = complete;
	}
}
