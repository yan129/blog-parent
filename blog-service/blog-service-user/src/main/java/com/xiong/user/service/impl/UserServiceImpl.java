package com.xiong.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.common.util.R;
import com.xiong.common.util.ResponseCode;
import com.xiong.user.entity.Role;
import com.xiong.user.entity.User;
import com.xiong.user.mapper.UserMapper;
import com.xiong.user.service.UserService;
import com.xiong.user.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-28
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService,UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 重写UserDetailsService的loadUserByUsername方法，用于WebSecurityConfig类登录时自动去匹配密码
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username.trim());
        if (StringUtils.isEmpty(user))
            throw new UsernameNotFoundException(ResponseCode.USER_ACCOUNT_NOT_EXIST.getMsg());
        //返回用户角色
        user.setRoles(findAllRolesByUserId(user.getId()));
        return user;
    }

    // 根据用户名查找用户信息
    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.select("id", "nickname", "username", "password", "avatar", "remark", "gender", "enabled", "not_expired",
//                "account_not_locked", "credentials_not_expired", "last_login_time");
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }

    // 根据用户ID返回该用户所有角色
    @Override
    public List<Role> findAllRolesByUserId(String userId) {
        return baseMapper.findAllRolesByUserId(userId);
    }

    // 用户注册
    @Override
    public R register(LoginVo loginVo) {
        //如果用户名存在，返回错误
        if (!StringUtils.isEmpty(findUserByUsername(loginVo.getUsername().trim())))
            return R.error("用户名已注册");
        StringBuffer buffer = new StringBuffer(loginVo.getUsername().trim());
        buffer.replace(3, 7, "****");
        String encode = bCryptPasswordEncoder.encode(loginVo.getPassword().trim());
        loginVo.setNickname(buffer.toString());
        loginVo.setPassword(encode);
        loginVo.setAvatar("");
        User user = new User();
        BeanUtils.copyProperties(loginVo, user);
        user.setNickname(buffer.toString());
        boolean result = baseMapper.register(user);
        return result ? R.success("注册成功") : R.error("注册失败");
    }
}
