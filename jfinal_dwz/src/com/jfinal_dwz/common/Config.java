package com.jfinal_dwz.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal_dwz.controller.IndexController;
import com.jfinal_dwz.controller.StudentController;
import com.jfinal_dwz.model.Student;

public class Config extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.FREE_MARKER);
//		me.setBaseViewPath("/WEB-INF/template");
		
	}

	@Override
	public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/student", StudentController.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("db.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
		getProperty("user"), getProperty("password"));
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		//≈‰÷√∑Ω—‘
		arp.setDialect(new MysqlDialect());
		arp.addMapping("student", Student.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("base"));
		
	}

}
