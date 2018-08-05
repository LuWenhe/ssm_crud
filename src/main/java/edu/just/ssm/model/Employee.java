package edu.just.ssm.model;

public class Employee {
    private Integer empId;

    private String name;

    private String gender;

    private String email;

    private Integer dId;
    
    //希望查询员工信息的同时, 部门信息也是查询好的
    private Department department;
    
    public Employee() {}
    
    public Employee(Integer empId, String name, String gender, String email, Integer dId) {
		this.empId = empId;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
    public Department getDepartment() {
		return department;
	}

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}