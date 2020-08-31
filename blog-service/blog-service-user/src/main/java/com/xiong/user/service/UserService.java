package com.xiong.user.service;

import com.xiong.common.util.R;
import com.xiong.user.entity.Role;
import com.xiong.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.user.vo.LoginVo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-28
 */
public interface UserService extends IService<User> {

    User findUserByUsername(String username);

    List<Role> findAllRolesByUserId(String userId);

    R register(LoginVo loginVo);

}
