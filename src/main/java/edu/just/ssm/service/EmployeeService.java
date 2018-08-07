package edu.just.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.just.ssm.dao.EmployeeMapper;
import edu.just.ssm.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getEmployees(){
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * 新增员工信息
	 */
	public int addEmployee(Employee employee) {
		return employeeMapper.insertSelective(employee);
	}
	
}
