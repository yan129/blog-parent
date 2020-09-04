package com.xiong.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.user.entity.UserRoleRelation;
import com.xiong.user.mapper.UserRoleRelationMapper;
import com.xiong.user.service.UserRoleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2020-09-04
 */
@Service
@Transactional
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements UserRoleRelationService {

}
