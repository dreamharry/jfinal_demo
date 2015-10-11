package com.jfinal_dwz.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class StudentInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
        System.out.println("Before action invoking");
        inv.invoke();
        System.out.println("After action invoking");

	}

}
