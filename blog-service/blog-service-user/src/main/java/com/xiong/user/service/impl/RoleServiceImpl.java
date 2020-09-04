package com.xiong.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.common.util.R;
import com.xiong.user.entity.Role;
import com.xiong.user.mapper.RoleMapper;
import com.xiong.user.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-08-29
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public R addRole(Role role) {
        boolean result = this.save(role);
        return result ? R.success("角色添加成功") : R.error("角色添加失败");
    }

    @Override
    public R deleteRoleById(String id) {
        boolean removeById = this.removeById(id);
        return removeById ? R.success("角色删除成功") : R.error("角色删除失败");
    }

    @Override
    public R updateRoleById(Role role) {
        boolean updateById = this.updateById(role);
        return updateById ? R.success("角色修改成功") : R.error("角色修改失败");
    }

    @Override
    public R findRoleByUsernameId(String uid) {
        return null;
    }

    @Override
    public String findRidByRoleName(String roleName) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("role_name", roleName);
        Role role = this.getOne(wrapper);
        return role.getId();
    }
}
