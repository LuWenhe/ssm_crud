package edu.just.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	/**
	 * 用于员工保存
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Message addEmployee(Employee employee) {
		employeeService.addEmployee(employee);
		return Message.success();
	}
	
	/**
	 * 用于展示员工的数据, 默认从第一页开始
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	@ResponseBody
	public Message getEmployeesWithJson(@RequestParam(value="page", defaultValue="1")Integer pageNo) {
		//传入页面, 以及每一页的大小
		PageHelper.startPage(pageNo, 10);
		
		List<Employee> list = employeeService.getEmployees();
		
		//使用 pageInfo 包装查询后的结果, 只需要将 pageInfo 交给页面就可以了, navigatePages 表示连续显示的页码数
		PageInfo<Employee> page = new PageInfo<>(list, 5);

		return Message.success().add("pageInfo", page);
	}
	
	/**
	 * 查询员工数据, 使用分页查询, 不适用 json 格式返回
	 */
	@RequestMapping("/emps")
	public String getEmployees(@RequestParam(value="page", defaultValue="1")Integer pageNo, 
			Model model) {
		//传入页面, 以及每一页的大小
		PageHelper.startPage(pageNo, 10);
		
		List<Employee> list = employeeService.getEmployees();
		
		//使用 pageInfo 包装查询后的结果, 只需要将 pageInfo 交给页面就可以了, navigatePages 表示连续显示的页码数
		PageInfo<Employee> page = new PageInfo<>(list, 5);
		model.addAttribute("pageInfo", page);
		
		return "list";
	}
	
}
