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
 * DepartmentMapper �Ĳ�����
 * 1.���� SpringTest ģ��
 * 2.ʹ�� @ContextConfiguration ָ�� Spring �����ļ���λ��
 * 3.ֱ��ʹ�� @Autowire ��עҪʹ�õ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DepartmentMapperTest {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Test
	public void testAddWithArgs() {
		Department record = new Department(null, "������");
		departmentMapper.insertSelective(record);
	}
	
	@Test
	public void testCRUD() {
		System.out.println(departmentMapper);
	}
	
}
