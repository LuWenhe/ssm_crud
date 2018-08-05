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
 * 使用 Spring 测试模块提供的测试请求功能
 * spring4 进行测试的时候, 引入 javax.servlet-api 的版本应该大于 3.0
 * @author luwen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration		//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class EmployeeMVCTest {

	//传入 springmvc 的 ioc 容器
	@Autowired
	private WebApplicationContext applicationContext;
	
	//虚拟 mvc 请求, 获取到处理请求
	private MockMvc mockMvc;
	
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("page", "2")).andReturn();
		
		//请求成功后, 可以获取请求域中的 pageInfo 属性值
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo= (PageInfo) request.getAttribute("pageInfo");
		
		System.out.println("总页码: " + pageInfo.getPages());
		System.out.println("总记录数: " + pageInfo.getTotal());
		System.out.println("当前页数: " + pageInfo.getPageNum());
		System.out.println("每一页的记录数: " + pageInfo.getPageSize());
		
		int[] navigatepageNums = pageInfo.getNavigatepageNums();
		for(int i=0; i<navigatepageNums.length; i++) {
			System.out.print(navigatepageNums[i] + " ");
		}
		System.out.println();
	}
}
