package edu.just.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.just.ssm.model.Employee;
import edu.just.ssm.model.Message;
import edu.just.ssm.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/emps2")
	@ResponseBody
	public Message getEmployeesWithJson(@RequestParam(value="page", defaultValue="1")Integer pageNo, 
			Model model) {
		//����ҳ��, �Լ�ÿһҳ�Ĵ�С
		PageHelper.startPage(pageNo, 10);
		
		List<Employee> list = employeeService.getEmployees();
		
		//ʹ�� pageInfo ��װ��ѯ��Ľ��, ֻ��Ҫ�� pageInfo ����ҳ��Ϳ�����, navigatePages ��ʾ������ʾ��ҳ����
		PageInfo<Employee> page = new PageInfo<>(list, 5);

		return Message.success().add("pageInfo", page);
	}
	
	/**
	 * ��ѯԱ������, ʹ�÷�ҳ��ѯ, ������ json ��ʽ����
	 */
	@RequestMapping("/emps")
	public String getEmployees(@RequestParam(value="page", defaultValue="1")Integer pageNo, 
			Model model) {
		//����ҳ��, �Լ�ÿһҳ�Ĵ�С
		PageHelper.startPage(pageNo, 10);
		
		List<Employee> list = employeeService.getEmployees();
		
		//ʹ�� pageInfo ��װ��ѯ��Ľ��, ֻ��Ҫ�� pageInfo ����ҳ��Ϳ�����, navigatePages ��ʾ������ʾ��ҳ����
		PageInfo<Employee> page = new PageInfo<>(list, 5);
		model.addAttribute("pageInfo", page);
		
		return "list";
	}
	
}
