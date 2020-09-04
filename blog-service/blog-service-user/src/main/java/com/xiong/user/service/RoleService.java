package com.xiong.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.common.util.R;
import com.xiong.user.entity.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xiong
 * @since 2020-08-29
 */
public interface RoleService extends IService<Role> {

    R addRole(Role role);

    R deleteRoleById(String id);

    R updateRoleById(Role role);

    R findRoleByUsernameId(String uid);

    String findRidByRoleName(String roleName);

}
