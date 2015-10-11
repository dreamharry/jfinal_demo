package com.jfinal_dwz.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal_dwz.common.DWZConstants;
import com.jfinal_dwz.interceptor.StudentInterceptor;
import com.jfinal_dwz.model.Student;
import com.jfinal_dwz.validator.StudentValidator;

public class StudentController extends Controller{

	
	@Before(StudentInterceptor.class)
	public void index(){
		int pageNum = getParaToInt("pageNum",DWZConstants.START_PAGE);
		int numPerPage = getParaToInt("numPerPage",DWZConstants.PAGE_SIZE);
		Page<Student> students = Student.dao.paginate(pageNum, numPerPage, "select * ", " from student");
//		List<Student>list = Student.dao.find("select * from student");
		setAttr("page",students);
		render("/student.html");
	}
	
    public void add() {
        render("/add.html");
    }
    
    public void delete() {
        // 获取表单域名为studentID的值
        // Student.dao.deleteById(getPara("studentID"));
        // 获取url请求中第一个值
        Student.dao.deleteById(getParaToInt());//无参方法，默认获取第一个从前端传过来的参数
        //forwardAction("/student");
        setAttr("statusCode",200);
        setAttr("navTabId","student_info");
        renderJson();
    }
    
    public void toEdit(){
    	Student student = Student.dao.findById(getParaToInt());
    	setAttr("student", student);
    	render("/edit.html");
    }
    
    public void update() {
        Student student = getModel(Student.class);
        student.update();
        setAttr("statusCode",200);
        setAttr("message","更改成功！");
        setAttr("navTabId","student_info");
        setAttr("callbackType","closeCurrent");
        renderJson();
    }
    
    public void get() {
        Student student = Student.dao.findById(getParaToInt());
        setAttr("student", student);
        render("/index2.html");
    }

    @Before(StudentValidator.class)
    public void save() {
        Student student = getModel(Student.class);
        student.save();
        setAttr("statusCode",200);
        setAttr("message","保存成功！");
        setAttr("navTabId","student_info");
        setAttr("callbackType","closeCurrent");
        renderJson();
    }

}
