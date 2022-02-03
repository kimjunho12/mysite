package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. HandlerMethod의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
//		System.out.println("getMethodAnnotation : "	+ auth);
//		System.out.println("getBean : " + handlerMethod.getBean());
//		System.out.println("getBeanType : " + handlerMethod.getBeanType());
//		System.out.println("getResolvedFromHandlerMethod : " + handlerMethod.getResolvedFromHandlerMethod());
//		System.out.println("getReturnType : " + handlerMethod.getReturnType());
//		System.out.println("hasMethodAnnotation : " + handlerMethod.hasMethodAnnotation(Auth.class));
//		System.out.println("getBeanType().getAnnotation : " + handlerMethod.getBeanType().getAnnotation(Auth.class));
//		System.out.println("getBeanType().getAnnotationsByType : " + handlerMethod.getBeanType().getAnnotationsByType(Auth.class));
//		System.out.println("getBeanType().getDeclaredAnnotation : " + handlerMethod.getBeanType().getDeclaredAnnotation(Auth.class));
//		System.out.println("getBeanType().getDeclaredAnnotationsByType : " + handlerMethod.getBeanType().getDeclaredAnnotationsByType(Auth.class));

		// 4. HandlerMethod에 @Auth가 없다면 Type에 있는지 확인(과제)
		if (auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		if (auth == null) {
			return true;
		}

		System.out.println(auth.annotationType().toString());

		// 5. @Auth가 적용이 되어있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 6. 인증 확인 -> controller의 handler(method) 실행
		return true;
	}

}
