package edu.just.ssm.controller;

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

	/**
	 * Ա��ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmployee(@PathVariable("id")Integer empId) {
		employeeService.deleteEmployee(empId);
		return Message.success();
	}
	
	/**
	 * Ա�����·���
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
			return Message.fail().add("mes", "��ʽ����ȷ");
		}
		
		boolean flag = employeeService.checkUser(name);

		//��ʱ��ʾ����ͬ��Ա��
		if(!flag) {
			return Message.fail().add("mes", "�û���������");
		}
		
		//��ʱ��ʾ��������ͬ��Ա��
		return Message.success();
	}
	
	/**
	 * ����Ա������, �ں�̨�����ݽ���У��
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Message addEmployee(@Valid Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			
			//У��ʧ��Ӧ�÷���ʧ��, ��ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError fieldError: fieldErrors) {
				System.out.println("�����ֶ�: " + fieldError.getField());
				System.out.println("������Ϣ: " + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			return Message.fail().add("error1", map);

		} else {
			employeeService.addEmployee(employee);
			return Message.success();
		}
	}
	
	/**
	 * ����չʾԱ��������, Ĭ�ϴӵ�һҳ��ʼ
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	@ResponseBody
	public Message getEmployeesWithJson(@RequestParam(value="page", defaultValue="1")Integer pageNo) {
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
