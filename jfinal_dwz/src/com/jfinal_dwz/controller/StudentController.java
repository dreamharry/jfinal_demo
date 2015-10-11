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
        // ��ȡ������ΪstudentID��ֵ
        // Student.dao.deleteById(getPara("studentID"));
        // ��ȡurl�����е�һ��ֵ
        Student.dao.deleteById(getParaToInt());//�޲η�����Ĭ�ϻ�ȡ��һ����ǰ�˴������Ĳ���
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
        setAttr("message","���ĳɹ���");
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
        setAttr("message","����ɹ���");
        setAttr("navTabId","student_info");
        setAttr("callbackType","closeCurrent");
        renderJson();
    }

}
