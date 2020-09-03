package com.xiong.user.mapper;

import com.xiong.user.entity.Role;
import com.xiong.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiong
 * @since 2020-08-28
 */
public interface UserMapper extends BaseMapper<User> {

    List<Role> findAllRolesByUserId(String userId);

    boolean register(User user);
}
