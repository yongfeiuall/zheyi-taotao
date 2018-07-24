package com.izheyi.sso.service;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbUser;

public interface SsoService {
	TaotaoResult checkUserData(String content, Integer type);
	TaotaoResult createUser(TbUser user);
	TaotaoResult userLogin(String username, String password);
	TaotaoResult getUserByToken(String token);
}
