package com.izheyi.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.izheyi.common.utils.CookieUtils;
import com.izheyi.pojo.TbUser;
import com.izheyi.portal.service.UserService;
import com.izheyi.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/// get token from cookie
		String token = CookieUtils.getCookieValue(request, "Z_TOKEN");
		
		// get user by token
		TbUser user = userService.getUserByToken(token);
		
		if (user == null) {
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_USER_LOGIN 
					+ "?redirect=" + request.getRequestURI());
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
