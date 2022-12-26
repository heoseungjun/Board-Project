package com.campus.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// interceptor ó���� Ŭ����
// �ݵ�� HandlerInterceptorAdaptor�� ��ӹ޾ƾ� ��

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// ��Ʈ�ѷ��� ����Ǳ� ���� ȣ��Ǵ� �޼ҵ�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		// �α��� ���θ� Ȯ���Ͽ� �α��ε� ��� ��� ����, ���� �ʾҴٸ� �α��� �������� ������
		HttpSession session=request.getSession();
		String logid = (String)session.getAttribute("logid");

		if(logid==null || logid=="") {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;    // ���� �� ���߰� �α��� ȭ������ �̵�
		}
		return true;
	}
	
	// ��Ʈ�ѷ� ���� �� view�� ���� ���� ȣ��Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   @Nullable ModelAndView modelAndView) throws Exception{
		
	}
	// ��Ʈ�ѷ� ���� �� ȣ��Ǵ� �޼ҵ�
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler,
								@Nullable Exception ex) throws Exception {
		
	}
	
	/*
	  �񵿱�� ��û�� ���� �� : postHandle�� preHandle�� �������� �ʰ� ��� ȣ��Ǵ� �޼ҵ�
	  afterConcurrentHandlingStated(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception
	 
	 */
}
