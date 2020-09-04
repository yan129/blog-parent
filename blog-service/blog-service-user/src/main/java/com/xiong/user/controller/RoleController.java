package com.xiong.user.controller;


import com.xiong.common.util.R;
import com.xiong.user.entity.Role;
import com.xiong.user.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xiong
 * @since 2020-08-29
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "添加角色")
    @PostMapping("/add")
    public R addRole(@RequestBody @Valid Role role, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        return roleService.addRole(role);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/del")
    public R deleteRoleById(@RequestParam(value = "id", required = true) String id){
        return roleService.deleteRoleById(id);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public R updateRoleById(@RequestBody @Valid Role role, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return R.error(message);
        }
        return roleService.updateRoleById(role);
    }

}
