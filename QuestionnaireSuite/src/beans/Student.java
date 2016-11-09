package beans;

public class Student {
	public String id;
	public String name;
	public String school;
	public String degree;
	public String profession;
	public String sex;
	public String nation;
	public String accountCategory;

	public Student() {
		super();
	}

	public Student(String id, String name, String school, String degree, String profession, String sex, String nation,
			String accountCategory) {
		super();
		this.id = id;
		this.name = name;
		this.school = school;
		this.degree = degree;
		this.profession = profession;
		this.sex = sex;
		this.nation = nation;
		this.accountCategory = accountCategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
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

	public String getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(String accountCategory) {
		this.accountCategory = accountCategory;
	}

}
