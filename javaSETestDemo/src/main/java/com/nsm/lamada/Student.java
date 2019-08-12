package com.nsm.lamada;

public class Student extends People{
	
	private Integer age;
	
	private String name;
	
	public Student() {}
	
	

	public Student(Integer age, String name) {
		this.age = age;
		this.name = name;
	}



	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public void test(){
		System.out.println("Long may the sun shine!");
	}
	
	public static void main(String[] args) {
		Student stu = new Student();
	}
	
}
