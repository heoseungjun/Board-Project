package com.campus.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// interceptor 처리할 클래스
// 반드시 HandlerInterceptorAdaptor를 상속받아야 함

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// 컨트롤러가 실행되기 전에 호출되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		// 로그인 여부를 확인하여 로그인된 경우 계속 실행, 되지 않았다면 로그인 페이지로 보내기
		HttpSession session=request.getSession();
		String logid = (String)session.getAttribute("logid");

		if(logid==null || logid=="") {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;    // 가던 길 멈추고 로그인 화면으로 이동
		}
		return true;
	}
	
	// 컨트롤러 실행 후 view로 가기 전에 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   @Nullable ModelAndView modelAndView) throws Exception{
		
	}
	// 컨트롤러 실행 후 호출되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler,
								@Nullable Exception ex) throws Exception {
		
	}
	
	/*
	  비동기식 요청이 있을 떄 : postHandle나 preHandle을 수행하지 않고 대신 호출되는 메소드
	  afterConcurrentHandlingStated(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception
	 
	 */
}
