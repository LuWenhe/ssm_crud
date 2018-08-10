package edu.just.ssm.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.just.ssm.dao.EmployeeMapper;
import edu.just.ssm.model.Employee;
import edu.just.ssm.model.EmployeeExample;
import edu.just.ssm.model.EmployeeExample.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void deleteEmp() {
		employeeMapper.deleteByPrimaryKey(153);
		System.out.println("成功");
	}
	
	@Test
	public void checkUser() {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo("陆文赫2");
	
		long count = employeeMapper.countByExample(example);
		System.out.println(count);
	}
	
	@Test
	public void getEmp() {
		Employee selectByPaimayKeyWithDept = employeeMapper.selectByPaimayKeyWithDept(1);
		System.out.println(selectByPaimayKeyWithDept);
	}
	
	@Test
	public void get() {
		List<Employee> selectByExampleWithDept = employeeMapper.selectByExampleWithDept(null);
		System.out.println(selectByExampleWithDept);
	}
	
	@Test
	public void addEmployee2() {
		int a = employeeMapper.insertSelective(new Employee(null, "hello", "m", "ww", 3));
		System.out.println(a);
	}
	
	/**
	 * 测试员工插入, 使用可以执行批量操作的 sqlSession
	 */
	@Test
	public void addEmployee() {
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		
		for(int i=0; i<100; i++) {
			String uuid = UUID.randomUUID().toString().substring(0, 4);
			int did = (int) (Math.random()*6 + 1);
			mapper.insertSelective(new Employee(null, uuid, "m",  uuid + "@163.com", did));
		}
	}
	
	@Test
	public void test() {
		System.out.println(employeeMapper);
	}
	
}
