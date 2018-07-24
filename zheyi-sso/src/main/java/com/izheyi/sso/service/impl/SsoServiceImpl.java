package com.izheyi.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.JsonUtils;
import com.izheyi.mapper.TbUserMapper;
import com.izheyi.pojo.TbUser;
import com.izheyi.pojo.TbUserExample;
import com.izheyi.pojo.TbUserExample.Criteria;
import com.izheyi.sso.redis.impl.RedisPool;
import com.izheyi.sso.service.SsoService;

@Service
public class SsoServiceImpl implements SsoService {
	
	@Value("${SSO_SESSION_KEY}")
	private String SSO_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private RedisPool redisPool;
	
	/*
	 * check user date
	 * @see com.izheyi.sso.service.SsoService#checkUserData(java.lang.String, java.lang.Integer)
	 */
	@Override
	public TaotaoResult checkUserData(String content, Integer type) {
		TbUserExample userExample = new TbUserExample();
		Criteria criteria = userExample.createCriteria();
		
		if (type == 1) {
			criteria.andUsernameEqualTo(content);
		}else if (type == 2) {
			criteria.andPhoneEqualTo(content);
		}else {
			criteria.andEmailEqualTo(content);
		}
		
		List<TbUser> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		
		return TaotaoResult.ok(false);
	}
	
	/*
	 * create user
	 * @see com.izheyi.sso.service.SsoService#createUser(com.izheyi.pojo.TbUser)
	 */
	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		
		return TaotaoResult.ok();
	}
	
	/*
	 * user login
	 * @see com.izheyi.sso.service.SsoService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public TaotaoResult userLogin(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		
		// username
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		
		if (list == null || list.size() == 0) {
			return TaotaoResult.build(400, "username or password wrong");
		}
		
		// password
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "username or password wrong");
		}
		
		// token
		String token = UUID.randomUUID().toString();
		
		// add redis
		redisPool.set(SSO_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		redisPool.expire(SSO_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		
		//return token
		return TaotaoResult.ok(token);
	}
	
	/*
	 * get user by token
	 * @see com.izheyi.sso.service.SsoService#getUserByToken(java.lang.String)
	 */
	@Override
	public TaotaoResult getUserByToken(String token) {
		String result = redisPool.get(SSO_SESSION_KEY + ":" + token);
		
		if (StringUtils.isBlank(result)) {
			return TaotaoResult.build(400, "session expired, please relogin");
		}
		
		redisPool.expire(SSO_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		
		return TaotaoResult.ok(JsonUtils.jsonToPojo(result, TbUser.class));
	}

}
