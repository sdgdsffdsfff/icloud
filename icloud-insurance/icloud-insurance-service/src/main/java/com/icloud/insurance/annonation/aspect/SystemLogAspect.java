package com.icloud.insurance.annonation.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icloud.insurance.annonation.SystemControllerLog;

@Aspect
@Component
public class SystemLogAspect {
	private static final Logger logger = LoggerFactory
			.getLogger(SystemLogAspect.class);

	// Controller层切点
	@Pointcut("@annotation(com.icloud.insurance.annonation.SystemControllerLog)")
	public void controllerAspect() {
		System.out.println("-----");
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object ob : args) {
			System.out.println(ob);
		}
		System.out.println("before");

	}
	
//	@Around("controllerAspect()")
//    public void around(ProceedingJoinPoint pjp) throws Throwable{
//		System.out.println("已经记录下操作日志@Around 方法执行前");
//        pjp.proceed();
//        System.out.println("已经记录下操作日志@Around 方法执行后");
//    }

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("controllerAspect()")
	public void after(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object ob : args) {
			System.out.println(ob);
		}
		System.out.println("after");
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}
}
