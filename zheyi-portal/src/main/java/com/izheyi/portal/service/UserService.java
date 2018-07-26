package com.izheyi.portal.service;

import com.izheyi.pojo.TbUser;

public interface UserService {
TbUser getUserByToken(String token);
}
