package com.jfinal_dwz.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class StudentValidator extends Validator {

	@Override
	protected void validate(Controller c) {
        c.keepPara("student.s_name");//将提交的值再传回页面以便保持原先输入的值
        c.render("/add.html");
	}

	@Override
	protected void handleError(Controller c) {
        //验证表单域name，返回信息key,返回信息value
        validateRequiredString("student.studentname", "studentnameMsg",
                "请输入学生名称!");
	}

}
