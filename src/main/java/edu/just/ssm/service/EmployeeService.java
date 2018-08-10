package edu.just.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.just.ssm.dao.EmployeeMapper;
import edu.just.ssm.model.Employee;
import edu.just.ssm.model.EmployeeExample;
import edu.just.ssm.model.EmployeeExample.Criteria;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	/**
	 * 删除多个员工
	 * @param id
	 */
	public void deleteEmployees(List<Integer> ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		
		//delete from employee where empid in(x,x,x)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
	
	/**
	 * 删除单个员工
	 * @param id
	 */
	public void deleteEmployee(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 员工更新操作
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * 根据员工 id 查询员工信息
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(Integer empId) {
		return employeeMapper.selectByPaimayKeyWithDept(empId);
	}
	
	/**
	 * 判断是否有相同姓名的员工, 如果有返回 true, 如果没有返回 false
	 * @param name
	 * @return
	 */
	public boolean checkUser(String name) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = employeeMapper.countByExample(example);
		
		boolean flag = true;
		if(count > 0) {
			flag = false;
		}
		
		return flag;
	}
	
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
