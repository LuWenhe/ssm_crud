package edu.just.ssm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.just.ssm.dao.DepartmentMapper;
import edu.just.ssm.model.Department;
import edu.just.ssm.model.DepartmentExample;
import edu.just.ssm.model.EmployeeExample;
import edu.just.ssm.model.EmployeeExample.Criteria;

/**
 * DepartmentMapper 的测试类
 * 1.导入 SpringTest 模块
 * 2.使用 @ContextConfiguration 指定 Spring 配置文件的位置
 * 3.直接使用 @Autowire 标注要使用的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DepartmentMapperTest {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Test
	public void testAddWithArgs() {
		Department record = new Department(null, "开发部");
		departmentMapper.insertSelective(record);
	}
	
	@Test
	public void testCRUD() {
		System.out.println(departmentMapper);
	}
	
}
