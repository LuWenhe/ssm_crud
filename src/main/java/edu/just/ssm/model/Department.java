package edu.just.ssm.model;

public class Department {
    private Integer deptId;

    private String name;

    public Department() {}
    
    public Department(Integer deptId, String name) {
		this.deptId = deptId;
		this.name = name;
	}

	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}