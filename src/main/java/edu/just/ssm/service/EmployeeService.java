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
	 * ɾ�����Ա��
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
	 * ɾ������Ա��
	 * @param id
	 */
	public void deleteEmployee(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * Ա�����²���
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * ����Ա�� id ��ѯԱ����Ϣ
	 * @param id
	 * @return
	 */
	public Employee getEmployeeById(Integer empId) {
		return employeeMapper.selectByPaimayKeyWithDept(empId);
	}
	
	/**
	 * �ж��Ƿ�����ͬ������Ա��, ����з��� true, ���û�з��� false
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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getEmployees(){
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * ����Ա����Ϣ
	 */
	public int addEmployee(Employee employee) {
		return employeeMapper.insertSelective(employee);
	}
	
}
