package edu.just.ssm.model;

import javax.validation.constraints.Pattern;

public class Employee {
    private Integer empId;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", 
    		message="用户名格式不正确")
    private String name;

    private String gender;

    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", 
    		message="邮箱格式不正确")
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

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", gender=" + gender + ", email=" + email + ", dId="
				+ dId + ", department=" + department + "]";
	}
    
}