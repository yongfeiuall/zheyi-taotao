package com.izheyi.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbUser;

public interface SsoService {
	TaotaoResult checkUserData(String content, Integer type);
	TaotaoResult createUser(TbUser user);
	TaotaoResult getUserByToken(String token);
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
