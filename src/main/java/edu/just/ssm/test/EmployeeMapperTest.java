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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void get() {
		List<Employee> selectByExampleWithDept = employeeMapper.selectByExampleWithDept(null);
		System.out.println(selectByExampleWithDept);
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
