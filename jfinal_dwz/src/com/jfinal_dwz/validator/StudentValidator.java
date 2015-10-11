package com.jfinal_dwz.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class StudentValidator extends Validator {

	@Override
	protected void validate(Controller c) {
        c.keepPara("student.s_name");//���ύ��ֵ�ٴ���ҳ���Ա㱣��ԭ�������ֵ
        c.render("/add.html");
	}

	@Override
	protected void handleError(Controller c) {
        //��֤����name��������Ϣkey,������Ϣvalue
        validateRequiredString("student.studentname", "studentnameMsg",
                "������ѧ������!");
	}

}
