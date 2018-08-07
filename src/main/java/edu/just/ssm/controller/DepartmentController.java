package edu.just.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.just.ssm.model.Department;
import edu.just.ssm.model.Message;
import edu.just.ssm.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 返回所有的部门信息
	 */
	@RequestMapping(value="/depts")
	@ResponseBody
	public Message getDepts() {
		List<Department> depts = departmentService.getDepts();
		return Message.success().add("depts", depts);
	}
}
