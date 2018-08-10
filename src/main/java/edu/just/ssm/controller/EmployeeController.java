package edu.just.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
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

/*	@RequestMapping(value="/emps/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmployees(@PathVariable("ids")String ids) {
		List<Integer> list = new ArrayList<>();
		String[] idStrs = ids.split("-");
		
		if(idStrs != null) {
			for(String idStr: idStrs) {
				Integer idInte = Integer.parseInt(idStr);
				list.add(idInte);
			}
		}
		
		employeeService.deleteEmployees(list);
		return Message.success();
	}*/
	
	/**
	 * 删除单个或者多个员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmployee(@PathVariable("ids")String empIdStr) {
		//如果是批量删除
		if(empIdStr.contains("-")) {
			List<Integer> list = new ArrayList<>();
			String[] idStrs = empIdStr.split("-");
			
			if(idStrs != null) {
				for(String idStr: idStrs) {
					Integer idInte = Integer.parseInt(idStr);
					list.add(idInte);
				}
			}
			
			employeeService.deleteEmployees(list);
		//如果是单个删除
		} else {
			Integer empId = Integer.parseInt(empIdStr);
			employeeService.deleteEmployee(empId);
		}
		return Message.success();
	}
	
	/**
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
	@ResponseBody
	public Message updateEmployee(Employee employee, HttpServletRequest request) {
		System.out.println(employee);
		employeeService.updateEmployee(employee);
		return Message.success();
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Message getEmployee(@PathVariable("id") Integer empId) {
		Employee employee = employeeService.getEmployeeById(empId);
		return Message.success().add("employee", employee);
	}
	
	@RequestMapping("/checkUser")
	@ResponseBody
	public Message checkUser(String name) {
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!name.matches(regex)) {
			return Message.fail().add("mes", "格式不正确");
		}
		
		boolean flag = employeeService.checkUser(name);

		//此时表示有相同的员工
		if(!flag) {
			return Message.fail().add("mes", "用户名不可用");
		}
		
		//此时表示不存在相同的员工
		return Message.success();
	}
	
	/**
	 * 用于员工保存, 在后台对数据进行校验
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Message addEmployee(@Valid Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			
			//校验失败应该返回失败, 在模态框中显示校验失败的错误信息
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError: fieldErrors) {
				System.out.println("错误字段: " + fieldError.getField());
				System.out.println("错误信息: " + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			return Message.fail().add("error1", map);

		} else {
			employeeService.addEmployee(employee);
			return Message.success();
		}
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
