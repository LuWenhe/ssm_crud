package edu.just.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.just.ssm.dao.DepartmentMapper;
import edu.just.ssm.model.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepts(){
		return departmentMapper.selectByExample(null);
	}
	
}
