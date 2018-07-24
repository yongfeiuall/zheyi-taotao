package com.izheyi.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.ExceptionUtil;
import com.izheyi.pojo.TbUser;
import com.izheyi.sso.service.SsoService;

@Controller
@RequestMapping("/user")
public class SsoController {
	
	@Autowired
	private SsoService ssoService;
	
	/*
	 * check user data
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkUserData(@PathVariable String param, @PathVariable Integer type, String callback) {
		
		TaotaoResult result = null;
		try {
			result = ssoService.checkUserData(param, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}else {
			return result;
		}
	}
	
	/*
	 * create user
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createUser(TbUser user) {
		TaotaoResult result = null;
		
		try {
			result = ssoService.createUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 * user login
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username, String password){
		try {
			TaotaoResult result = ssoService.userLogin(username, password);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	/*
	 * get user by token
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		TaotaoResult result = null;
		
		try {
			result = ssoService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if (!StringUtils.isBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}else {
			return result;
		}
		
	}
}
