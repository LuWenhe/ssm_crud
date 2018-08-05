package edu.just.ssm.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

/**
 * ʹ�� Spring ����ģ���ṩ�Ĳ���������
 * spring4 ���в��Ե�ʱ��, ���� javax.servlet-api �İ汾Ӧ�ô��� 3.0
 * @author luwen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration		//������Web��Ŀ��Junit��Ҫģ��ServletContext�����������Ҫ�����ǵĲ��������@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class EmployeeMVCTest {

	//���� springmvc �� ioc ����
	@Autowired
	private WebApplicationContext applicationContext;
	
	//���� mvc ����, ��ȡ����������
	private MockMvc mockMvc;
	
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testPage() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("page", "2")).andReturn();
		
		//����ɹ���, ���Ի�ȡ�������е� pageInfo ����ֵ
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo= (PageInfo) request.getAttribute("pageInfo");
		
		System.out.println("��ҳ��: " + pageInfo.getPages());
		System.out.println("�ܼ�¼��: " + pageInfo.getTotal());
		System.out.println("��ǰҳ��: " + pageInfo.getPageNum());
		System.out.println("ÿһҳ�ļ�¼��: " + pageInfo.getPageSize());
		
		int[] navigatepageNums = pageInfo.getNavigatepageNums();
		for(int i=0; i<navigatepageNums.length; i++) {
			System.out.print(navigatepageNums[i] + " ");
		}
		System.out.println();
	}
}
